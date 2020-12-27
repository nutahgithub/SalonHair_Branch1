package com.hairteen.hung.web.controller.servicetype;

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

import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.form.ServiceTypeEditForm;
import com.hairteen.hung.web.form.ServiceTypeForm;
import com.hairteen.hung.web.service.ServiceTypeService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.validator.ServiceTypeValidator;

@Controller
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private ServiceTypeValidator serviceTypeValidator;

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
 
        if (target.getClass() == ServiceTypeForm.class) {
            dataBinder.setValidator(serviceTypeValidator);
        }
    }

    /**
     * Init manager Service type screen.
     * @param model
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/manager_service_type_view", method = RequestMethod.GET)
    public String serviceView(Model model, @RequestParam(required = false) String pageId) {

        Integer pageIdInt;

        // Check page id is number
        try {
            pageIdInt = Integer.parseInt(pageId);
        } catch(NumberFormatException e) {
            pageIdInt = 1;
        }

        // Get list employee pages
        Page<ServiceType> serviceTypePages = serviceTypeService.getServiceTypePage(pageIdInt);
        int totalPages = serviceTypePages.getTotalPages();

        if (totalPages > 0) {
            // Create List page
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("serviceTypeForm", new ServiceTypeForm());
        model.addAttribute("serviceTypeEditForm", new ServiceTypeEditForm());
        model.addAttribute("serviceTypePages", serviceTypePages);
        return "service_type_view";
    }

    /**
     * Do Service type register.
     * @param model
     * @param employeeForm
     * @return
     */
    @RequestMapping(value = "/service_type_register", method = RequestMethod.POST)
    public String serviceTypeRegister(Model model,
            @ModelAttribute("serviceTypeForm") @Validated ServiceTypeForm serviceTypeForm,
            BindingResult result, RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        // Validate result
        if (result.hasErrors()) {
            // Get list employee pages
            Page<ServiceType> serviceTypePages = serviceTypeService.getServiceTypePage(1);
            int totalPages = serviceTypePages.getTotalPages();

            if (totalPages > 0) {
                // Create List page
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            model.addAttribute("modalFlag", ConstantDefine.MODAL_DISPLAY_ADD_FLAG);
            model.addAttribute("serviceTypeForm", serviceTypeForm);
            model.addAttribute("serviceTypeEditForm", new ServiceTypeEditForm());
            model.addAttribute("serviceTypePages", serviceTypePages);
            return "service_type_view";
        }

        serviceTypeService.saveServiceType(serviceTypeForm, user);;
        return "redirect:/manager_service_type_view?pageId=1";
    }

    /**
     * Get Service type by ID.
     * @param id
     * @return Service
     */
    @RequestMapping("/service_type_find_one")
    @ResponseBody
    public ServiceType findOne(Integer id) {
        return serviceTypeService.getOneServiceTypeByID(id);
    }
}
