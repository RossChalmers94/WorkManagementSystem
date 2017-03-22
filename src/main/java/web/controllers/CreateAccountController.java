package web.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.User;
import web.domain.User.createAccount;
import web.service.UserService;







@Controller
public class CreateAccountController
{
    private UserService userService;

    @Autowired
    public CreateAccountController(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(path={"/newaccount"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String viewCreateAccountPage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "createaccount";
    }


    @RequestMapping(path={"/newaccount"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String addUser(@Validated({User.createAccount.class}) @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session)
    {
        boolean check = userService.checkUsername(newUser.getUsername());

        if (!result.hasErrors()) {
            if (!check) {
                userService.insertUser(newUser);
                session.setAttribute("currentUser", newUser);
                return "redirect:/user/personaldetails";
            }
            model.addAttribute("exists", Boolean.valueOf(true));
        }
        else {
            model.addAttribute("error", Boolean.valueOf(true));
            return "createaccount";
        }

        return "createaccount";
    }
}