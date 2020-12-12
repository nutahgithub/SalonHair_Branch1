package com.hairteen.hung.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hairteen.hung.web.entity.Service;
import com.hairteen.hung.web.form.ServiceEditForm;
import com.hairteen.hung.web.form.ServiceForm;
import com.hairteen.hung.web.form.ServiceInOutForm;

public interface ServiceService {

    /**
     * Get list Service by page.
     * 
     * @param page
     * @return Page<Service>
     */
    Page<Service> getServicePage(int page);

    /**
     * Get list all Service.
     * 
     * @return list Service.
     */
    List<Service> getAllService();

    /**
     * Get Service by ID.
     * 
     * @param id
     * @return Service
     */
    Service getOneServiceByID(Integer id);

    /**
     * Register Service to DB.
     * 
     * @param serviceForm
     * @param accountId
     */
    void saveService(ServiceForm serviceForm, String accountId);

    /**
     * Update Service to DB.
     * 
     * @param ServiceForm
     * @param accountId
     */
    void editService(ServiceEditForm serviceEditForm, String accountId);

    /**
     * Delete logic Service ( not delete physic).
     * 
     * @param id
     * @param accountId
     */
    void deleteService(Integer id, String accountId);

    /**
     * In out service to DB.
     * 
     * @param serviceInOutForm
     * @param accountId
     */
    void inOutService(ServiceInOutForm serviceInOutForm, String accountId);
}
