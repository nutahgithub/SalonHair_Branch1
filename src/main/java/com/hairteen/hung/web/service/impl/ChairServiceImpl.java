package com.hairteen.hung.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.respository.ChairRespository;
import com.hairteen.hung.web.service.ChairService;
import com.hairteen.hung.web.utils.ConstantDefine;

@Service
public class ChairServiceImpl implements ChairService{

    @Autowired
    private ChairRespository chairRespository;

    /**
     * Chair not empty.
     */
    private static final Integer STATUS_CHAIR_ACTIVE = 1;

    /**
     * Chair is empty.
     */
    private static final Integer STATUS_CHAIR_INACTIVE = 0;

    @Override
    public List<Chair> getAllChair() {
        return chairRespository.findAll();
    }

    @Override
    public List<List<Chair>> getGroupChair() {
        List<List<Chair>> chairGroup = new ArrayList<List<Chair>>();
        List<Chair> totalChair = chairRespository.findAll();
        List<Chair> gChair = new ArrayList<Chair>();
        
        // Get number row chair.
        int numberGroup = totalChair.size();
        if (numberGroup%ConstantDefine.NUMBER_CHAIR_ON_ROW == 0) {
            numberGroup = numberGroup/ConstantDefine.NUMBER_CHAIR_ON_ROW;
        } else {
            numberGroup = numberGroup/ConstantDefine.NUMBER_CHAIR_ON_ROW + 1;
        }

        for (int i = 0; i < totalChair.size(); i++) {
            // 1 Row have 3 chair
            if (i%ConstantDefine.NUMBER_CHAIR_ON_ROW == 0 && i != 0) {
                chairGroup.add(gChair);
                gChair = new ArrayList<Chair>();
            }

            gChair.add(totalChair.get(i));

            // Row chair END.
            if (i == totalChair.size()-1) {
                chairGroup.add(gChair);
            }
        }
        return chairGroup;
    }

    @Override
    public Chair getChairById(Integer idChair) {
        return chairRespository.findById(idChair).get();
    }

    @Override
    public void updateStatusChair(Integer idChair, String accountId) {
        Chair chair = this.getChairById(idChair);
        // Check status chair.
        if (chair.getStatusChair() == STATUS_CHAIR_INACTIVE) {
            chair.setStatusChair(STATUS_CHAIR_ACTIVE);
        } else {
            chair.setStatusChair(STATUS_CHAIR_INACTIVE);
        }
        chair.setUpdateTsamp(new Date());
        chair.setUpdateId(accountId);
        chairRespository.save(chair);
    }

}
