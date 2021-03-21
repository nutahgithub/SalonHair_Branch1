package com.hairteen.hung.web.respository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hairteen.hung.web.entity.Bill;
import com.hairteen.hung.web.entity.BillInfo;
import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.entity.Employee;
import com.hairteen.hung.web.entity.Service;
import com.hairteen.hung.web.respository.BillInfoRespositoryCustom;

@Repository
@Transactional
public class BillInfoRespositoryCustomImpl implements BillInfoRespositoryCustom{

    /* SQl get bill info by bill */
    private final StringBuilder SQL_FIND_BILL_INFO_OF_BILL = new StringBuilder()
            .append("Select ")
            .append(    "bi ")
            .append("from ")
            .append(    Service.class.getName()).append(" sv, ")
            .append(    BillInfo.class.getName()).append(" bi, ")
            .append(    Bill.class.getName()).append(" b, ")
            .append(    Employee.class.getName()).append(" em, ")
            .append(    Chair.class.getName()).append(" ch ")
            .append("Where ")
            .append(    "bi.bill.idBill = b.idBill ")
            .append(    "and b.chair.idChair = ch.idChair ")
            .append(    "and b.chair.idChair = :idChair  ")
            .append(    "and b.statusBill = :statusBill ")
            .append(    "and bi.service.idService = sv.idService ")
            .append(    "and bi.employee.idEmployee = em.idEmployee ")
            .append(    "and sv.deleteTsamp IS NULL ")
            .append(    "and bi.deleteTsamp IS NULL ")
            .append(    "and b.deleteTsamp IS NULL ")
            .append(    "and em.deleteTsamp IS NULL ")
            .append(    "and ch.deleteTsamp IS NULL ")
            .append("ORDER BY bi.registerTsamp ");

    /* SQl check exist bill info */
    private final StringBuilder SQL_CHECK_EXIST_BILL_INFO = new StringBuilder()
            .append("Select ")
            .append(    "bi ")
            .append("from ")
            .append(    Service.class.getName()).append(" sv, ")
            .append(    BillInfo.class.getName()).append(" bi, ")
            .append(    Bill.class.getName()).append(" b, ")
            .append(    Employee.class.getName()).append(" em, ")
            .append(    Chair.class.getName()).append(" ch ")
            .append("Where ")
            .append(    "bi.bill.idBill = b.idBill ")
            .append(    "and b.chair.idChair = ch.idChair ")
            .append(    "and b.chair.idChair = :idChair  ")
            .append(    "and b.statusBill = :statusBill ")
            .append(    "and bi.service.idService = :idService ")
            .append(    "and bi.employee.idEmployee = :idEmployee ")
            .append(    "and bi.service.idService = sv.idService ")
            .append(    "and bi.employee.idEmployee = em.idEmployee ")
            .append(    "and sv.deleteTsamp IS NULL ")
            .append(    "and bi.deleteTsamp IS NULL ")
            .append(    "and b.deleteTsamp IS NULL ")
            .append(    "and em.deleteTsamp IS NULL ")
            .append(    "and ch.deleteTsamp IS NULL ")
            .append("ORDER BY bi.registerTsamp ");

    
    /* SQl get bill info by bill id */
    private final StringBuilder SQL_FIND_BILL_INFO_OF_BILL_ID = new StringBuilder()
            .append("Select ")
            .append(    "bi ")
            .append("from ")
            .append(    Service.class.getName()).append(" sv, ")
            .append(    BillInfo.class.getName()).append(" bi, ")
            .append(    Bill.class.getName()).append(" b, ")
            .append(    Employee.class.getName()).append(" em ")
            .append("Where ")
            .append(    "b.idBill = :idBill ")
            .append(    "and bi.bill.idBill = b.idBill ")
            .append(    "and bi.service.idService = sv.idService ")
            .append(    "and bi.employee.idEmployee = em.idEmployee ")
            .append(    "and sv.deleteTsamp IS NULL ")
            .append(    "and bi.deleteTsamp IS NULL ")
            .append(    "and b.deleteTsamp IS NULL ")
            .append(    "and em.deleteTsamp IS NULL ")
            .append("ORDER BY bi.registerTsamp ");

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<BillInfo> getAllBillInfoByBill(Integer idChair) {
        StringBuilder sql = this.SQL_FIND_BILL_INFO_OF_BILL;
        Query query = entityManager.createQuery(sql.toString(), BillInfo.class);
        query.setParameter("idChair", idChair);
        query.setParameter("statusBill", 0);

        List<BillInfo> billInfos = query.getResultList();
        return billInfos;
    }

    @Override
    public BillInfo getExistBillInfo(Integer idChair, Integer idService, Integer idEmployee) {
        StringBuilder sql = this.SQL_CHECK_EXIST_BILL_INFO;
        BillInfo billInfo;
        try {
            Query query = entityManager.createQuery(sql.toString(), BillInfo.class);
            query.setParameter("idChair", idChair);
            query.setParameter("idService", idService);
            query.setParameter("idEmployee", idEmployee);
            query.setParameter("statusBill", 0);
            billInfo = (BillInfo)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return billInfo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BillInfo> getAllBillInfoByBillId(Integer idBill) {
        StringBuilder sql = this.SQL_FIND_BILL_INFO_OF_BILL_ID;
        Query query = entityManager.createQuery(sql.toString(), BillInfo.class);
        query.setParameter("idBill", idBill);

        List<BillInfo> billInfos = query.getResultList();
        return billInfos;
    }

}
