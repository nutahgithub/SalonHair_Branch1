package com.hairteen.hung.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.form.ServiceTypeEditForm;
import com.hairteen.hung.web.form.ServiceTypeForm;

public interface ServiceTypeService {

    /**
     * Get list ServiceType by page.
     * 
     * @param page
     * @return Page<Employee>
     */
    Page<ServiceType> getServiceTypePage(int page);

    /**
     * Get list all ServiceType.
     * 
     * @return list ServiceType.
     */
    List<ServiceType> getAllServiceType();

    /**
     * Get ServiceType by ID.
     * 
     * @param id
     * @return ServiceType
     */
    ServiceType getOneServiceTypeByID(Integer id);

    ServiceType getServiceTypeByID(Integer id);
    
    /**
     * Register ServiceType to DB.
     * 
     * @param employeeForm
     * @param accountId
     */
    void saveServiceType(ServiceTypeForm serviceTypeForm, String accountId);

    /**
     * Update ServiceType to DB.
     * 
     * @param employeeEditForm
     * @param accountId
     */
    void editServiceType(ServiceTypeEditForm serviceTypeEditForm, String accountId);

    /**
     * Delete logic ServiceType ( not delete physic).
     * 
     * @param id
     * @param accountId
     */
    void deleteServiceType(Integer id, String accountId);
}
