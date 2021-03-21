package com.hairteen.hung.web.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.Bill;
import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.respository.BillRespository;
import com.hairteen.hung.web.respository.BillRespositoryCustom;
import com.hairteen.hung.web.service.BillService;
import com.hairteen.hung.web.service.ChairService;
import com.hairteen.hung.web.utils.ConstantDefine;
import com.hairteen.hung.web.utils.DateUtils;

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

    @Override
    public void payBill(Integer idChair, Integer discount, Float totalPrice, String accountId) {
        Bill billPay = billRespositoryCustom.getBillByChair(idChair);
        Date currentDate = new Date();
        billPay.setDisCount(discount);
        billPay.setTotalMoney(totalPrice);
        billPay.setStatusBill(1);
        billPay.setUpdateTsamp(currentDate);
        billPay.setDateCheckOut(currentDate);
        billPay.setUpdateId(accountId);
        chairService.updateStatusChair(idChair, accountId);
    }

    @Override
    public List<Bill> getBillByDatePaging(String dateFrom, String dateTo, int page) {
        page = page * ConstantDefine.PAGINATION_REVENUE;
        Date dateFromD = this.getDateTimeFromCompare(DateUtils.convertStringToDate(dateFrom, "yyyy-MM-dd"));
        Date dateToD = this.getDateTimeToCompare(DateUtils.convertStringToDate(dateTo, "yyyy-MM-dd"));
        return billRespositoryCustom.getBillByDatePaging(dateFromD,dateToD,page);
    }

    @Override
    public List<Bill> getAllBillByDate(String dateFrom, String dateTo) {
        Date dateFromD = this.getDateTimeFromCompare(DateUtils.convertStringToDate(dateFrom, "yyyy-MM-dd"));
        Date dateToD = this.getDateTimeToCompare(DateUtils.convertStringToDate(dateTo, "yyyy-MM-dd"));
        return billRespositoryCustom.getAllBillByDate(dateFromD,dateToD);
    }

    private Date getDateTimeFromCompare(Date date) {
        Calendar cFrom = Calendar.getInstance();
        cFrom.setTime(date); /* today */
        cFrom.set(Calendar.HOUR_OF_DAY, 0);
        cFrom.set(Calendar.MINUTE, 0);
        cFrom.set(Calendar.SECOND, 0);
        cFrom.set(Calendar.MILLISECOND, 0);
        Date from = new Date(cFrom.getTime().getTime());
        return from;
    }

    private Date getDateTimeToCompare(Date date) {
        Calendar cTo = Calendar.getInstance();
        cTo.setTime(date); /* today */
        cTo.set(Calendar.HOUR_OF_DAY, 23);
        cTo.set(Calendar.MINUTE, 59);
        cTo.set(Calendar.SECOND, 59);
        cTo.set(Calendar.MILLISECOND, 999);
        Date to = new Date(cTo.getTime().getTime());
        return to;
    }

}
