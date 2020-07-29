package com.hairteen.hung.web.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.Account;

@Repository
public interface AccountRespository extends CrudRepository<Account, Integer>{

    @Query("SELECT coalesce(max(ac.id), 0) FROM Account ac")
    Integer getMaxId();
}
