package com.hairteen.hung.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.respository.ServiceTypeRespository;
import com.hairteen.hung.web.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService{

    @Autowired
    private ServiceTypeRespository serviceTypeRespository;

    @Override
    public List<ServiceType> getAllServiceType() {
        return (List<ServiceType>) serviceTypeRespository.findAll();
    }

    @Override
    public ServiceType getOneServiceTypeByID(Integer id) {
        return serviceTypeRespository.findById(id).get();
    }
}
