package com.hairteen.hung.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.form.ServiceInOutForm;
import com.hairteen.hung.web.utils.ValidatorUtils;

@Component
public class ServiceInOutValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ServiceInOutForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceInOutForm serviceForm = (ServiceInOutForm)target;

        // Check not input
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "statusInOut", "ServiceInOutForm.statusInOut.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countInOut", "ServiceInOutForm.countInOut.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resonInOut", "ServiceInOutForm.resonInOut.NotEmpty");
        if (!errors.hasFieldErrors("countInOut")) {
            if (!ValidatorUtils.validateNumberPlus(serviceForm.getCountInOut())) {
                errors.rejectValue("countInOut", "ServiceInOutForm.countInOut.Pattern");
            }
        }
    }

}
