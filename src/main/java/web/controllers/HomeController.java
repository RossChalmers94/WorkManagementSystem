package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import web.service.UserService;

/**
 * Created by RossChalmers on 06/02/2017.
 */

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public String viewPage(Model model) {
        model.addAttribute("industryName", userService.getIndustryName());
        return "home";
    }

}
