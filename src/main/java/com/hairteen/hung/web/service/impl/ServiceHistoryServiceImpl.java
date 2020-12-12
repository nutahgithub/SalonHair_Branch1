package com.hairteen.hung.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.ServiceHistory;
import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.form.ServiceInOutForm;
import com.hairteen.hung.web.respository.ServiceHistoryRespository;
import com.hairteen.hung.web.service.ServiceHistoryService;

@Service
public class ServiceHistoryServiceImpl implements ServiceHistoryService{

    @Autowired
    private ServiceHistoryRespository serviceHistoryRespository;
    
    @Override
    public Page<ServiceHistory> getServicePage(int page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ServiceHistory> getAllService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceHistory getOneServiceByID(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveService(ServiceInOutForm serviceInOutForm, String accountId) {
        Date date = new Date();
        ServiceHistory serviceHistory = new ServiceHistory();

        Integer id = serviceHistoryRespository.getMaxId() + 1;
        Integer countInOut = Integer.parseInt(serviceInOutForm.getCountInOut());

        serviceHistory.setIdServiceHistory(id);
        serviceHistory.setService(new com.hairteen.hung.web.entity.Service(serviceInOutForm.getIdService()));
        serviceHistory.setStatusInOut(serviceInOutForm.getStatusInOut());
        serviceHistory.setCountInOut(countInOut);
        serviceHistory.setReasonInOut(serviceInOutForm.getResonInOut());
        serviceHistory.setRegisterId(accountId);
        serviceHistory.setRegisterTsamp(date);
        serviceHistoryRespository.save(serviceHistory);
        
    }

}
