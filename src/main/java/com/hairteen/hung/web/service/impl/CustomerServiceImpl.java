package com.hairteen.hung.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.Customer;
import com.hairteen.hung.web.respository.CustomerRespository;
import com.hairteen.hung.web.service.CustomerService;
import com.hairteen.hung.web.utils.ConstantDefine;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRespository CustomerRespository;

    @Override
    public Page<Customer> getCustomerPage(int page) {

        int totalRecord = CustomerRespository.findAll().size();
        int totalPage = totalRecord/ConstantDefine.PAGINATION_EMPLOYEE;

        if (totalRecord % ConstantDefine.PAGINATION_EMPLOYEE > 0) {
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

        Page<Customer> pages =CustomerRespository.findAll(PageRequest.of(page, ConstantDefine.PAGINATION_EMPLOYEE));
        return pages;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return (List<Customer>) CustomerRespository.findAll();
    }
}
