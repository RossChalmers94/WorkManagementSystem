package web.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.User;
import web.domain.User.createAccount;
import web.service.UserService;

/**
 * This controller is responsible for handling requests for creating an account.
 *
 * @Author Ross Chalmers
 */
@Controller
public class CreateAccountController
{
    private UserService userService;

    /**
     * Configuring the service to use
     * @param userService the {@link UserService userService} that handles user details
     */
    @Autowired
    public CreateAccountController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * This is responsible for loading the create account page
     * @param model the {@link Model model} for view attributes
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/newaccount", method= RequestMethod.GET)
    public String viewCreateAccountPage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "createaccount";
    }

    /**
     * This is responsible for adding a new user
     * @param newUser the {@link User newUser} that holds the new account details
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/newaccount", method=RequestMethod.POST)
    public String addUser(@Validated({createAccount.class}) @ModelAttribute("newUser") User newUser,
                          BindingResult result, Model model, HttpSession session)
    {
        boolean check = userService.checkUsername(newUser.getUsername());

        if (!result.hasErrors()) {
            if (!check) {
                userService.insertUser(newUser);
                session.setAttribute("currentUser", newUser);
                return "redirect:/user/personaldetails";
            }
            model.addAttribute("exists", true);
        }
        else {
            model.addAttribute("error", true);
            return "createaccount";
        }

        return "createaccount";
    }
}