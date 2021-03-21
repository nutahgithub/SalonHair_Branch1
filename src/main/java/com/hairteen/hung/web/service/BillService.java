package com.hairteen.hung.web.service;

import java.util.List;

import com.hairteen.hung.web.entity.Bill;

public interface BillService {

    /**
     * Switch chair for bill.
     * 
     * @param idChairOld
     * @param idChairNew
     * @param accountId
     */
    void switchChairForBill(Integer idChairOld, Integer idChairNew, String accountId);

    /**
     * Pay bill
     * 
     * @param idChair
     * @param discount
     * @param accountId
     */
    void payBill(Integer idChair, Integer discount, Float totalPrice, String accountId);
    
    List<Bill> getBillByDatePaging(String dateFrom, String dateTo, int page);
    
    List<Bill> getAllBillByDate(String dateFrom, String dateTo);
    
}
