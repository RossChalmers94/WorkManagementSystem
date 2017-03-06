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
import web.service.UserService;
import javax.servlet.http.HttpSession;
import web.domain.User.*;

/**
 * Created by RossChalmers on 06/02/2017.
 */


@Controller
public class CreateAccountController {


    private UserService userService;

    @Autowired
    public CreateAccountController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/newaccount", method = RequestMethod.GET)
    public String viewPage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "createaccount";
    }

    @RequestMapping(path = "/newaccount", method = RequestMethod.POST)
    public String getUser(@Validated({createAccount.class}) @ModelAttribute("newUser") User newUser, BindingResult result,
                          Model model, HttpSession session){

        boolean check = userService.checkUsername(newUser.getUsername());

        if(!result.hasErrors()) {
            if (!check) {
                userService.insertUser(newUser);
                session.setAttribute("currentUser", newUser);
                return "redirect:/user/personal";
            } else {
                model.addAttribute("exists", true);
            }
        } else {
            model.addAttribute("error", true);
            return "createaccount";
        }

        return "createaccount";

    }

}

