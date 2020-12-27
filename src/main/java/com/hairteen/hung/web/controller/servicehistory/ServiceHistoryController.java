package com.hairteen.hung.web.controller.servicehistory;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hairteen.hung.web.entity.ServiceHistory;
import com.hairteen.hung.web.form.ServiceHistoryConditionForm;
import com.hairteen.hung.web.service.ServiceHistoryService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.utils.ConvertDataUtils;

@Controller
public class ServiceHistoryController {

    @Autowired
    private ServiceHistoryService serviceHistoryService;

    /**
     * Init manager Service screen.
     * @param model
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/manager_service_history_view", method = RequestMethod.GET)
    public String serviceHistoryView(Model model,
            @ModelAttribute("serviceHistoryConditionForm") ServiceHistoryConditionForm serviceHistoryConditionForm,
            @RequestParam(required = false) String pageId,
            @RequestParam(required = false) String yearHistory,
            @RequestParam(required = false) String monthHistory) {

        //Integer serviceTypeInt;

        Integer yearHistoryInt = ConvertDataUtils.convertStringToNumber(yearHistory);
        Integer monthHistoryInt = ConvertDataUtils.convertStringToNumber(monthHistory);

        // Get year & month current
        if (yearHistoryInt == null || monthHistoryInt == null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            Integer currentMonth = calendar.get(Calendar.MONTH) + 1; // {0 - 11}
            Integer currentYear  = calendar.get(Calendar.YEAR);
            yearHistoryInt = currentYear;
            monthHistoryInt = currentMonth;
            serviceHistoryConditionForm.setYearHistory(yearHistoryInt.toString());
            serviceHistoryConditionForm.setMonthHistory(monthHistoryInt.toString());
        }

        // Get total record & page.
        int totalRecord = serviceHistoryService.getAllServiceHistoryByMonthAndService(
                yearHistoryInt,
                monthHistoryInt,
                1)
                .size();
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
        List<ServiceHistory> serviceHistories = serviceHistoryService.findServiceHistoryByMonthAndServicePage(
                yearHistoryInt,
                monthHistoryInt,
                1,
                pageIdInt);

        model.addAttribute("totalRecord", totalRecord);
        model.addAttribute("currentPage", pageIdInt + 1);
        model.addAttribute("servicePages", serviceHistories);
        return "service_history_view";
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
        if (totalRecord%ConstantDefine.PAGINATION_SERVICE_HISTORY==0) {
            return totalRecord/ConstantDefine.PAGINATION_SERVICE_HISTORY;
        } else {
            return totalRecord/ConstantDefine.PAGINATION_SERVICE_HISTORY + 1;
        }
    }
}
