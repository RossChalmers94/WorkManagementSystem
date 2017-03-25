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

/**
 * This is responsible for entering a user's personal details
 *
 */
@Controller
public class PersonalDetailsController
{
    private UserService userService;

    /**
     * Configure the service to use
     * @param userService the {@link UserService userService} that handles user details
     */
    @Autowired
    public PersonalDetailsController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * This is responsible for loading the personal details page
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
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


    /**
     * This is responsible for gathering an employer's personal details
     * @param personal the {@link UserPersonal personal} object that holds the user's personal details
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/user/personaldetails", method=RequestMethod.POST, params="employer")
    public String confirmPersonalEmployer(@Validated({personalDetailsEmployer.class}) @ModelAttribute("userPersonal")
                                                      UserPersonal personal, BindingResult result, Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("currentUser");
        user.setUserPersonal(personal);
        return enterPersonalDetails(user, result, model, session);
    }


    /**
     * This is responsible for gathering an freelancer's personal details
     * @param personal the {@link UserPersonal personal} object that holds the user's personal details
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/user/personaldetails", method=RequestMethod.POST, params="freelancer")
    public String confirmPersonalFreelancer(@Validated({personalDetailsFreelancer.class}) @ModelAttribute("userPersonal")
                                                        UserPersonal personal, BindingResult result, Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("currentUser");
        user.setUserPersonal(personal);
        return enterPersonalDetails(user, result, model, session);
    }


    /**
     * This is responsible for entering a user's personal details
     * @param user the {@link User user} object that holds the user's personal details
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
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
