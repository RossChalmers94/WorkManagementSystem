package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.PreferencesService;
import web.service.UserService;
import web.domain.application.Application;
import web.domain.application.Salary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class AdminController {

    @Autowired
    private PreferencesService preferencesService;

    @RequestMapping(path = "/admin/adminhome", method= RequestMethod.GET)
    public String viewAdminHome(Model model){
        return "admin/adminhome";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.GET)
    public String viewConfigureApplication(Model model){
        Application newApplication = new Application();
        model.addAttribute("newApplication", newApplication);
        Map<String, String> skills = preferencesService.getSkills();
        Map<String, String> locations = preferencesService.getLocations();
        model.addAttribute("skills", skills);
        model.addAttribute("locations", locations);
        return "admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST)
    public String changeConfigureApplication(Model model){
        Application newApplication = new Application();
        model.addAttribute("newApplication", newApplication);
        return "admin/configureapplication";
    }

    @RequestMapping(path = "admin/manageusers", method = RequestMethod.GET)
    public String viewManageUsers(Model model){
        return "admin/manageusers";
    }

}
