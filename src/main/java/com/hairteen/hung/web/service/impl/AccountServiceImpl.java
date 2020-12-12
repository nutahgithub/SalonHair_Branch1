package com.hairteen.hung.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.Account;
import com.hairteen.hung.web.form.AccountForm;
import com.hairteen.hung.web.respository.AccountRespository;
import com.hairteen.hung.web.respository.AccountRespositoryCustom;
import com.hairteen.hung.web.service.AccountService;
import com.hairteen.hung.web.utils.EncrytedPasswordUtils;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService{

    @Autowired
    private AccountRespositoryCustom accountRespositoryCustom;

    @Autowired
    private AccountRespository accountRespository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // Get account of userName input
        Account account = this.accountRespositoryCustom.findUserAccount(userName, true);

        if (account == null) {
            //System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("account " + userName + " was not found in the database");
        }

        System.out.println("Found account: " + account);
 
        // Add role
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        grantList.add(new SimpleGrantedAuthority(account.getRole()));
//        if (roleNames != null) {
//            for (String role : roleNames) {
//                // ROLE_USER, ROLE_ADMIN,..
//                GrantedAuthority authority = new SimpleGrantedAuthority(role);
//                grantList.add(authority);
//            }
//        }
 
        UserDetails userDetails = (UserDetails) new User(account.getUserName(),
                account.getPassword(), grantList);
 
        return userDetails;
    }

    @Override
    public Account findUserAccount(String userName, Boolean deleteflag) {
        return accountRespositoryCustom.findUserAccount(userName, deleteflag);
    }

    @Override
    public Account findUserAccountByEmail(String email, Boolean deleteflag) {
        return accountRespositoryCustom.findUserAccountByEmail(email, deleteflag);
    }

    @Override
    public void saveAccount(AccountForm account) {

        String encrypted = EncrytedPasswordUtils.encrytePassword(account.getPassword());

        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Integer id = accountRespository.getMaxId() + 1;

        accountRespository.save(new Account(id, account.getUserName(), encrypted, account.getEmailUser(), date));
    }


}
