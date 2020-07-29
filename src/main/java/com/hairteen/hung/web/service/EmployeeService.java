package com.hairteen.hung.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hairteen.hung.web.entity.Employee;
import com.hairteen.hung.web.form.EmployeeForm;

public interface EmployeeService {

    Page<Employee> getEmployeePage(int page);

    List<Employee> getAllEmployee();

    Employee getOneEmployeeByID(Integer id);

    void saveEmployee(EmployeeForm employeeForm);
}
