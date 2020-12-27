package com.hairteen.hung.web.service.impl;

import java.util.ArrayList;
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

}
