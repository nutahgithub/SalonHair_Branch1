package com.hairteen.hung.web.service;

import java.util.List;

import com.hairteen.hung.web.entity.Chair;

public interface ChairService {

    /**
     * Get list all Chair.
     * 
     * @return list Chair.
     */
    List<Chair> getAllChair();

    /**
     * Get group chair in row.
     * 
     * @return
     */
    List<List<Chair>> getGroupChair();

    /**
     * Get chair by id.
     * 
     * @param idChair
     * @return
     */
    Chair getChairById(Integer idChair);

    /**
     * Update status chair.
     * 
     * @param idChair
     * @param accountId
     */
    void updateStatusChair(Integer idChair, String accountId);
}
