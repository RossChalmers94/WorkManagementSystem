package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.enumconstants.UserDetails;
import web.service.UserService;
import org.springframework.ui.Model;
import web.domain.User;
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

        String page = "";
        boolean login = userService.checkUserLogIn(loginUser);
        if(login){
            Map<String, Object> out = userService.getLogIn(loginUser);
            User currentUser = new User();
            currentUser.setUsername(loginUser.getUsername());
            currentUser.setPassword(loginUser.getPassword());
            currentUser.setFirstname((String) out.get(UserDetails.USER_FIRSTNAME.getValue()));
            currentUser.setLastname((String) out.get(UserDetails.USER_LASTNAME.getValue()));
            currentUser.setTelephone((String) out.get(UserDetails.USER_TELEPHONE.getValue()));
            currentUser.setEmailaddress((String) out.get(UserDetails.USER_EMAILADDRESS.getValue()));
            currentUser.setAddress((String) out.get(UserDetails.USER_ADDRESS.getValue()));
            currentUser.setPostcode((String) out.get(UserDetails.USER_POSTCODE.getValue()));
            currentUser.setTowncity((String) out.get(UserDetails.USER_TOWNCITY.getValue()));
            currentUser.setRole((String) out.get(UserDetails.USER_ROLE.getValue()));
            currentUser.setCompany((String) out.get(UserDetails.USER_COMPANY.getValue()));
            session.setAttribute("currentUser", currentUser);
            page = "user/userhome";
        }
        else {
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
