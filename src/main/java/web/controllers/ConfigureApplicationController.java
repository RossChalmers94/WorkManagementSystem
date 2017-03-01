package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.application.Application;
import web.domain.application.Application.*;
import web.service.PreferencesService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class ConfigureApplicationController {

    @Autowired
    private PreferencesService preferencesService;

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.GET)
    public String viewConfigureApplication(Model model, HttpSession session){
        Application newApplication = new Application();
        newApplication.setLocations(preferencesService.getLocations());
        newApplication.setSkills(preferencesService.getSkills());
        newApplication.setSalarys(preferencesService.getSalarys());
        newApplication.setJobLengths(preferencesService.getJobLengths());
        model.addAttribute("newApplication", newApplication);
        return "admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "addskill")
    public String addSkill(@Valid @ModelAttribute("newApplication") Application newApplication, BindingResult result,
                           Model model, HttpSession session){
        if(!result.hasErrors()){
            preferencesService.addSkill(newApplication.getSkill().getSkillName().trim());
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("skills", true);
            return "redirect:/admin/configureapplication";
        }

    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "deleteskill")
    public String deleteSkills(@Valid @ModelAttribute("newApplication") Application newApplication, BindingResult result,
                                             Model model, HttpSession session){
        preferencesService.deleteSkill(newApplication.getSkillsSet());
        return "redirect:/admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "addlocation")
    public String addLocation(@Valid @ModelAttribute("newApplication") Application newApplication, BindingResult result,
                           Model model, HttpSession session){
        if(!result.hasErrors()){
            preferencesService.addLocation(newApplication.getLocation().getLocationName().trim());
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("locations", true);
            return "redirect:/admin/configureapplication";
        }
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "deletelocation")
    public String deleteLocation(@ModelAttribute("newApplication") Application newApplication, BindingResult result,
                               Model model, HttpSession session){
        preferencesService.deleteLocation(newApplication.getLocationSet());
        return "redirect:/admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "configureapplication")
    public String configureApplication(@Valid @ModelAttribute("newApplication") Application newApplication, BindingResult result,
                                       Model model, HttpSession session){
        if(!result.hasErrors()) {
            preferencesService.updatePreferences(newApplication);
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("preferences", true);
            return "redirect:/admin/configureapplication";
        }

    }


}
