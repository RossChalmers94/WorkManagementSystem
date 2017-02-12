package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.UserService;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class PreferencesController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/employerpreferences", method= RequestMethod.GET)
    public String viewEmployerPreferences(){
        return "employerpreferences";
    }
    @RequestMapping(path = "/employerpreferences", method= RequestMethod.POST)
    public String confirmEmployerPreferences(){
        return "employerpreferences";
    }
    @RequestMapping(path = "/freelancerpreferences", method= RequestMethod.GET)
    public String viewFreelancerPreferences(){
        return "freelancerpreferences";
    }
    @RequestMapping(path = "/freelancerpreferences", method= RequestMethod.POST)
    public String confirmFreelancerPreferences(){
        return "freelancerpreferences";
    }
}
