package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.BillInfo;

@Repository
public interface BillInfoRespositoryCustom {

    /**
     * Get all bill info by bill
     * 
     * @param idChair
     * @return
     */
    List<BillInfo> getAllBillInfoByBill(Integer idChair);

    /**
     * Get bill info exist.
     * 
     * @param idChair
     * @param idService
     * @param idEmployee
     * @return
     */
    BillInfo getExistBillInfo(Integer idChair, Integer idService, Integer idEmployee);
}
