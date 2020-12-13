package com.hairteen.hung.web.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hairteen.hung.web.form.ServiceForm;
import com.hairteen.hung.web.service.ServiceService;

@Controller
public class ServiceDeleteController {

    @Autowired
    private ServiceService serviceService;

    /**
     * Do employee Edit.
     * @param model
     * @param employeeForm
     * @return
     */
    @RequestMapping(value = "/service_delete", method = RequestMethod.POST)
    public String serviceDelete(Model model,
            @RequestParam(value = "serviceTypeId",required = false) String serviceTypeId,
            @ModelAttribute("serviceForm") ServiceForm serviceForm) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        String serviceParam = serviceTypeId==null?"all":serviceTypeId;
        // Validate result
        if (serviceForm.getIdService() != null) {
            serviceService.deleteService(serviceForm.getIdService(), user);
        }
        return "redirect:/manager_service_view?pageId=1&serviceType="+serviceParam;
    }
}
