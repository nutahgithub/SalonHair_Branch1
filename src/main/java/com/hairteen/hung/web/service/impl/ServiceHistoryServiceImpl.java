package com.hairteen.hung.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.ServiceHistory;
import com.hairteen.hung.web.form.ServiceInOutForm;
import com.hairteen.hung.web.respository.ServiceHistoryRespository;
import com.hairteen.hung.web.respository.ServiceHistoryRespositoryCustom;
import com.hairteen.hung.web.service.ServiceHistoryService;
import com.hairteen.hung.web.utils.ConstantDefine;

@Service
public class ServiceHistoryServiceImpl implements ServiceHistoryService{

    @Autowired
    private ServiceHistoryRespository serviceHistoryRespository;

    @Autowired
    private ServiceHistoryRespositoryCustom serviceHistoryRespositoryCustom;

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

    @Override
    public List<ServiceHistory> findServiceHistoryByMonthAndServicePage(Integer year, Integer month, Integer serviceId, int page) {
        page = page * ConstantDefine.PAGINATION_SERVICE_HISTORY;
        return serviceHistoryRespositoryCustom.findServiceHistoryByMonthAndServicePage(year, month, null, page);
    }

    @Override
    public List<ServiceHistory> getAllServiceHistoryByMonthAndService(Integer year, Integer month, Integer serviceId) {
        return serviceHistoryRespositoryCustom.getAllServiceHistoryByMonthAndService(year, month, null);
    }

}
