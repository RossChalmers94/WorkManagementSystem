package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.UserService;




@Controller
@RequestMapping({"/home"})
public class HomeController
{
    @Autowired
    private UserService userService;

    public HomeController() {}

    @RequestMapping(method = RequestMethod.GET)
    public String viewPage(Model model)
    {
        model.addAttribute("industryName", userService.getIndustryName());
        return "home";
    }
}