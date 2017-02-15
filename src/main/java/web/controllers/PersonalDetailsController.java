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
    public String viewFreelancer() {
        return "freelancer";
    }

    @RequestMapping(path = "/freelancer", method = RequestMethod.POST)
    public String confirmFreelancer() {
        return "freelancerpreferences";
    }

    @RequestMapping(path = "/employer", method = RequestMethod.GET)
    public String viewEmployer(Model model, HttpSession session){
        User user = new User();
        model.addAttribute("userPersonal", user);
        return "employer";
    }

    @RequestMapping(path = "/employer", method = RequestMethod.POST)
    public String confirmEmployer(@ModelAttribute("userPersonal") User userPersonal, Model model, HttpSession session) {
        model.addAttribute("userPersonal", userPersonal);
        User user = (User) session.getAttribute("currentUser");
        String username = user.getUsername();
        userService.insertUserPersonal(userPersonal, username);
        return "employerpreferences";
    }

}

