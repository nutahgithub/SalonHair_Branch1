package com.hairteen.hung.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.Bill;
import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.respository.BillRespository;
import com.hairteen.hung.web.respository.BillRespositoryCustom;
import com.hairteen.hung.web.service.BillService;
import com.hairteen.hung.web.service.ChairService;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRespositoryCustom billRespositoryCustom;

    @Autowired
    private BillRespository billRespository;

    @Autowired
    private ChairService chairService;

    @Override
    public void switchChairForBill(Integer idChairOld, Integer idChairNew, String accountId) {
        Bill billOld = billRespositoryCustom.getBillByChair(idChairOld);
        Bill billNew = billRespositoryCustom.getBillByChair(idChairNew);
        if (billOld == null && billNew != null) {
            chairService.updateStatusChair(idChairOld, accountId);
            chairService.updateStatusChair(idChairNew, accountId);
            this.updateBillByChair(billNew, idChairOld, accountId);
        } else if (billOld != null && billNew == null) {
            chairService.updateStatusChair(idChairOld, accountId);
            chairService.updateStatusChair(idChairNew, accountId);
            this.updateBillByChair(billOld, idChairNew, accountId);
        } else if (billOld != null && billNew != null) {
            this.updateBillByChair(billNew, idChairOld, accountId);
            this.updateBillByChair(billOld, idChairNew, accountId);
        }
    }

    /**
     * Update bill by chair.
     * 
     * @param bill
     * @param idChair
     * @param accountId
     */
    private void updateBillByChair(Bill bill, Integer idChair, String accountId) {
        Chair chair = new Chair();
        chair.setIdChair(idChair);
        bill.setChair(chair);
        bill.setUpdateTsamp(new Date());
        bill.setUpdateId(accountId);
        billRespository.save(bill);
    }
}
