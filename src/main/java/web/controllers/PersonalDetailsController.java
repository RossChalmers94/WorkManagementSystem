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
import web.domain.UserPersonal;
import web.domain.UserPersonal.personalDetailsEmployer;
import web.domain.UserPersonal.personalDetailsFreelancer;
import web.service.UserService;

@Controller
public class PersonalDetailsController
{
    private UserService userService;

    @Autowired
    public PersonalDetailsController(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(path="/user/personaldetails", method= RequestMethod.GET)
    public String viewPersonal(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            User user = (User)session.getAttribute("currentUser");
            User newUser = userService.getLogIn(user);
            UserPersonal userPersonal = newUser.getUserPersonal();
            model.addAttribute("userPersonal", userPersonal);
            return "/user/personal";
        }
        return "redirect:/newlogin";
    }



    @RequestMapping(path="/user/personaldetails", method=RequestMethod.POST, params="employer")
    public String confirmPersonalEmployer(@Validated({personalDetailsEmployer.class}) @ModelAttribute("userPersonal")
                                                      UserPersonal personal, BindingResult result, Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("currentUser");
        user.setUserPersonal(personal);
        return enterPersonalDetails(user, result, model, session);
    }


    @RequestMapping(path="/user/personaldetails", method=RequestMethod.POST, params="freelancer")
    public String confirmPersonalFreelancer(@Validated({personalDetailsFreelancer.class}) @ModelAttribute("userPersonal")
                                                        UserPersonal personal, BindingResult result, Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("currentUser");
        user.setUserPersonal(personal);
        return enterPersonalDetails(user, result, model, session);
    }



    private String enterPersonalDetails(User user, BindingResult result, Model model, HttpSession session)
    {
        if (!result.hasErrors()) {
            userService.insertUserPersonal(user);
            return "redirect:/user/preferencesdetails";
        }
        model.addAttribute("error", true);
        return "user/personal";
    }
}
