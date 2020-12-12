package com.hairteen.hung.web.respository.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hairteen.hung.web.entity.Account;
import com.hairteen.hung.web.respository.AccountRespositoryCustom;

@Repository
@Transactional
public class AccountRespositoryCustomImpl implements AccountRespositoryCustom{

    /* Condition sql delete_tstamp is null */
    private final String DELETE_TSTAMP_IS_NULL = " and ac.deleteTsamp IS NULL";

    /* SQl get account by userName */
    private final StringBuilder SQL_FIND_ACCOUNT_BY_NAME = new StringBuilder()
            .append("Select ")
            .append(    "ac ")
            .append("from ")
            .append(    Account.class.getName()).append(" ac ")
            .append("Where ")
            .append("ac.userName = :userName");

    /* SQl get account by email */
    private final StringBuilder SQL_FIND_ACCOUNT_BY_EMAIL = new StringBuilder()
            .append("Select ")
            .append(    "ac ")
            .append("from ")
            .append(    Account.class.getName()).append(" ac ")
            .append("Where ")
            .append("ac.emailUser = :emailUser");
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Account findUserAccount(String userName, Boolean deleteflag) {
        try {
            StringBuilder sql = SQL_FIND_ACCOUNT_BY_NAME;
            // Check condition delete logic
            if(deleteflag) {
                sql.append(DELETE_TSTAMP_IS_NULL);
            }
 
            Query query = entityManager.createQuery(sql.toString(), Account.class);
            query.setParameter("userName", userName);
 
            return (Account) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Account findUserAccountByEmail(String email, Boolean deleteflag) {
        try {
            StringBuilder sql = SQL_FIND_ACCOUNT_BY_EMAIL;
            // Check condition delete logic
            if(deleteflag) {
                sql.append(DELETE_TSTAMP_IS_NULL);
            }
 
            Query query = entityManager.createQuery(sql.toString(), Account.class);
            query.setParameter("emailUser", email);
 
            return (Account) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
