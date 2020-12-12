package com.hairteen.hung.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.form.ServiceEditForm;
import com.hairteen.hung.web.utils.ValidatorUtils;

@Component
public class ServiceEditValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ServiceEditForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceEditForm serviceForm = (ServiceEditForm)target;

        // Check not input
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameService", "ServiceForm.nameService.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idServiceType", "ServiceForm.serviceType.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priceService", "ServiceForm.priceService.NotEmpty");

        if (!errors.hasFieldErrors("priceService")) {
            if (!ValidatorUtils.validateNumberPlus(serviceForm.getPriceService())) {
                errors.rejectValue("priceService", "ServiceForm.priceService.Pattern");
            }
        }
    }

}
