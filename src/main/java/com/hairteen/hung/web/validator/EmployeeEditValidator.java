package com.hairteen.hung.web.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.form.EmployeeEditForm;
import com.hairteen.hung.web.utils.StringUtil;
import com.hairteen.hung.web.utils.ValidatorUtils;

@Component
public class EmployeeEditValidator implements Validator{

    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == EmployeeEditForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        EmployeeEditForm employeeEditForm = (EmployeeEditForm)target;

     // Check not input
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameEmployee", "EmployeeForm.nameEmployee.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "EmployeeForm.sex.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "EmployeeForm.birthday.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "EmployeeForm.address.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "EmployeeForm.phone.NotEmpty");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "EmployeeForm.email.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "basicSalary", "EmployeeForm.basicSalary.NotEmpty");
        // Check date
        if (!errors.hasFieldErrors("birthday")) {
            if (!ValidatorUtils.validateJavaDate(employeeEditForm.getBirthday())) {
                errors.rejectValue("birthday", "EmployeeForm.birthday.Format");
            }
        }
        // Check phone
        if (!errors.hasFieldErrors("phone")) {
            if (!ValidatorUtils.validatePhoneNumber(employeeEditForm.getPhone())) {
                errors.rejectValue("phone", "EmployeeForm.phone.Pattern");
            }
        }
        // Check email
        if(!StringUtil.isNull(employeeEditForm.getEmail()) && !this.emailValidator.isValid(employeeEditForm.getEmail())) {
            // Email not valid.
            errors.rejectValue("email", "EmployeeForm.email.Pattern");
        }
        if (!errors.hasFieldErrors("basicSalary")) {
            if (!ValidatorUtils.validateNumberPlus(employeeEditForm.getBasicSalary())) {
                errors.rejectValue("basicSalary", "EmployeeForm.basicSalary.Pattern");
            }
        }
    }
}
