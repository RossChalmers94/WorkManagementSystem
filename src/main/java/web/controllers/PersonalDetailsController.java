package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import web.domain.User;
import web.domain.User.*;
import web.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 06/02/2017.
 */

@Controller
public class PersonalDetailsController {


    private UserService userService;

    @Autowired
    public PersonalDetailsController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/user/personaldetails", method = RequestMethod.GET)
    public String viewPersonal(Model model, HttpSession session){
        if(session.getAttribute("currentUser") != null) {
            User user = (User) session.getAttribute("currentUser");
            User newUser = userService.getLogIn(user);
            model.addAttribute("userPersonal", newUser);
            return "/user/personal";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(path = "/user/personaldetails", method = RequestMethod.POST, params = "employer")
    public String confirmPersonalEmployer(@Validated({personalDetailsEmployer.class})@ModelAttribute("userPersonal")
                                              User userPersonal, BindingResult result, Model model, HttpSession session) {
        // Use session variable to get username
        User user = (User) session.getAttribute("currentUser");
        String username = user.getUsername();

        return enterPersonalDetails(userPersonal, username, result, model, session);
    }

    @RequestMapping(path = "/user/personaldetails", method = RequestMethod.POST, params = "freelancer")
    public String confirmPersonalFreelancer(@Validated({personalDetailsFreelancer.class})@ModelAttribute("userPersonal")
                                          User userPersonal, BindingResult result, Model model, HttpSession session) {
        // Use session variable to get username
        User user = (User) session.getAttribute("currentUser");
        String username = user.getUsername();

        return enterPersonalDetails(userPersonal, username, result, model, session);

    }

    private String enterPersonalDetails(User user, String username, BindingResult result, Model model, HttpSession session){

        if(!result.hasErrors()){
            userService.insertUserPersonal(user, username);
            return "redirect:/user/preferences";
        } else {
            model.addAttribute("error", true);
            return "user/personal";
        }
    }

}

