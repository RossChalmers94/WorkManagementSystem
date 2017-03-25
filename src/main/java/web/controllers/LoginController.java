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
import web.domain.User.logIn;
import web.domain.application.Admin;
import web.service.UserService;

/**
 * This is responsible for logging a user into the application
 * @Author Ross Chalmers
 */
@Controller
public class LoginController
{
    private UserService userService;

    /**
     * Configuring the service to use
     * @param userService the {@link UserService userService} that handles user details
     */
    @Autowired
    public LoginController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * This is responsible for loading the log in page
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/newlogin", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session) {
        String page = "";
        if (session.getAttribute("currentUser") != null) {
            page = "user/userhome";
        }
        else {
            User user = new User();
            model.addAttribute("loginUser", user);
            page = "login";
        }

        return page;
    }

    /**
     * This is responsible for logging the user into the application
     * @param loginUser the {@link User loginUser} that holds the new account details
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session  the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/newlogin", method=RequestMethod.POST)
    public String confirmEmployerPreferences(@Validated({logIn.class}) @ModelAttribute("loginUser") User loginUser,
                                             BindingResult result, Model model, HttpSession session)
    {
        if (!result.hasErrors()) {
            boolean login = userService.checkUserLogIn(loginUser.getUsername(), loginUser.getPassword());
            if (login) {
                User currentUser = userService.getLogIn(loginUser);
                session.setAttribute("currentUser", currentUser);
                return "redirect:/user/userhome"; }
            else if (loginUser.getUsername().equals("admin")) {
                boolean adminLogIn = userService.checkAdminLogIn(loginUser.getPassword());
                if (adminLogIn) {
                    Admin admin = userService.getAdminLogIn(loginUser);
                    session.setAttribute("adminUser", admin);
                    return "redirect:/admin/adminhome";
                }

                model.addAttribute("error", true);
            }
            else {
                model.addAttribute("error", true);
            }
        } else {
            model.addAttribute("details", true);
        }

        return "login";
    }

    /**
     * This is responsible for logging a user out of the application
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/logoutuser", method=RequestMethod.GET)
    public String logOut(Model model, HttpSession session) {
        session.removeAttribute("currentUser");
        session.removeAttribute("adminUser");
        return "redirect:/home";
    }
}