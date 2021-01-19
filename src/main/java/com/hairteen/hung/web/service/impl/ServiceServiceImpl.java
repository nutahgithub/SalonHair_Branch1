package com.hairteen.hung.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.form.ServiceEditForm;
import com.hairteen.hung.web.form.ServiceForm;
import com.hairteen.hung.web.form.ServiceInOutForm;
import com.hairteen.hung.web.respository.ServiceRespository;
import com.hairteen.hung.web.service.ServiceService;
import com.hairteen.hung.web.utils.ConstantDefine;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRespository serviceRespository;

    @Override
    public Page<com.hairteen.hung.web.entity.Service> getServicePage(int page, Integer serviceTypeId) {

        int totalRecord;
        if (serviceTypeId == null) {
            totalRecord = serviceRespository.findAll().size();
        } else {
            totalRecord = serviceRespository.findByServiceType(new ServiceType(serviceTypeId)).size();
        }

        int totalPage = totalRecord/ConstantDefine.PAGINATION_SERVICE;
        if (totalRecord % ConstantDefine.PAGINATION_SERVICE > 0) {
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

        // Get detail info.
        Page<com.hairteen.hung.web.entity.Service> pages;
        if (serviceTypeId == null) {
            pages = serviceRespository.findAll(PageRequest.of(page, ConstantDefine.PAGINATION_SERVICE));
        } else {
            pages = serviceRespository.findAll(PageRequest.of(page, ConstantDefine.PAGINATION_SERVICE), new ServiceType(serviceTypeId));
        }
        return pages;
    }

    @Override
    public List<com.hairteen.hung.web.entity.Service> getAllService() {
        return (List<com.hairteen.hung.web.entity.Service>) serviceRespository.findAll();
    }

    @Override
    public com.hairteen.hung.web.entity.Service getOneServiceByID(Integer id) {
        com.hairteen.hung.web.entity.Service service = serviceRespository.findById(id).get();
        com.hairteen.hung.web.entity.Service service2 = new com.hairteen.hung.web.entity.Service(service.getIdService(),
                service.getNameService(), service.getPriceService(), service.getCountIn(), service.getCountOut(),
                service.getCountRemain(), service.getRegisterId(), service.getRegisterTsamp(), service.getUpdateId(),
                service.getUpdateTsamp(), service.getDeleteId(), service.getDeleteTsamp(), service.getServiceType().getIdServiceType());
        return service2;
    }

    @Override
    public void saveService(ServiceForm serviceForm, String accountId) {

        Date date = new Date();
        ServiceType serviceType = new ServiceType(serviceForm.getIdServiceType());
        Float priceService = Float.parseFloat(serviceForm.getPriceService());
        Integer id = serviceRespository.getMaxId() + 1;
        com.hairteen.hung.web.entity.Service service = new com.hairteen.hung.web.entity.Service(id, serviceForm.getNameService(), priceService,
                0, 0, 0, accountId, date, null, null, null, null, serviceType); 

            serviceRespository.save(service);
    }

    @Override
    public void editService(ServiceEditForm serviceForm, String accountId) {

        Integer id = serviceForm.getIdService();
        com.hairteen.hung.web.entity.Service service = this.getServiceByID(id);
        Date date = new Date();
        Float priceService = Float.parseFloat(serviceForm.getPriceService());
        service.setNameService(serviceForm.getNameService());
        service.setPriceService(priceService);
        service.setUpdateId(accountId);
        service.setUpdateTsamp(date);

            serviceRespository.save(service);
    }

    @Override
    public void deleteService(Integer id, String accountId) {
        com.hairteen.hung.web.entity.Service service = this.getServiceByID(id);
        Date deleteTsamp = new Date();
        service.setDeleteTsamp(deleteTsamp);
        service.setDeleteId(accountId);
        serviceRespository.save(service);
    }

    @Override
    public void inOutService(ServiceInOutForm serviceInOutForm, String accountId) {
        Date date = new Date();
        com.hairteen.hung.web.entity.Service serviceCurrent = this.getOneServiceByID(serviceInOutForm.getIdService());
        serviceCurrent.setServiceType(new ServiceType(serviceCurrent.getIdServiceType()));
        Integer countInOut = Integer.parseInt(serviceInOutForm.getCountInOut());
        if (serviceInOutForm.getStatusInOut() == 1) {
            serviceCurrent.setCountIn(serviceCurrent.getCountIn() + countInOut);
            serviceCurrent.setCountRemain(serviceCurrent.getCountRemain() + countInOut);
        } else if (serviceInOutForm.getStatusInOut() == 0) {
            serviceCurrent.setCountOut(serviceCurrent.getCountOut() + countInOut);
            serviceCurrent.setCountRemain(serviceCurrent.getCountRemain() - countInOut);
        }
        serviceCurrent.setUpdateId(accountId);
        serviceCurrent.setUpdateTsamp(date);
        serviceRespository.save(serviceCurrent);
    }

    @Override
    public com.hairteen.hung.web.entity.Service getServiceByID(Integer id) {
        return serviceRespository.findById(id).get();
    }

    @Override
    public List<com.hairteen.hung.web.entity.Service> getServiceByType(Integer serviceTypeId) {
        // TODO Auto-generated method stub
        return serviceRespository.findAll(new ServiceType(serviceTypeId));
    }
}
