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
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login", method= RequestMethod.GET)
    public String viewEmployerPreferences(){
        return "login";
    }
    @RequestMapping(path = "/login", method= RequestMethod.POST)
    public String confirmEmployerPreferences(){
        return "login";
    }

}
