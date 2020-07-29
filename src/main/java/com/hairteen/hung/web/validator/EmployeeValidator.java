package com.hairteen.hung.web.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.form.EmployeeForm;

@Component
public class EmployeeValidator implements Validator{

    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == EmployeeForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        EmployeeForm employeeForm = (EmployeeForm)target;

        // Check not input
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameEmployee", "NotEmpty.employeeForm.name");
    }
    
    
}
