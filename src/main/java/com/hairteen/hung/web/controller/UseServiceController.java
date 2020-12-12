package com.hairteen.hung.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UseServiceController {

    /**
     * Init use service screen.
     * @param model
     * @return
     */
    @RequestMapping(value = "/use_service_view", method = RequestMethod.GET)
    public String useServiceView(Model model) {
        return "use_service";
    }
}
