package com.hairteen.hung.web.service;

import com.hairteen.hung.web.entity.Account;
import com.hairteen.hung.web.form.AccountForm;

public interface AccountService {

    Account findUserAccount(String userName, Boolean deleteflag);

    Account findUserAccountByEmail(String email, Boolean deleteflag);

    void saveAccount(AccountForm account);
}
