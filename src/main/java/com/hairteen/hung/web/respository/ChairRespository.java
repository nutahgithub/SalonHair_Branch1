package com.hairteen.hung.web.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hairteen.hung.web.entity.Chair;

@Repository
public interface ChairRespository extends JpaRepository<Chair, Integer>{

    /**
     * Get max id chair.
     * 
     * @return Integer
     */
    @Query("SELECT coalesce(max(c.id), 0) FROM Chair c")
    Integer getMaxId();

    /**
     * Get list all chair.
     * 
     */
    @Query("SELECT c from Chair c where c.deleteTsamp IS NULL")
    List<Chair> findAll();
}
