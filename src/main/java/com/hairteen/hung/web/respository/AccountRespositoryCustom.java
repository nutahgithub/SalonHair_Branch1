package com.hairteen.hung.web.respository;

import com.hairteen.hung.web.entity.Account;

public interface AccountRespositoryCustom {

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
}
