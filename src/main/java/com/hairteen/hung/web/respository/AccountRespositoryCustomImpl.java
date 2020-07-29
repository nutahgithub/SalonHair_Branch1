package com.hairteen.hung.web.respository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hairteen.hung.web.entity.Account;

@Repository
@Transactional
public class AccountRespositoryCustomImpl implements AccountRespositoryCustom{

    public final String DELETE_TSTAMP_IS_NULL = " and ac.deleteTsamp IS NULL";

    @Autowired
    private EntityManager entityManager;

    @Override
    public Account findUserAccount(String userName, Boolean deleteflag) {
        try {
            StringBuilder sql = new StringBuilder().append("Select ac from " + Account.class.getName() + " ac " //
                    + " Where ac.userName = :userName");

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
            StringBuilder sql = new StringBuilder().append("Select ac from " + Account.class.getName() + " ac " //
                    + " Where ac.emailUser = :emailUser");

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
