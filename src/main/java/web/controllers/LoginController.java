package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.enumconstants.UserDetails;
import web.service.UserService;
import org.springframework.ui.Model;
import web.domain.application.*;
import web.domain.*;
import web.domain.User.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

/*    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
        binder.setDisallowedFields("role");
    }*/

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
    public String confirmEmployerPreferences(@Validated({logIn.class}) @ModelAttribute("loginUser") User loginUser, BindingResult result,
                                             Model model, HttpSession session){

        if(!result.hasErrors()) {
            boolean login = userService.checkUserLogIn(loginUser);
            if (login) {
                User currentUser = userService.getLogIn(loginUser);
                session.setAttribute("currentUser", currentUser);
                return "redirect:/user/userhome";
            } else if (loginUser.getUsername().equals("admin")) {
                boolean adminLogIn = userService.checkAdminLogIn(loginUser);
                if (adminLogIn) {
                    Admin admin = userService.getAdminLogIn(loginUser);
                    session.setAttribute("adminUser", admin);
                    return "redirect:/admin/adminhome";
                }
            } else {
                model.addAttribute("error", true);
            }
        } else {
            model.addAttribute("details", true);
        }

        return "login";
    }

    @RequestMapping(path = "/logoutuser", method= RequestMethod.GET)
    public String logOut(Model model, HttpSession session){
        session.removeAttribute("currentUser");
        session.removeAttribute("adminUser");
        return "redirect:/home";
    }



}
