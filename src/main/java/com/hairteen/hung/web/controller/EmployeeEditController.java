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

import com.hairteen.hung.web.entity.Employee;
import com.hairteen.hung.web.form.EmployeeEditForm;
import com.hairteen.hung.web.form.EmployeeForm;
import com.hairteen.hung.web.service.EmployeeService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.validator.EmployeeEditValidator;

@Controller
public class EmployeeEditController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeEditValidator employeeEditValidator;

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
 
        if (target.getClass() == EmployeeEditForm.class) {
            dataBinder.setValidator(employeeEditValidator);
        }
    }

    /**
     * Do employee Edit.
     * @param model
     * @param employeeForm
     * @return
     */
    @RequestMapping(value = "/employee_edit", method = RequestMethod.POST)
    public String employeeEdit(Model model,
            @ModelAttribute("employeeEditForm") @Validated EmployeeEditForm employeeForm,
            BindingResult result, RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        // Validate result
        if (result.hasErrors()) {
            // Get list employee pages
            Page<Employee> employeePages = employeeService.getEmployeePage(1);
            int totalPages = employeePages.getTotalPages();

            if (totalPages > 0) {
                // Create List page
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            model.addAttribute("modalFlag", ConstantDefine.MODAL_DISPLAY_EDIT_FLAG);
            model.addAttribute("employeeForm", new EmployeeForm());
            model.addAttribute("employeeEditForm", employeeForm);
            model.addAttribute("employeePages", employeePages);
            return "employee";
        }

        employeeService.editEmployee(employeeForm, user);
        return "redirect:/manager_employee_view?pageId=1";
    }
}
