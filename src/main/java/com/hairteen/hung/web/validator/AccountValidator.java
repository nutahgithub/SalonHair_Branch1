package com.hairteen.hung.web.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hairteen.hung.web.entity.Account;
import com.hairteen.hung.web.form.AccountForm;
import com.hairteen.hung.web.service.AccountService;

@Component
public class AccountValidator implements Validator{

    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AccountForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        AccountForm accountForm = (AccountForm) target;

        // Check not input
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "AccountForm.userName.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "AccountForm.password.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "AccountForm.passwordConfirm.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailUser", "AccountForm.email.NotEmpty");

        // Check Password
        if (!errors.hasFieldErrors("password")) {
            if (accountForm.getPassword().length() < 8 || accountForm.getPassword().length() > 255) {
                errors.rejectValue("password", "AccountForm.password.Pattern");
            }
        }

        // Check email
        if (!errors.hasFieldErrors("emailUser")) {
            if(!this.emailValidator.isValid(accountForm.getEmailUser())) {
                // Email not valid.
                errors.rejectValue("emailUser", "AccountForm.email.Pattern");
            } else {
                Account account = accountService.findUserAccountByEmail(accountForm.getEmailUser(), true);
                if(account != null) {
                    // Email is exist in DB
                    errors.rejectValue("emailUser", "AccountForm.email.Duplicate");
                }
            }
        }

        // Check User Name
        if (!errors.hasFieldErrors("userName")) {
            Account account = accountService.findUserAccount(accountForm.getUserName(), false);
            if(account != null) {
                // UserName is exist in DB
                errors.rejectValue("userName", "AccountForm.userName.Duplicate");
            }
        }

        // Check password & passwordConfirm is mapping
        if (!errors.hasErrors()) {
            if (!accountForm.getPasswordConfirm().equals(accountForm.getPassword())) {
                errors.rejectValue("passwordConfirm", "AccountForm.passwordConfirm.Match");
            }
        }
    }
}
