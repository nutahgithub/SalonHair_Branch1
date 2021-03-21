package com.hairteen.hung.web.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hairteen.hung.web.entity.Customer;

public interface CustomerService {

    /**
     * Get list Customer by page.
     * 
     * @param page
     * @return Page<Customer>
     */
    Page<Customer> getCustomerPage(int page);

    /**
     * Get list all Customer.
     * 
     * @return list Customer.
     */
    List<Customer> getAllCustomer();
}
