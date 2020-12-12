package com.hairteen.hung.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hairteen.hung.web.entity.Service;
import com.hairteen.hung.web.form.ServiceEditForm;
import com.hairteen.hung.web.form.ServiceForm;
import com.hairteen.hung.web.form.ServiceInOutForm;
import com.hairteen.hung.web.service.ServiceHistoryService;
import com.hairteen.hung.web.service.ServiceService;
import com.hairteen.hung.web.service.ServiceTypeService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.validator.ServiceInOutValidator;

@Controller
public class ServiceInOutController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private ServiceHistoryService serviceHistoryService;

    @Autowired
    private ServiceInOutValidator serviceValidator;

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
 
        if (target.getClass() == ServiceInOutForm.class) {
            dataBinder.setValidator(serviceValidator);
        }
    }

    /**
     * Do Service edit.
     * @param model
     * @param ServiceForm
     * @return
     */
    @RequestMapping(value = "/service_in_out", method = RequestMethod.POST)
    public String serviceEdit(Model model,
            @ModelAttribute("serviceInOutForm") @Validated ServiceInOutForm serviceInOutForm,
            BindingResult result, RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        // Validate result
        if (result.hasErrors()) {
            // Get list Service pages
            Page<Service> servicePages = serviceService.getServicePage(1);
            int totalPages = servicePages.getTotalPages();

            if (totalPages > 0) {
                // Create List page
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            model.addAttribute("serviceTypes", serviceTypeService.getAllServiceType());
            model.addAttribute("serviceForm", new ServiceForm());
            model.addAttribute("serviceEditForm", new ServiceEditForm());
            model.addAttribute("serviceInOutForm", serviceInOutForm);
            model.addAttribute("servicePages", servicePages);
            model.addAttribute("modalFlag", ConstantDefine.MODAL_DISPLAY_IN_OUT_FLAG);
            return "service_view";
        }

        serviceService.inOutService(serviceInOutForm, user);
        serviceHistoryService.saveService(serviceInOutForm, user);
        return "redirect:/manager_service_view?pageId=1";
    }
}
