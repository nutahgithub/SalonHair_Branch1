package com.hairteen.hung.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hairteen.hung.web.form.EmployeeForm;
import com.hairteen.hung.web.service.EmployeeService;

@Controller
public class EmployeeDeleteController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Do employee Edit.
     * @param model
     * @param employeeForm
     * @return
     */
    @RequestMapping(value = "/employee_delete", method = RequestMethod.POST)
    public String employeeDelete(Model model,
            @ModelAttribute("employeeForm") EmployeeForm employeeForm) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        // Validate result
        if (employeeForm.getIdEmployee() != null) {
            employeeService.deleteEmployee(employeeForm.getIdEmployee(), user);
        }
        return "redirect:/manager_employee_view?pageId=1";
    }
}
