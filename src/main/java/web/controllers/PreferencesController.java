package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.PreferencesService;
import web.service.UserService;
import org.springframework.ui.Model;
import java.util.Map;
import web.domain.Employer;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class PreferencesController {

    @Autowired
    private PreferencesService preferencesService;

    @RequestMapping(path = "/preferences", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session){
        Employer newWorker = new Employer();
        Map<String, Object> skills = preferencesService.getSkills();
        model.addAttribute("newWorker", newWorker);
        model.addAttribute("skills", skills);
        return "preferences";
    }
    @RequestMapping(path = "/preferences", method= RequestMethod.POST)
    public String confirmEmployerPreferences(){
        return "match";
    }
}
