package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.ServiceHistory;

@Repository
public interface ServiceHistoryRespository extends JpaRepository<ServiceHistory, Integer>{

    /**
     * Get max id ServiceHistory.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(sh.id), 0) FROM ServiceHistory sh")
    Integer getMaxId();

    /**
     * Find all ServiceHistory page.
     * 
     */
    @Query("SELECT sh "
            + "from ServiceHistory sh, ServiceType st, Service sv "
            + "where "
            + "sh.service.idService = sv.idService "
            + "and sv.serviceType.idServiceType = st.idServiceType "
            + "and sv.deleteTsamp IS NULL "
            + "and st.deleteTsamp IS NULL "
            + "and sh.deleteTsamp IS NULL")
    Page<ServiceHistory> findAll(Pageable pageable);

    /**
     * Get list all ServiceHistory.
     * 
     */
    @Query("SELECT sh "
            + "from ServiceHistory sh, ServiceType st, Service sv "
            + "where "
            + "sh.service.idService = sv.idService "
            + "and sv.serviceType.idServiceType = st.idServiceType "
            + "and sv.deleteTsamp IS NULL "
            + "and st.deleteTsamp IS NULL "
            + "and sh.deleteTsamp IS NULL")
    List<ServiceHistory> findAll();
}
