package com.hairteen.hung.web.service;

import java.util.List;

import com.hairteen.hung.web.entity.BillInfo;

public interface BillInfoService {

    List<BillInfo> getAllBillInfoByBill(Integer idChair);

    Float totalPrice(Integer idChair);
    
    BillInfo addBillInfoToBill(Integer idChair, String accountId, Integer idService, Integer idEmployee, Integer countService);
}
