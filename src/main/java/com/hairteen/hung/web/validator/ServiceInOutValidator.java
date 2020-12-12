package com.hairteen.hung.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.form.ServiceInOutForm;

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameService", "ServiceForm.nameService.NotEmpty");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idServiceType", "ServiceForm.serviceType.NotEmpty");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priceService", "ServiceForm.priceService.NotEmpty");
    }

}
