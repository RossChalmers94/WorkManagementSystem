package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import web.domain.User;
import web.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 06/02/2017.
 */

@Controller
public class PersonalDetailsController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user/personal", method = RequestMethod.GET)
    public String viewEmployer(Model model, HttpSession session){
        String page = "";
        model.addAttribute("userPersonal", new User());
        return "/user/personal";
    }

    @RequestMapping(path = "/user/personal", method = RequestMethod.POST)
    public String confirmEmployer(@ModelAttribute("userPersonal") User userPersonal, Model model, HttpSession session) {
        // Use session variable to get username
        User user = (User) session.getAttribute("currentUser");
        String username = user.getUsername();

        // Add userPersonal object to users table where username is correct
        userService.insertUserPersonal(userPersonal, username);
        return "redirect:/user/preferences";
    }

}

