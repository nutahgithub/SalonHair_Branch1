package com.hairteen.hung.web.controller.useservice;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hairteen.hung.web.entity.BillInfo;
import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.entity.Service;
import com.hairteen.hung.web.service.BillInfoService;
import com.hairteen.hung.web.service.BillService;
import com.hairteen.hung.web.service.ChairService;
import com.hairteen.hung.web.service.EmployeeService;
import com.hairteen.hung.web.service.ServiceService;
import com.hairteen.hung.web.service.ServiceTypeService;
import com.hairteen.hung.web.utils.ConvertDataUtils;
import com.hairteen.hung.web.utils.DateUtils;

@Controller
public class UseServiceController {

    @Autowired
    private ChairService chairService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private BillInfoService billInfoService;

    @Autowired
    private BillService billService;

    /**
     * Init use service screen.
     * @param model
     * @return
     */
    @RequestMapping(value = "/use_service_view", method = RequestMethod.GET)
    public String useServiceView(Model model,
            @RequestParam(value = "chair",required = false) String chair) {

        Integer idChair;
        try {
            idChair = Integer.parseInt(chair);
        } catch(NumberFormatException e) {
            idChair = 1;
        }

        // Get current chair
        Chair currentChair = chairService.getChairById(idChair);
        List<BillInfo> billInfoList = billInfoService.getAllBillInfoByBill(idChair);
        // Get time check in (HH:mm)
        String timeCheckIn = billInfoList.isEmpty()?"":DateUtils.formatDateToHM(billInfoList.get(0).getBill().getDateCheckIn());
        // Get list chair remove current chair.
        List<Chair> chairs = chairService.getAllChair();
        chairs.remove(currentChair);

        model.addAttribute("timeCheckIn", timeCheckIn);
        model.addAttribute("chair", currentChair);
        model.addAttribute("totalPrice", billInfoService.totalPrice(idChair));
        model.addAttribute("billInfos", billInfoList);
        model.addAttribute("chairLst", chairs);
        model.addAttribute("chairGroup", chairService.getGroupChair());
        model.addAttribute("serviceTypes", serviceTypeService.getAllServiceType());
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "use_service";
    }

    /**
     * Switch chair.
     * 
     * @param model
     * @param chair
     * @return
     */
    @RequestMapping(value = "/switch_chair", method = RequestMethod.GET)
    public String switchChair(Model model,
            @RequestParam(value = "chairOld",required = false) String chairOld,
            @RequestParam(value = "chairNew",required = false) String chairNew) {

        Integer idChairOld = ConvertDataUtils.convertStringToNumber(chairOld);
        Integer idChairNew = ConvertDataUtils.convertStringToNumber(chairNew);
        if (idChairNew != null && idChairOld != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String user = authentication.getName();
            billService.switchChairForBill(idChairOld, idChairNew, user);
            return "redirect:/use_service_view?chair=" + idChairNew;
        }
        return "redirect:/use_service_view?chair=1";
    }

    /**
     * Get service when select service type.
     * 
     * @param serviceTypeId
     * @return
     */
    @RequestMapping(value = "/get_service", method = RequestMethod.POST)
    public @ResponseBody String getService(@RequestBody String serviceTypeId){

        serviceTypeId = serviceTypeId.split("serviceTypeId=")[1];
        try {
            Integer serviceTypeInt = Integer.parseInt(serviceTypeId);
            List<Service> serviceList = serviceService.getServiceByType(serviceTypeInt);
            JSONObject jsonAll = new JSONObject();
            JSONArray jArray = new JSONArray();
            for (Service service : serviceList) {
                JSONObject json = new JSONObject();
                json.put("idService", service.getIdService());
                json.put("nameService", service.getNameService());
                jArray.put(json);
            }
            jsonAll.put("listService", jArray);
            return jsonAll.toString();
        } catch(NumberFormatException e) {
            return null;
        }
    }

    /**
     * Add service to Bill.
     * 
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/add_bill_info_to_bill", method = RequestMethod.POST)
    public @ResponseBody String addBillInfoToBill(@RequestBody String jsonParam){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        String[] listParam = jsonParam.split("&");
        Integer idChair = ConvertDataUtils.convertStringToNumber(listParam[0].split("idChair=")[1]);
        Integer idService = listParam[1].length() == 10? null :ConvertDataUtils.convertStringToNumber(listParam[1].split("idService=")[1]);
        Integer idEmployee = ConvertDataUtils.convertStringToNumber(listParam[2].split("idEmployee=")[1]);
        Integer countService = ConvertDataUtils.convertStringToNumber(listParam[3].split("countService=")[1]);

        // Check input parameter.
        if (idChair == null || idService == null || idEmployee == null || countService == null || countService == 0) {
            return "";
        }
        // Add bill & bill info
        BillInfo billInfo = billInfoService.addBillInfoToBill(idChair, user, idService, idEmployee, countService);

        List<BillInfo> billInfoList = billInfoService.getAllBillInfoByBill(idChair);
        Locale localeEN = new Locale("en", "EN");
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
            json.put("employeeName", bi.getEmployee().getNameEmployee());
            jArray.put(json);
        }
        jsonAll.put("billInfoList", jArray);
        jsonAll.put("totalPrice", en.format(billInfoService.totalPrice(idChair)) + " vnđ");
        jsonAll.put("timeCheckIn", DateUtils.formatDateToHM(billInfo.getBill().getDateCheckIn()));
        return jsonAll.toString();
    }
}
