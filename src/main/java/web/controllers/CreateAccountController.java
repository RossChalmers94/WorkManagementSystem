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
@RequestMapping("/createaccount")
public class CreateAccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewPage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "createaccount";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getUser(@ModelAttribute("newUser") User newUser, Model model, HttpSession session){

        String role = newUser.getRole();
        String page = "";
        int value = userService.checkUser(newUser);
        model.addAttribute("value", value);

        if(value == 0) {
            userService.insertUser(newUser);
            session.setAttribute("currentUser", newUser);
            page = "personal";
        } else {
            page = "createaccount";
        }

        return page;

    }

}

