package com.hairteen.hung.web.service;

import com.hairteen.hung.web.entity.Account;
import com.hairteen.hung.web.form.AccountForm;

public interface AccountService {

    /**
     * Find user account by name.
     * 
     * @param userName
     * @param deleteflag
     * @return Account
     */
    Account findUserAccount(String userName, Boolean deleteflag);

    /**
     * Find user account by email.
     * 
     * @param email
     * @param deleteflag
     * @return Account
     */
    Account findUserAccountByEmail(String email, Boolean deleteflag);

    /**
     * Register account to DB.
     * 
     * @param account
     */
    void saveAccount(AccountForm account);
}
