package com.hairteen.hung.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.form.ServiceTypeEditForm;

@Component
public class ServiceTypeEditValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ServiceTypeEditForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceTypeEditForm serviceTypeEditForm = (ServiceTypeEditForm)target;

        // Check not input
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameServiceType", "ServiceTypeForm.nameServiceType.NotEmpty");
    }

}
