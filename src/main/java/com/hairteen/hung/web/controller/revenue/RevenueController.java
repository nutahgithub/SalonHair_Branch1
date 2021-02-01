package com.hairteen.hung.web.controller.revenue;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hairteen.hung.web.entity.Bill;
import com.hairteen.hung.web.entity.BillInfo;
import com.hairteen.hung.web.form.RevenueConditionForm;
import com.hairteen.hung.web.service.BillInfoService;
import com.hairteen.hung.web.service.BillService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.utils.ConvertDataUtils;
import com.hairteen.hung.web.utils.DateUtils;
import com.hairteen.hung.web.utils.StringUtil;

@Controller
public class RevenueController {

    @Autowired
    private BillInfoService billInfoService;

    @Autowired
    private BillService billService;

    /**
     * Manager revenue view.
     * 
     * @param model
     * @param chair
     * @return
     */
    @RequestMapping(value = "/manager_revenue_view", method = RequestMethod.GET)
    public String managerRevenueView(Model model,
            @ModelAttribute("revenueConditionForm") RevenueConditionForm revenueConditionForm,
            @RequestParam(required = false) String pageId,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo) {

        if (StringUtil.isNull(dateFrom) || StringUtil.isNull(dateTo)) {
            Date currentDate = new Date();
            dateFrom = DateUtils.getFirstDateOfMonth(currentDate, "yyyy-MM-dd");
            dateTo = DateUtils.getLastDateOfMonth(currentDate, "yyyy-MM-dd");
        }
        revenueConditionForm.setDateFrom(dateFrom);
        revenueConditionForm.setDateTo(dateTo);

        // Get total revenue
        List<Bill> allBills = billService.getAllBillByDate(dateFrom, dateTo);
        Float totalRevenue = 0f;
        for (Bill bill : allBills) {
            totalRevenue = totalRevenue + bill.getTotalMoney();
        }

        // Get total record & page.
        int totalRecord = allBills.size();
        int totalPages = this.getToTalPages(totalRecord);
        if (totalPages > 0) {
            // Create List page
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        // page select > total Page
        Integer pageIdInt = this.getPageId(pageId, totalPages);

        // Get list Service pages
        List<Bill> bills = billService.getBillByDatePaging(dateFrom, dateTo, pageIdInt);

        model.addAttribute("totalRevenue",totalRevenue);
        model.addAttribute("totalRecord", totalRecord);
        model.addAttribute("currentPage", pageIdInt + 1);
        model.addAttribute("billPages", bills);
        return "revenue_view";
    }

    @RequestMapping(value = "/get_details_bill", method = RequestMethod.POST)
    public @ResponseBody String getDetailBill(@RequestBody String jsonParam){
        
        Integer idBill = ConvertDataUtils.convertStringToNumber(jsonParam.split("idBill=")[1]);
        List<BillInfo> billInfoList = billInfoService.getAllBillInfoByBillId(idBill);
        Float totalPrice = billInfoService.totalPriceByIdBill(idBill);
        
        Locale localeEN = new Locale("vi", "VN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        JSONObject jsonAll = new JSONObject();
        JSONArray jArray = new JSONArray();
        int index = 0;
        for (BillInfo bi : billInfoList) {
            JSONObject json = new JSONObject();
            json.put("index", ++index);
            json.put("nameService", bi.getService().getNameService());
            json.put("countService", bi.getCountService());
            json.put("priceService", en.format(bi.getService().getPriceService()));
            json.put("priceServiceNumber", bi.getService().getPriceService());
            json.put("employeeName", bi.getEmployee().getNameEmployee());
            jArray.put(json);
        }
        jsonAll.put("billInfoList", jArray);
        jsonAll.put("totalPriceNumber", totalPrice);
        jsonAll.put("discount", billInfoList.get(0).getBill().getDisCount());
        jsonAll.put("totalPriceFinal", billInfoList.get(0).getBill().getTotalMoney());
        jsonAll.put("timeCheckIn", billInfoList.get(0).getBill().getDateCheckInFormat());
        jsonAll.put("timeCheckOut", billInfoList.get(0).getBill().getDateCheckOutFormat());
        return jsonAll.toString();
    }
    
    /**
     * Get page number.
     * 
     * @param pageIdInt
     * @param totalPages
     * @return
     */
    private Integer getPageId(String pageId, int totalPages) {
        Integer pageIdInt = ConvertDataUtils.convertStringToNumber(pageId);
        // Check page id is number
        if (pageIdInt == null) {
            pageIdInt = 1;
        }
        if (pageIdInt > totalPages  && totalPages > 0) {
            pageIdInt = totalPages - 1;
        } else if (pageIdInt <= 0) {
            // page select < 0
            pageIdInt = 0;
        } else {
            // pages{0,1,2...)
            pageIdInt = pageIdInt - 1;
        }
        return pageIdInt;
    }

    /**
     * Get total page.
     * 
     * @param totalRecord
     * @return
     */
    private int getToTalPages(int totalRecord) {
        if (totalRecord%ConstantDefine.PAGINATION_REVENUE==0) {
            return totalRecord/ConstantDefine.PAGINATION_REVENUE;
        } else {
            return totalRecord/ConstantDefine.PAGINATION_REVENUE + 1;
        }
    }
}
