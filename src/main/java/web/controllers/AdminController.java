package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.PreferencesService;
import web.domain.application.Application;
import web.domain.application.Admin;

import javax.servlet.http.HttpSession;
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
    public String viewConfigureApplication(Model model, HttpSession session){
        Application newApplication = new Application();
        newApplication.setLocations(preferencesService.getLocations());
        newApplication.setAdmin(preferencesService.getAdmin());
        newApplication.setSkills(preferencesService.getSkills());
        newApplication.setSalarys(preferencesService.getSalarys());
        newApplication.setJobLengths(preferencesService.getJobLengths());
        model.addAttribute("newApplication", newApplication);
        return "admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST)
    public String changeConfigureApplication(@ModelAttribute("newApplication") Application newApplication, Model model, HttpSession session){
        preferencesService.updatePreferences(newApplication);
        return "admin/configureapplication";
    }

    @RequestMapping(path = "admin/manageusers", method = RequestMethod.GET)
    public String viewManageUsers(Model model){
        return "admin/manageusers";
    }

}
