package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.ServiceType;

@Repository
public interface ServiceTypeRespository extends JpaRepository<ServiceType, Integer>{

    /**
     * Get max id ServiceType.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(sv.id), 0) FROM ServiceType sv")
    Integer getMaxId();

    /**
     * Find all ServiceType page.
     * 
     */
    @Query("SELECT sv from ServiceType sv where sv.deleteTsamp IS NULL")
    Page<ServiceType> findAll(Pageable pageable);

    /**
     * Get list all ServiceType.
     * 
     */
    @Query("SELECT sv from ServiceType sv where sv.deleteTsamp IS NULL")
    List<ServiceType> findAll();
}
