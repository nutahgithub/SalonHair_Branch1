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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.accountForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty.accountForm.passwordConfirm");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailUser", "NotEmpty.accountForm.email");

        // Check email
        if(!errors.hasFieldErrors("emailUser")) {
            if(!this.emailValidator.isValid(accountForm.getEmailUser())) {
                // Email not valid.
                errors.rejectValue("emailUser", "Pattern.accountForm.email");
            } else {
                Account account = accountService.findUserAccountByEmail(accountForm.getEmailUser(), true);
                if(account != null) {
                    // Email is exist in DB
                    errors.rejectValue("emailUser", "Duplicate.accountForm.email");
                }
            }
        }

        // Check User Name
        if(!errors.hasFieldErrors("userName")) {
            Account account = accountService.findUserAccount(accountForm.getUserName(), false);
            if(account != null) {
                // UserName is exist in DB
                errors.rejectValue("userName", "Duplicate.accountForm.userName");
            }
        }

        // Check password & passwordConfirm is mapping
        if (!errors.hasErrors()) {
            if (!accountForm.getPasswordConfirm().equals(accountForm.getPassword())) {
                errors.rejectValue("passwordConfirm", "Match.accountForm.passwordConfirm");
            }
        }
    }

}
