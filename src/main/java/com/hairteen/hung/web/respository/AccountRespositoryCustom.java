package com.hairteen.hung.web.respository;

import com.hairteen.hung.web.entity.Account;

public interface AccountRespositoryCustom {

    Account findUserAccount(String userName, Boolean deleteflag);

    Account findUserAccountByEmail(String email, Boolean deleteflag);
}
