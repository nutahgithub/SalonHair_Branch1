package com.hairteen.hung.web.service;

import java.util.List;

import com.hairteen.hung.web.entity.ServiceHistory;
import com.hairteen.hung.web.form.ServiceInOutForm;

public interface ServiceHistoryService {

    /**
     * Find service history by month and service page.
     * 
     * @param year
     * @param month
     * @param serviceId
     * @param page
     * @return
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

    /**
     * Register ServiceHistory to DB.
     * 
     * @param serviceInOutForm
     * @param accountId
     */
    void saveService(ServiceInOutForm serviceInOutForm, String accountId);
}
