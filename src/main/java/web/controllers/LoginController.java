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
import web.domain.User.logIn;
import web.domain.application.Admin;
import web.service.UserService;

@Controller
public class LoginController
{
    private UserService userService;

    @Autowired
    public LoginController(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(path={"/newlogin"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
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

    @RequestMapping(path={"/newlogin"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String confirmEmployerPreferences(@Validated({User.logIn.class}) @ModelAttribute("loginUser") User loginUser, BindingResult result, Model model, HttpSession session)
    {
        if (!result.hasErrors()) {
            boolean login = userService.checkUserLogIn(loginUser.getUsername(), loginUser.getPassword());
            if (login) {
                User currentUser = userService.getLogIn(loginUser);
                session.setAttribute("currentUser", currentUser);
                return "redirect:/user/userhome"; }
            if (loginUser.getUsername().equals("admin")) {
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

    @RequestMapping(path={"/logoutuser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String logOut(Model model, HttpSession session) {
        session.removeAttribute("currentUser");
        session.removeAttribute("adminUser");
        return "redirect:/home";
    }
}