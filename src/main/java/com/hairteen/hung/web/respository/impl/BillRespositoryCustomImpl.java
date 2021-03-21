package com.hairteen.hung.web.respository.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hairteen.hung.web.entity.Bill;
import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.respository.BillRespositoryCustom;
import com.hairteen.hung.web.utils.ConstantDefine;

@Repository
@Transactional
public class BillRespositoryCustomImpl implements BillRespositoryCustom{

    /* SQl get bill by chair */
    private final StringBuilder SQL_FIND_BILL_ACTIVE_BY_CHAIR = new StringBuilder()
            .append("Select ")
            .append(    "b ")
            .append("from ")
            .append(    Bill.class.getName()).append(" b, ")
            .append(    Chair.class.getName()).append(" ch ")
            .append("Where ")
            .append(    "b.chair.idChair = ch.idChair ")
            .append(    "and b.chair.idChair = :idChair ")
            .append(    "and b.statusBill = :statusBill ")
            .append(    "and b.deleteTsamp IS NULL ")
            .append(    "and ch.deleteTsamp IS NULL ")
            .append("ORDER BY b.dateCheckOut ");

    /* SQl get list bill by date */
    private final StringBuilder SQL_FIND_LIST_BILL_BY_DATE = new StringBuilder()
            .append("Select ")
            .append(    "b ")
            .append("from ")
            .append(    Bill.class.getName()).append(" b ")
            .append("Where ")
            .append(    "b.statusBill = '1' ")
            .append(    "and b.dateCheckOut >= :dateFrom ")
            .append(    "and b.dateCheckOut <= :dateTo ")
            .append(    "and b.deleteTsamp IS NULL ")
            .append("ORDER BY b.dateCheckOut ");

    @Autowired
    private EntityManager entityManager;

    @Override
    public Bill getBillByChair(Integer idChair) {
        StringBuilder sql = this.SQL_FIND_BILL_ACTIVE_BY_CHAIR;
        Bill bill;
        try {
            Query query = entityManager.createQuery(sql.toString(), Bill.class);
            query.setParameter("idChair", idChair);
            query.setParameter("statusBill", 0);

            bill = (Bill)query.getSingleResult();
        } catch (Exception e) {
            bill = null;
        }
        return bill;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bill> getBillByDatePaging(Date dateFrom, Date dateTo, int page) {
        StringBuilder sql = this.SQL_FIND_LIST_BILL_BY_DATE;

        Query query = entityManager.createQuery(sql.toString(), Bill.class)
                .setFirstResult(page).setMaxResults(ConstantDefine.PAGINATION_SERVICE_HISTORY);

        if (dateFrom != null && dateTo != null) {
            query.setParameter("dateFrom", dateFrom);
            query.setParameter("dateTo", dateTo);
        }

        List<Bill> bills = query.getResultList();
        return bills;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bill> getAllBillByDate(Date dateFrom, Date dateTo) {
        StringBuilder sql = this.SQL_FIND_LIST_BILL_BY_DATE;

        Query query = entityManager.createQuery(sql.toString(), Bill.class);

        if (dateFrom != null && dateTo != null) {
            query.setParameter("dateFrom", dateFrom);
            query.setParameter("dateTo", dateTo);
        }

        List<Bill> bills = query.getResultList();
        return bills;
    }

}
