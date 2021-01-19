package com.hairteen.hung.web.respository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hairteen.hung.web.entity.Service;
import com.hairteen.hung.web.entity.ServiceHistory;
import com.hairteen.hung.web.entity.ServiceType;
import com.hairteen.hung.web.respository.ServiceHistoryRespositoryCustom;
import com.hairteen.hung.web.utils.ConstantDefine;

@Repository
@Transactional
public class ServiceHistoryRespositoryCustomImpl implements ServiceHistoryRespositoryCustom{

    /* Condition sql delete_tstamp is null */
    //SSprivate final String DELETE_TSTAMP_IS_NULL = " and ac.deleteTsamp IS NULL";

    /* SQl get account by userName */
    private final StringBuilder SQL_FIND_SERVICE_HISTORY = new StringBuilder()
            .append("Select ")
            .append(    "sh ")
            .append("from ")
            .append(    Service.class.getName()).append(" sv, ")
            .append(    ServiceHistory.class.getName()).append(" sh, ")
            .append(    ServiceType.class.getName()).append(" st ")
            .append("Where ")
            .append(    "sv.idService = sh.service.idService ")
            .append(    "and sv.serviceType.idServiceType = st.idServiceType ")
            .append(    "and sv.deleteTsamp IS NULL ")
            .append(    "and st.deleteTsamp IS NULL ")
            .append(    "and sh.deleteTsamp IS NULL ");

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<ServiceHistory> findServiceHistoryByMonthAndServicePage(Integer year, Integer month, Integer serviceId, int page) {
        StringBuilder sql = this.SQL_FIND_SERVICE_HISTORY;
        if (year != null && month != null) {
            sql = sql.append("and year(sh.registerTsamp) = :year ")
                    .append("and month(sh.registerTsamp) = :month ");
        }

        Query query = entityManager.createQuery(sql.toString(), ServiceHistory.class)
                .setFirstResult(page).setMaxResults(ConstantDefine.PAGINATION_SERVICE_HISTORY);
        if (year != null && month != null) {
            query.setParameter("year", year);
            query.setParameter("month", month);
        }

        List<ServiceHistory> serviceHistories = query.getResultList();
        return serviceHistories;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ServiceHistory> getAllServiceHistoryByMonthAndService(Integer year, Integer month, Integer serviceId) {
        StringBuilder sql = this.SQL_FIND_SERVICE_HISTORY;
        if (year != null && month != null) {
            sql = sql.append("and year(sh.registerTsamp) = :year ")
                    .append("and month(sh.registerTsamp) = :month ");
        }
        Query query = entityManager.createQuery(sql.toString(), ServiceHistory.class);
        if (year != null && month != null) {
            query.setParameter("year", year);
            query.setParameter("month", month);
        }
        List<ServiceHistory> serviceHistories = query.getResultList();
        return serviceHistories;
    }

}
