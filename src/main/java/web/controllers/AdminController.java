package web.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.ManageUsers;
import web.domain.application.Admin;
import web.domain.application.Admin.application;
import web.domain.application.Admin.password;
import web.service.MatchService;
import web.service.PreferencesService;
import web.service.UserService;
import web.service.WorkerService;

/**
 * This controller is responsible for handling requests from an admin user.
 * Specifically, it will handle requests for managing users and application settings.
 *
 *
 */
@Controller
public class AdminController
{
    @Autowired
    private PreferencesService preferencesService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkerService workerService;
    BCryptPasswordEncoder passwordEncoder;

    /**
     * Configuring the services to be used
     * @param preferencesService the {@link #preferencesService preferencesService} interface
     * @param matchService the {@link #matchService matchService} interface
     * @param userService the {@link #userService userService} interface
     * @param workerService the {@link #workerService workerService} interface
     */
    public AdminController(PreferencesService preferencesService, MatchService matchService,
                           UserService userService, WorkerService workerService)
    {
        this.preferencesService = preferencesService;
        this.matchService = matchService;
        this.userService = userService;
        this.workerService = workerService;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * This is responsible for loading the admin home page.
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/admin/adminhome", method=RequestMethod.GET)
    public String viewAdminHome(Model model, HttpSession session) {
        if (session.getAttribute("adminUser") != null) {
            return "admin/adminhome";
        }
        return "redirect:/newlogin";
    }

    /**
     * This is responsible for loading the manage users page.
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/manageusers", method=RequestMethod.GET)
    public String viewManageUsers(Model model, HttpSession session)
    {
        if (session.getAttribute("adminUser") != null) {
            prePopulateUsers(model);
            return "admin/manageusers";
        }
        return "redirect:/newlogin";
    }

    /**
     * This is responsible for deleting a freelancer
     * @param manageUsers the {@link ManageUsers manageUsers} object that holds the freelancers to delete
     * @param model the {@link Model model} for view attributes
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/manageusers", method=RequestMethod.POST, params="deletefreelancer")
    public String deleteFreelancer(@ModelAttribute("manageUsers") ManageUsers manageUsers, Model model)
    {
        workerService.deleteFreelancer(manageUsers.getFreelancers());
        return "redirect:/admin/manageusers";
    }

    /**
     * This is responsible for deleting an employer
     * @param manageUsers the {@link ManageUsers manageUsers} object that holds the employers to delete
     * @param model the {@link Model model} for view attributes
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/manageusers", method=RequestMethod.POST, params="deleteemployer")
    public String deleteEmployer(@ModelAttribute("manageUsers") ManageUsers manageUsers, Model model) {
        workerService.deleteEmployer(manageUsers.getEmployers());
        return "redirect:/admin/manageusers";
    }

    /**
     * This is responsible for deleting a match
     * @param manageUsers the {@link ManageUsers manageUsers} object that holds the matches to delete
     * @param model the {@link Model model} for view attributes
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path={"admin/manageusers"}, method=RequestMethod.POST, params="deletematch")
    public String deleteMatch(@ModelAttribute("manageUsers") ManageUsers manageUsers, Model model) {
        matchService.deleteMatch(manageUsers.getMatches());
        return "redirect:/admin/manageusers";
    }

    /**
     * This is responsible for loading the application settings page
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/adminpassword", method=RequestMethod.GET)
    public String viewChangePassword(Model model, HttpSession session)
    {
        if (session.getAttribute("adminUser") != null) {
            model.addAttribute("passwordAdmin", preferencesService.getAdmin());
            model.addAttribute("applicationAdmin", preferencesService.getAdmin());
            return "admin/adminpassword";
        }
        return "redirect:/newlogin";
    }


    /**
     * This is responsible for updating the industry name
     * @param applicationAdmin the {@link Admin applicationAdmin} object responsible for holding the new industry name
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/admin/adminpassword", method=RequestMethod.POST, params="newIndustry")
    public String configureApplication(@Validated({application.class}) @ModelAttribute("applicationAdmin") Admin applicationAdmin,
                                       BindingResult result, Model model)
    {
        if (!result.hasErrors()) {
            preferencesService.updateApplication(applicationAdmin);
            model.addAttribute("applicationsuccess", true);
        } else {
            model.addAttribute("applicationerror", true);
        }
        return "redirect:/admin/adminpassword";
    }

    /**
     * This is responsible for updating the admin password
     * @param passwordAdmin the {@link Admin passwordAdmin} object responsible for holding the updated password
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="/admin/adminpassword", method= RequestMethod.POST, params="updatePassword")
    public String changePassword(@Validated({password.class}) @ModelAttribute("passwordAdmin") Admin passwordAdmin,
                                 BindingResult result, Model model)
    {
        if (!result.hasErrors()) {
            if (userService.checkAdminLogIn(passwordAdmin.getPassword())) {
                if (passwordAdmin.getNewPassword().trim().equals(passwordAdmin.getConfirmPassword().trim())) {
                    userService.updateAdminPassword(passwordAdmin.getNewPassword().trim());
                    model.addAttribute("success", true);
                } else {
                    model.addAttribute("error", true);
                }
            } else {
                model.addAttribute("error", true);
            }
        } else {
            model.addAttribute("error", true);
        }
        return "redirect:/admin/adminpassword";
    }

    private void prePopulateUsers(Model model) {
        model.addAttribute("manageUsers", new ManageUsers());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("employers", matchService.getEmployers());
        model.addAttribute("freelancers", matchService.getFreelancers());
        model.addAttribute("matches", matchService.getAllMatches());
    }
}