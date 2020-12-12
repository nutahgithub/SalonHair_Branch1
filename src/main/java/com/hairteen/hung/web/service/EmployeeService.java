package com.hairteen.hung.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hairteen.hung.web.entity.Employee;
import com.hairteen.hung.web.form.EmployeeEditForm;
import com.hairteen.hung.web.form.EmployeeForm;

public interface EmployeeService {

    /**
     * Get list employee by page.
     * 
     * @param page
     * @return Page<Employee>
     */
    Page<Employee> getEmployeePage(int page);

    /**
     * Get list all employee.
     * 
     * @return list employee.
     */
    List<Employee> getAllEmployee();

    /**
     * Get employee by ID.
     * 
     * @param id
     * @return Employee
     */
    Employee getOneEmployeeByID(Integer id);

    /**
     * Register employee to DB.
     * 
     * @param employeeForm
     * @param accountId
     */
    void saveEmployee(EmployeeForm employeeForm, String accountId);

    /**
     * Update employee to DB.
     * 
     * @param employeeEditForm
     * @param accountId
     */
    void editEmployee(EmployeeEditForm employeeEditForm, String accountId);

    /**
     * Delete logic employee ( not delete physic).
     * 
     * @param id
     * @param accountId
     */
    void deleteEmployee(Integer id, String accountId);
}
