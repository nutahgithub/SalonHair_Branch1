package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.Bill;

@Repository
public interface BillRespository extends JpaRepository<Bill, Integer>{

    /**
     * Get max id bill.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(b.id), 0) FROM Bill b")
    Integer getMaxId();

    /**
     * Get list all bill.
     * 
     */
    @Query("SELECT b from Bill b where b.deleteTsamp IS NULL")
    List<Bill> findAll();
}
