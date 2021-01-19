package com.hairteen.hung.web.respository;

import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.Bill;

@Repository
public interface BillRespositoryCustom {

    /**
     * Get bill by chair.
     * 
     * @param idChair
     * @return Bill
     */
    Bill getBillByChair(Integer idChair);
}
