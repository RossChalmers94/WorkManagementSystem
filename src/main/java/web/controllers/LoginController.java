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
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session){
        String page = "";
        if(session.getAttribute("currentUser") != null){
            page = "userhome";
        } else
        {
            User user = new User();
            model.addAttribute("loginUser", user);
            page = "login";
        }

        return page;
    }
    @RequestMapping(path = "/login", method= RequestMethod.POST)
    public String confirmEmployerPreferences(@ModelAttribute("loginUser") User loginUser, Model model, HttpSession session){
        boolean login = false;
        String page = "";
        login = userService.getLogIn(loginUser);
        if(login){
            page = "userhome";
            session.setAttribute("currentUser", loginUser);
        }
        else{
            page = "login";
        }
        return page;
    }

    @RequestMapping(path = "/logoutuser", method= RequestMethod.GET)
    public String logOut(Model model, HttpSession session){
        session.removeAttribute("currentUser");
        return "redirect:/home";
    }



}
