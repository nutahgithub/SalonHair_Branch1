package com.hairteen.hung.web.controller.useservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hairteen.hung.web.entity.Chair;
import com.hairteen.hung.web.service.ChairService;

@Controller
public class UseServiceController {

    @Autowired
    private ChairService chairService;

    /**
     * Init use service screen.
     * @param model
     * @return
     */
    @RequestMapping(value = "/use_service_view", method = RequestMethod.GET)
    public String useServiceView(Model model) {
        List<List<Chair>> chairGroup = chairService.getGroupChair();
        model.addAttribute("chairGroup", chairGroup);
        return "use_service";
    }
}
