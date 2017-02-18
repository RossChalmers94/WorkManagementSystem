package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import web.domain.User;
import web.service.UserService;
import web.domain.Employer;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 06/02/2017.
 */

@Controller
public class PersonalDetailsController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/freelancer", method = RequestMethod.GET)
    public String viewFreelancer(Model model, HttpSession session) {
        String page = "";
        User user = new User();
        model.addAttribute("userPersonal", user);
        return "freelancer";
    }

    @RequestMapping(path = "/freelancer", method = RequestMethod.POST)
    public String confirmFreelancer(@ModelAttribute("userPersonal") User userPersonal, Model model, HttpSession session) {
        model.addAttribute("userPersonal", userPersonal);
        User user = (User) session.getAttribute("currentUser");
        userService.insertUserPersonal(userPersonal, user.getUsername());
        return "freelancerpreferences";
    }

    @RequestMapping(path = "/personal", method = RequestMethod.GET)
    public String viewEmployer(Model model, HttpSession session){
        String page = "";
        User user = new User();
        model.addAttribute("userPersonal", user);
        return "personal";
    }

    @RequestMapping(path = "/personal", method = RequestMethod.POST)
    public String confirmEmployer(@ModelAttribute("userPersonal") User userPersonal, Model model, HttpSession session) {
        model.addAttribute("userPersonal", userPersonal);
        User user = (User) session.getAttribute("currentUser");
        String username = user.getUsername();
        userService.insertUserPersonal(userPersonal, username);
        return "preferences";
    }

}

