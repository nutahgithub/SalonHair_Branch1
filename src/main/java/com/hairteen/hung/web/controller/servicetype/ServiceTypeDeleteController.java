package com.hairteen.hung.web.controller.servicetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hairteen.hung.web.form.ServiceTypeForm;
import com.hairteen.hung.web.service.ServiceTypeService;

@Controller
public class ServiceTypeDeleteController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    /**
     * Do service type delete.
     * @param model
     * @param serviceTypeForm
     * @return
     */
    @RequestMapping(value = "/service_type_delete", method = RequestMethod.POST)
    public String serviceTypeEdit(Model model,
            @ModelAttribute("serviceTypeForm") @Validated ServiceTypeForm serviceTypeForm) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        if (serviceTypeForm.getIdServiceType() != null) {
            serviceTypeService.deleteServiceType(serviceTypeForm.getIdServiceType(), user);
        }
        return "redirect:/manager_service_type_view?pageId=1";
    }
}
