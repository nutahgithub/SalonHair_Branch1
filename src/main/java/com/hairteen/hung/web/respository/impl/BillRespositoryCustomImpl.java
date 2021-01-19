package com.hairteen.hung.web.respository.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hairteen.hung.web.entity.Bill;
import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.respository.BillRespositoryCustom;

@Repository
@Transactional
public class BillRespositoryCustomImpl implements BillRespositoryCustom{

    /* SQl get account by userName */
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
            .append(    "and ch.deleteTsamp IS NULL ");

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

}
