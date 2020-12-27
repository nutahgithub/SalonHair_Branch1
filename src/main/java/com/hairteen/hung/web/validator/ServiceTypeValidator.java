package com.hairteen.hung.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.form.ServiceTypeForm;

@Component
public class ServiceTypeValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ServiceTypeForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceTypeForm serviceTypeForm = (ServiceTypeForm)target;

        // Check not input
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameServiceType", "ServiceTypeForm.nameServiceType.NotEmpty");
    }

}
