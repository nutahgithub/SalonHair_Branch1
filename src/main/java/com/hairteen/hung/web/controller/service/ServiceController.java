package com.hairteen.hung.web.controller.service;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hairteen.hung.web.entity.Service;
import com.hairteen.hung.web.form.ServiceEditForm;
import com.hairteen.hung.web.form.ServiceForm;
import com.hairteen.hung.web.form.ServiceInOutForm;
import com.hairteen.hung.web.service.ServiceService;
import com.hairteen.hung.web.service.ServiceTypeService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.validator.ServiceValidator;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private ServiceValidator serviceValidator;

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
 
        if (target.getClass() == ServiceForm.class) {
            dataBinder.setValidator(serviceValidator);
        }
    }

    /**
     * Init manager Service screen.
     * @param model
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/manager_service_view", method = RequestMethod.GET)
    public String serviceView(Model model, @RequestParam(required = false) String pageId,
            @RequestParam(required = false) String serviceType) {

        Integer pageIdInt;
        Integer serviceTypeInt;

        // Check page id is number
        try {
            pageIdInt = Integer.parseInt(pageId);
        } catch(NumberFormatException e) {
            pageIdInt = 1;
        }

        // Check serviceType id is number
        try {
            serviceTypeInt = Integer.parseInt(serviceType);
        } catch(NumberFormatException e) {
            serviceTypeInt = null;
        }

        // Get list Service pages
        Page<Service> servicePages = serviceService.getServicePage(pageIdInt, serviceTypeInt);
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
        model.addAttribute("serviceInOutForm", new ServiceInOutForm());
        model.addAttribute("serviceTypeId", serviceTypeInt==null?"all":serviceTypeInt);
        model.addAttribute("servicePages", servicePages);
        return "service_view";
    }

    /**
     * Do Service register.
     * @param model
     * @param ServiceForm
     * @return
     */
    @RequestMapping(value = "/service_register", method = RequestMethod.POST)
    public String serviceRegister(Model model,
            @RequestParam(value = "serviceTypeId",required = false) String serviceTypeId,
            @ModelAttribute("serviceForm") @Validated ServiceForm serviceForm,
            BindingResult result, RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        String serviceParam = serviceTypeId==null?"all":serviceTypeId;
        // Validate result
        if (result.hasErrors()) {
            // Get list Service pages
            Page<Service> servicePages = serviceService.getServicePage(1, null);
            int totalPages = servicePages.getTotalPages();

            if (totalPages > 0) {
                // Create List page
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            model.addAttribute("serviceTypes", serviceTypeService.getAllServiceType());
            model.addAttribute("serviceForm", serviceForm);
            model.addAttribute("serviceEditForm", new ServiceEditForm());
            model.addAttribute("serviceInOutForm", new ServiceInOutForm());
            model.addAttribute("servicePages", servicePages);
            model.addAttribute("modalFlag", ConstantDefine.MODAL_DISPLAY_ADD_FLAG);
            model.addAttribute("serviceTypeId", serviceParam);
            return "service_view";
        }

        serviceService.saveService(serviceForm, user);
        model.addAttribute("serviceTypeId", serviceParam);
        return "redirect:/manager_service_view?pageId=1&serviceType="+serviceParam;
    }

    /**
     * Get Service by ID.
     * @param id
     * @return Service
     */
    @RequestMapping("/service_find_one")
    @ResponseBody
    public Service findOne(Integer id) {
        return serviceService.getOneServiceByID(id);
    }
}
