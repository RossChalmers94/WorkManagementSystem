package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.UserService;

/**
 * This is responsible for loading the home page.
 *
 */
@Controller
@RequestMapping({"/home"})
public class HomeController
{
    private UserService userService;

    /**
     * Configuring the service to use
     * @param userService the {@link UserService userService} for gathering application details
     */
    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This is responsible for loading the home page
     * @param model the {@link Model model} for view attributes
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewPage(Model model)
    {
        model.addAttribute("industryName", userService.getIndustryName());
        return "home";
    }
}