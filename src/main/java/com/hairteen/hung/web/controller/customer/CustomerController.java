package com.hairteen.hung.web.controller.customer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hairteen.hung.web.entity.Customer;
import com.hairteen.hung.web.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService CustomerService;

    /**
     * Init manager Customer screen.
     * @param model
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/manager_customer_view", method = RequestMethod.GET)
    public String customerView(Model model, @RequestParam(required = false) String pageId) {

        Integer pageIdInt;

        // Check page id is number
        try {
            pageIdInt = Integer.parseInt(pageId);
        } catch(NumberFormatException e) {
            pageIdInt = 1;
        }

        // Get list Customer pages
        Page<Customer> customerPages = CustomerService.getCustomerPage(pageIdInt);
        int totalPages = customerPages.getTotalPages();

        if (totalPages > 0) {
            // Create List page
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        //model.addAttribute("CustomerForm", new CustomerForm());
        //model.addAttribute("CustomerEditForm", new CustomerEditForm());
        model.addAttribute("customerPages", customerPages);
        return "customer";
    }
}
