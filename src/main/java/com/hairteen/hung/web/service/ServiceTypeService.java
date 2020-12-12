package com.hairteen.hung.web.service;

import java.util.List;

import com.hairteen.hung.web.entity.ServiceType;

public interface ServiceTypeService {

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
}
