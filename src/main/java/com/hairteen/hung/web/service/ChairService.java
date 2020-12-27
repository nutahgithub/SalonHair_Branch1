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
}
