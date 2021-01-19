package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.BillInfo;

@Repository
public interface BillInfoRespository extends JpaRepository<BillInfo, Integer>{

    /**
     * Get max id bill info.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(bi.id), 0) FROM BillInfo bi")
    Integer getMaxId();

    /**
     * Get list all bill info.
     * 
     */
    @Query("SELECT bi from BillInfo bi where bi.deleteTsamp IS NULL")
    List<BillInfo> findAll();
}
