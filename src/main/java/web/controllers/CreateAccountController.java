package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import web.domain.repository.UserRepository;
import web.domain.User;
import web.service.UserService;

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
    public String getUser(@ModelAttribute("newUser") User newUser, Model model){

        String role = newUser.getRole();
        String page = "";

        int id = userService.getUserID(newUser);
        model.addAttribute("id", id);
        /*if(id > 0) {
            userService.addUser(newUser);
            if(role.equals("freelancer")){
                page = "redirect:/freelancer";
            } else if(role.equals("employer")){
                page = "redirect:/employer";
            }
        }
        else {
            page = "createaccount";
        }*/

        return "createaccount";

    }

}

