package com.hairteen.hung.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.form.ServiceTypeEditForm;
import com.hairteen.hung.web.form.ServiceTypeForm;
import com.hairteen.hung.web.respository.ServiceTypeRespository;
import com.hairteen.hung.web.service.ServiceTypeService;
import com.hairteen.hung.web.utils.ConstantDefine;

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
        ServiceType serviceType =  serviceTypeRespository.findById(id).get();
        serviceType.setServices(null);
        return serviceType;
    }

    @Override
    public Page<ServiceType> getServiceTypePage(int page) {
        int totalRecord = serviceTypeRespository.findAll().size();
        int totalPage = totalRecord/ConstantDefine.PAGINATION_SERVICE_TYPE;

        if (totalRecord % ConstantDefine.PAGINATION_SERVICE_TYPE > 0) {
            totalPage++;
        }

        // page select > total Page
        if (page > totalPage  && totalPage > 0) {
            page = totalPage - 1;
        } else if (page <= 0) {
            // page select < 0
            page = 0;
        } else {
            // pages{0,1,2...)
            page = page -1;
        }

        Page<ServiceType> pages =serviceTypeRespository.findAll(PageRequest.of(page, ConstantDefine.PAGINATION_SERVICE_TYPE));
        return pages;
    }

    @Override
    public void saveServiceType(ServiceTypeForm serviceTypeForm, String accountId) {
        Date date = new Date();
        Integer id = serviceTypeRespository.getMaxId() + 1;
        ServiceType serviceType = new ServiceType(id, serviceTypeForm.getNameServiceType(), accountId, date, null, null, null, null, null);
        serviceTypeRespository.save(serviceType);
    }

    @Override
    public void deleteServiceType(Integer id, String accountId) {
        ServiceType serviceType = this.getServiceTypeByID(id);
        Date deleteTsamp = new Date();
        serviceType.setDeleteTsamp(deleteTsamp);
        serviceType.setDeleteId(accountId);
        serviceTypeRespository.save(serviceType);
    }

    @Override
    public void editServiceType(ServiceTypeEditForm serviceTypeEditForm, String accountId) {
        Date date = new Date();
        ServiceType serviceType = this.getServiceTypeByID(serviceTypeEditForm.getIdServiceType());
        serviceType.setUpdateTsamp(date);
        serviceType.setUpdateId(accountId);
        serviceType.setNameServiceType(serviceTypeEditForm.getNameServiceType());
        serviceTypeRespository.save(serviceType);
    }

    @Override
    public ServiceType getServiceTypeByID(Integer id) {
        return serviceTypeRespository.findById(id).get();
    }
}
