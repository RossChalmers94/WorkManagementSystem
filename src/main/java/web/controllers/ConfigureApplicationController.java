package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.application.*;
import web.domain.application.Application.*;
import web.service.PreferencesService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class ConfigureApplicationController {


    private PreferencesService preferencesService;

    @Autowired
    public ConfigureApplicationController(PreferencesService preferencesService){
        this.preferencesService = preferencesService;
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.GET)
    public String viewConfigureApplication(Model model, HttpSession session){
        if(session.getAttribute("adminUser") != null) {
            Application newApplication = new Application();
            model.addAttribute("newApplication", newApplication);
            Skill configureSkills = new Skill();
            configureSkills.setSkills(preferencesService.getSkills());
            Location configureLocations = new Location();
            configureLocations.setLocations(preferencesService.getLocations());
            Salary configureSalaries = new Salary();
            configureSalaries.setSalarys(preferencesService.getSalarys());
            JobLength configureJobLengths = new JobLength();
            configureJobLengths.setJobLengths(preferencesService.getJobLengths());
            model.addAttribute("configureSkills", configureSkills);
            model.addAttribute("configureLocations", configureLocations);
            model.addAttribute("configureSalaries", configureSalaries);
            model.addAttribute("configureJobLengths", configureJobLengths);
            return "admin/configureapplication";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "addskill")
    public String addSkill(@Validated @ModelAttribute("configureSkills") Skill configureSkills, BindingResult result,
                           Model model, HttpSession session){
        if(!result.hasErrors()){
            preferencesService.addSkill(configureSkills.getSkillName().trim());
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("skills", true);
            return "redirect:/admin/configureapplication";
        }

    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "deleteskill")
    public String deleteSkills(@Valid @ModelAttribute("configureSkills") Skill configureSkills, BindingResult result,
                                             Model model, HttpSession session){
        preferencesService.deleteSkill(configureSkills.getSkillsSet());
        return "redirect:/admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "addlocation")
    public String addLocation(@Valid @ModelAttribute("configureLocations") Location configureLocations, BindingResult result,
                           Model model, HttpSession session){
        if(!result.hasErrors()){
            preferencesService.addLocation(configureLocations.getLocationName().trim());
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("locations", true);
            return "redirect:/admin/configureapplication";
        }
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "deletelocation")
    public String deleteLocation(@ModelAttribute("configureLocations") Location configureLocations, BindingResult result,
                               Model model, HttpSession session){
        preferencesService.deleteLocation(configureLocations.getLocationSet());
        return "redirect:/admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "addsalaries")
    public String configureSalaries(@Valid @ModelAttribute("configureSalaries") Salary configureSalaries,
                                       BindingResult result,
                                       Model model, HttpSession session){
        if(!result.hasErrors()) {
            preferencesService.updateSalaries(configureSalaries);
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("preferences", true);
            return "redirect:/admin/configureapplication";
        }

    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "addJobLengths")
    public String configureJobLengths(@Valid @ModelAttribute("configureJobLengths") JobLength configureJobLengths,
                                       BindingResult result,
                                       Model model, HttpSession session){
        if(!result.hasErrors()) {
            preferencesService.updateJobLengths(configureJobLengths);
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("preferences", true);
            return "redirect:/admin/configureapplication";
        }

    }


}
