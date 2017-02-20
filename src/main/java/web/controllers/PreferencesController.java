package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.application.Application;
import web.service.PreferencesService;
import org.springframework.ui.Model;
import web.domain.*;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class PreferencesController {

    @Autowired
    private PreferencesService preferencesService;

    @RequestMapping(path = "user/preferences", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        Employer newWorker = new Employer();
        Application application = new Application();
        application.setSkills(preferencesService.getSkills());
        application.setLocations(preferencesService.getLocations());
        application.setJobLengths(preferencesService.getJobLengths());
        application.setSalarys(preferencesService.getSalarys());
        newWorker.setApplication(application);
        model.addAttribute("newWorker", newWorker);
        return "user/preferences";
    }
    @RequestMapping(path = "/user/preferences", method= RequestMethod.POST)
    public String confirmEmployerPreferences(){
        return "/user/match";
    }
}
