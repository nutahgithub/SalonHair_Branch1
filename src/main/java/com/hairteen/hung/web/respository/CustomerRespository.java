package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Integer>{

    /**
     * Get max id employee.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(cs.id), 0) FROM Customer cs")
    Integer getMaxId();

    /**
     * Find all employee page.
     * 
     */
    @Query("SELECT cs from Customer cs where cs.deleteTsamp IS NULL")
    Page<Customer> findAll(Pageable pageable);

    /**
     * Get list all employee.
     * 
     */
    @Query("SELECT cs from Customer cs where cs.deleteTsamp IS NULL")
    List<Customer> findAll();
}
