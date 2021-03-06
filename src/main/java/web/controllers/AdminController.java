package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.ManageUsers;
import web.domain.application.*;
import web.service.MatchService;
import web.service.PreferencesService;
import web.domain.application.Admin.*;
import web.service.UserService;
import web.service.WorkerService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class AdminController {

    @Autowired
    private PreferencesService preferencesService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkerService workerService;

    @RequestMapping(path = "/admin/adminhome", method= RequestMethod.GET)
    public String viewAdminHome(Model model, HttpSession session){
        if(session.getAttribute("adminUser") != null) {
            return "admin/adminhome";
        } else {
            return "redirect:/login";
        }
    }


    @RequestMapping(path = "admin/manageusers", method = RequestMethod.GET)
    public String viewManageUsers(Model model, HttpSession session){
        if(session.getAttribute("adminUser") != null) {
            model.addAttribute("manageUsers", new ManageUsers());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("employers", matchService.getEmployers());
            model.addAttribute("freelancers", matchService.getFreelancers());
            model.addAttribute("matches", matchService.getAllMatches());
            return "admin/manageusers";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(path = "admin/manageusers", method = RequestMethod.POST, params = "deletefreelancer")
    public String deleteFreelancer(@ModelAttribute("manageUsers") ManageUsers manageUsers, Model model){
        workerService.deleteFreelancer(manageUsers.getFreelancers());
        return "redirect:/admin/manageusers";
    }

    @RequestMapping(path = "admin/manageusers", method = RequestMethod.POST, params = "deleteemployer")
    public String deleteEmployer(@ModelAttribute("manageUsers") ManageUsers manageUsers, Model model){
        workerService.deleteEmployer(manageUsers.getEmployers());
        return "redirect:/admin/manageusers";
    }

    @RequestMapping(path = "admin/manageusers", method = RequestMethod.POST, params = "deletematch")
    public String deleteMatch(@ModelAttribute("manageUsers") ManageUsers manageUsers, Model model){
        matchService.deleteMatch(manageUsers.getMatches());
        return "redirect:/admin/manageusers";
    }


    @RequestMapping(path = "admin/adminpassword", method = RequestMethod.GET)
    public String viewChangePassword(Model model, HttpSession session){
        if(session.getAttribute("adminUser") != null) {
            model.addAttribute("admin", preferencesService.getAdmin());
            return "admin/adminpassword";
        } else {
            return "redirect:/login";
        }
    }


    @RequestMapping(path = "/admin/adminpassword", params = {"application"}, method = RequestMethod.POST)
    public String configureApplication(@Validated({application.class}) @ModelAttribute("admin") Admin admin,
                                        BindingResult result, Model model){
        if(!result.hasErrors()){
            preferencesService.updateApplication(admin);
            model.addAttribute("applicationsuccess", true);
        } else {
            model.addAttribute("applicationerror", true);
        }
        return "admin/adminpassword";
    }

    @RequestMapping(path = "/admin/adminpassword", params = {"confirmpassword"}, method = RequestMethod.POST)
    public String changePassword(@Validated({password.class})@ModelAttribute("admin") Admin admin, BindingResult result, Model model){
        if(!result.hasErrors()){
            if(preferencesService.checkAdminPassword(admin.getPassword())){
                if(admin.getNewPassword().trim().equals(admin.getConfirmPassword().trim())){
                    preferencesService.updatePassword(admin);
                    preferencesService.updateApplication(admin);
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
        return "admin/adminpassword";
    }

}
