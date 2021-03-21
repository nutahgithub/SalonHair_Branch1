package com.hairteen.hung.web.respository;

import java.util.Date;
import java.util.List;

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
    
    List<Bill> getBillByDatePaging(Date dateFrom, Date dateTo, int page);
    
    List<Bill> getAllBillByDate(Date dateFrom, Date dateTo);
}
