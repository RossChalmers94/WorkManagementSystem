package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.application.*;
import web.service.PreferencesService;

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

    @RequestMapping(path = "/admin/adminhome", method= RequestMethod.GET)
    public String viewAdminHome(Model model){
        return "admin/adminhome";
    }


    @RequestMapping(path = "admin/manageusers", method = RequestMethod.GET)
    public String viewManageUsers(Model model){
        return "admin/manageusers";
    }

    @RequestMapping(path = "admin/adminpassword", method = RequestMethod.GET)
    public String viewChangePassword(Model model){
        model.addAttribute("admin", new Admin());
        return "admin/adminpassword";
    }

    @RequestMapping(path = "admin/adminpassword", method = RequestMethod.POST)
    public String postChangePassword(@Valid @ModelAttribute("admin") Admin admin, BindingResult result, Model model){
        if(!result.hasErrors()){
            if(preferencesService.checkAdminPassword(admin.getPassword())){
                if(admin.getNewPassword().trim().equals(admin.getConfirmPassword().trim())){
                    preferencesService.updatePassword(admin);
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
