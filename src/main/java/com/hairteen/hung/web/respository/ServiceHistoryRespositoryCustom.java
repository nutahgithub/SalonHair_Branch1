package com.hairteen.hung.web.respository;

import java.util.List;

import com.hairteen.hung.web.entity.ServiceHistory;

public interface ServiceHistoryRespositoryCustom {

    /**
     * Get service history by month and service.
     * 
     * @param date
     * @param serviceId
     * @return List<ServiceHistory>
     */
    List<ServiceHistory> findServiceHistoryByMonthAndServicePage(Integer year, Integer month, Integer serviceId, int page);
    
    List<ServiceHistory> getAllServiceHistoryByMonthAndService(Integer year, Integer month, Integer serviceId);
}
