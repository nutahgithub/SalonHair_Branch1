package com.hairteen.hung.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.hairteen.hung.web.entity.Employee;
import com.hairteen.hung.web.form.EmployeeForm;
import com.hairteen.hung.web.service.EmployeeService;
import com.hairteen.hung.web.validator.EmployeeValidator;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeValidator employeeValidator;

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
 
        if (target.getClass() == EmployeeForm.class) {
            dataBinder.setValidator(employeeValidator);
        }
    }

    /**
     * Init manager employee screen.
     * @param model
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/manager_employee_view", method = RequestMethod.GET)
    public String employeeView(Model model, @RequestParam(required = false) String pageId) {

        Integer pageIdInt;

        // Check page id is number
        try {
            pageIdInt = Integer.parseInt(pageId);
        } catch(NumberFormatException e) {
            pageIdInt = 1;
        }

        // Get list employee pages
        Page<Employee> employeePages = employeeService.getEmployeePage(pageIdInt);
        int totalPages = employeePages.getTotalPages();

        if (totalPages > 0) {
            // Create List page
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("employeeForm", new EmployeeForm());
        model.addAttribute("employeePages", employeePages);
        return "employee";
    }

    /**
     * Do employee register.
     * @param model
     * @param employeeForm
     * @return
     */
    @RequestMapping(value = "/employee_register", method = RequestMethod.POST)
    public String employeeRegister(Model model,
            @ModelAttribute("employeeForm") @Validated EmployeeForm employeeForm,
            BindingResult result, RedirectAttributes redirectAttributes) {

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

            model.addAttribute("modalFlag", 1);
            model.addAttribute("employeeForm", employeeForm);
            model.addAttribute("employeePages", employeePages);
            return "employee";
        }

        employeeService.saveEmployee(employeeForm);
        return "redirect:/manager_employee_view?pageId=1";
    }

    /**
     * Do employee register.
     * @param model
     * @param employeeForm
     * @return
     */
    @RequestMapping(value = "/employee_edit", method = RequestMethod.POST)
    public String employeeEdit(Model model,
            @ModelAttribute("employeeForm") @Validated EmployeeForm employeeForm,
            BindingResult result, RedirectAttributes redirectAttributes) {

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

            model.addAttribute("modalFlag", 1);
            model.addAttribute("employeeForm", employeeForm);
            model.addAttribute("employeePages", employeePages);
            return "employee";
        }

        employeeService.editEmployee(employeeForm);
        return "redirect:/manager_employee_view?pageId=1";
    }

    /**
     * Get Employee by ID (popup edit).
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    @ResponseBody
    public Employee findOne(Integer id) {
        return employeeService.getOneEmployeeByID(id);
    }
}
