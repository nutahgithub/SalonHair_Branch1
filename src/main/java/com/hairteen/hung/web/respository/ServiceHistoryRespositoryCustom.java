package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.ServiceHistory;

@Repository
public interface ServiceHistoryRespositoryCustom {

    /**
     * Get service history by month and service.
     * 
     * @param date
     * @param serviceId
     * @return List<ServiceHistory>
     */
    List<ServiceHistory> findServiceHistoryByMonthAndServicePage(Integer year, Integer month, Integer serviceId, int page);

    /**
     * Get all service history by month and service.
     * 
     * @param year
     * @param month
     * @param serviceId
     * @return
     */
    List<ServiceHistory> getAllServiceHistoryByMonthAndService(Integer year, Integer month, Integer serviceId);
}
