package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.enumconstants.UserDetails;
import web.service.UserService;
import org.springframework.ui.Model;
import web.domain.application.*;
import web.domain.*;
import java.util.*;

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

        String page = "login";
        boolean login = userService.checkUserLogIn(loginUser);
        if(login){
            User currentUser = userService.getLogIn(loginUser);
            session.setAttribute("currentUser", currentUser);
            page = "redirect:/user/userhome";
        }
        else if(loginUser.getUsername().equals("admin")) {
            boolean adminLogIn = userService.checkAdminLogIn(loginUser);
            if (adminLogIn) {
                Admin admin = userService.getAdminLogIn(loginUser);
                session.setAttribute("adminUser", admin);
                page = "redirect:/admin/adminhome";
            }
        }

        return page;
    }

    @RequestMapping(path = "/logoutuser", method= RequestMethod.GET)
    public String logOut(Model model, HttpSession session){
        session.removeAttribute("currentUser");
        session.removeAttribute("adminUser");
        return "redirect:/home";
    }



}
