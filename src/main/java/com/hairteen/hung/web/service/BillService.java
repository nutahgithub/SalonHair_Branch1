package com.hairteen.hung.web.service;

public interface BillService {

    /**
     * Switch chair for bill.
     * 
     * @param idChairOld
     * @param idChairNew
     * @param accountId
     */
    void switchChairForBill(Integer idChairOld, Integer idChairNew, String accountId);
}
