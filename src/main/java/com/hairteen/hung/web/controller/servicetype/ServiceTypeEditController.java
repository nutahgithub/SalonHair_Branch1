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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.form.ServiceTypeEditForm;
import com.hairteen.hung.web.form.ServiceTypeForm;
import com.hairteen.hung.web.service.ServiceTypeService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.validator.ServiceTypeEditValidator;

@Controller
public class ServiceTypeEditController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private ServiceTypeEditValidator serviceTypeEditValidator;

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
 
        if (target.getClass() == ServiceTypeEditForm.class) {
            dataBinder.setValidator(serviceTypeEditValidator);
        }
    }

    /**
     * Do employee Edit.
     * @param model
     * @param employeeForm
     * @return
     */
    @RequestMapping(value = "/service_type_edit", method = RequestMethod.POST)
    public String serviceTypeEdit(Model model,
            @ModelAttribute("serviceTypeEditForm") @Validated ServiceTypeEditForm serviceTypeEditForm,
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

            model.addAttribute("modalFlag", ConstantDefine.MODAL_DISPLAY_EDIT_FLAG);
            model.addAttribute("serviceTypeForm", new ServiceTypeForm());
            model.addAttribute("serviceTypeEditForm", serviceTypeEditForm);
            model.addAttribute("serviceTypePages", serviceTypePages);
            return "service_type_view";
        }

        serviceTypeService.editServiceType(serviceTypeEditForm, user);
        return "redirect:/manager_service_type_view?pageId=1";
    }
}
