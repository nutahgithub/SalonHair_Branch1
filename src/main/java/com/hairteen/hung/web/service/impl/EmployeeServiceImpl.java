package com.hairteen.hung.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.Employee;
import com.hairteen.hung.web.form.EmployeeEditForm;
import com.hairteen.hung.web.form.EmployeeForm;
import com.hairteen.hung.web.respository.EmployeeRespository;
import com.hairteen.hung.web.service.EmployeeService;
import com.hairteen.hung.web.utils.ConstantDefine;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRespository employeeRespository;

    @Override
    public Page<Employee> getEmployeePage(int page) {

        int totalRecord = employeeRespository.findAll().size();
        int totalPage = totalRecord/ConstantDefine.PAGINATION_EMPLOYEE;

        if (totalRecord % ConstantDefine.PAGINATION_EMPLOYEE > 0) {
            totalPage++;
        }

        // page select > total Page
        if (page > totalPage  && totalPage > 0) {
            page = totalPage - 1;
        } else if (page <= 0) {
            // page select < 0
            page = 0;
        } else {
            // pages{0,1,2...)
            page = page -1;
        }

        Page<Employee> pages =employeeRespository.findAll(PageRequest.of(page, ConstantDefine.PAGINATION_EMPLOYEE));
        return pages;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRespository.findAll();
    }

    @Override
    public Employee getOneEmployeeByID(Integer id) {
        Employee employeeTemp = employeeRespository.findById(id).get();
        employeeTemp.setBillInfos(null);
        return employeeTemp;
    }

    @Override
    public void saveEmployee(EmployeeForm employeeForm, String accountId) {

        Date date = new Date();
        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("dd-MM-yyyy").parse(employeeForm.getBirthday());
            Float basicSalary = Float.parseFloat(employeeForm.getBasicSalary());
            Integer id = employeeRespository.getMaxId() + 1;

            employeeRespository.save(new Employee(id, employeeForm.getNameEmployee(),
                    employeeForm.getSex(), birthDate, employeeForm.getAddress(), employeeForm.getPhone(),
                    employeeForm.getEmail(), basicSalary, accountId, date, null, null, null, null));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void editEmployee(EmployeeEditForm employeeForm, String accountId) {
	    Employee employee = employeeRespository.findById(employeeForm.getIdEmployee()).get();
		Date date = new Date();
        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("dd-MM-yyyy").parse(employeeForm.getBirthday());
            Float basicSalary = Float.parseFloat(employeeForm.getBasicSalary());
            Integer id = employeeForm.getIdEmployee();

            employeeRespository.save(new Employee(id, employeeForm.getNameEmployee(),
                    employeeForm.getSex(), birthDate, employeeForm.getAddress(), employeeForm.getPhone(),
                    employeeForm.getEmail(), basicSalary, employee.getRegisterId(), employee.getRegisterTsamp(), accountId, date, null, null));
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}

    @Override
    public void deleteEmployee(Integer id, String accountId) {
        Employee employee = employeeRespository.findById(id).get();
        Date deleteTsamp = new Date();
        employee.setDeleteTsamp(deleteTsamp);
        employee.setDeleteId(accountId);
        employeeRespository.save(employee);
    }
}
