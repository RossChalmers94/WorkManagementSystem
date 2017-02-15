package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.UserService;
import org.springframework.ui.Model;
import web.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class UserHomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/userhome", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model){
        return "userhome";
    }

}
