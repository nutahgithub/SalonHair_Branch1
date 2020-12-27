package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.Service;
import com.hairteen.hung.web.entity.ServiceType;

@Repository
public interface ServiceRespository extends JpaRepository<Service, Integer>{

    /**
     * Get max id Service.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(sv.id), 0) FROM Service sv")
    Integer getMaxId();

    /**
     * Find all Service page.
     * 
     */
    @Query("SELECT sv from Service sv where sv.deleteTsamp IS NULL")
    Page<Service> findAll(Pageable pageable);

    /**
     * Get list all Service.
     * 
     */
    @Query("SELECT sv from Service sv where sv.deleteTsamp IS NULL")
    List<Service> findAll();

    /**
     * Get all service by service type.
     * 
     * @param serviceType
     * @return
     */
    @Query("SELECT sv from Service sv where sv.serviceType = :serviceType and sv.deleteTsamp IS NULL")
    List<Service> findByServiceType(ServiceType serviceType);

    /**
     * Get all service by service type page.
     * 
     * @param pageable
     * @param serviceType
     * @return
     */
    @Query("SELECT sv from Service sv where sv.serviceType = :serviceType and sv.deleteTsamp IS NULL")
    Page<Service> findAll(Pageable pageable, ServiceType serviceType);
}
