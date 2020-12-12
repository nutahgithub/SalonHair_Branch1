package com.hairteen.hung.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hairteen.hung.web.entity.ServiceHistory;
import com.hairteen.hung.web.form.ServiceInOutForm;

public interface ServiceHistoryService {

    /**
     * Get list ServiceHistory by page.
     * 
     * @param page
     * @return Page<ServiceHistory>
     */
    Page<ServiceHistory> getServicePage(int page);

    /**
     * Get list all ServiceHistory.
     * 
     * @return list ServiceHistory.
     */
    List<ServiceHistory> getAllService();

    /**
     * Get Service by ID.
     * 
     * @param id
     * @return ServiceHistory
     */
    ServiceHistory getOneServiceByID(Integer id);

    /**
     * Register ServiceHistory to DB.
     * 
     * @param serviceInOutForm
     * @param accountId
     */
    void saveService(ServiceInOutForm serviceInOutForm, String accountId);
}
