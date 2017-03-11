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
import web.domain.application.Skill.addSkill;
import web.domain.application.Location.addLocation;
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
    public ConfigureApplicationController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.GET)
    public String viewConfigureApplication(Model model, HttpSession session) {
        if (session.getAttribute("adminUser") != null) {
            prePopulateValues(model);
            return "admin/configureapplication";
        } else {
            return "redirect:/newlogin";
        }
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "newSkill")
    public String addSkill(@Validated({addSkill.class}) @ModelAttribute("configureSkills") Skill configureSkills, BindingResult result,
                           Model model, HttpSession session) {
        if (!result.hasErrors()) {
            preferencesService.addSkill(configureSkills.getSkillName().trim());
            model.addAttribute("skillSuccess", true);
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("skills", true);
            return "redirect:/admin/configureapplication";
        }


    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "deleteskill")
    public String deleteSkills(@ModelAttribute("configureSkills") Skill configureSkills, BindingResult result,
                               Model model, HttpSession session) {
        preferencesService.deleteSkill(configureSkills.getSkillsSet());
        return "redirect:/admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "newLocation")
    public String addLocation(@Validated({addLocation.class}) @ModelAttribute("configureLocations") Location configureLocations, BindingResult result,
                              Model model, HttpSession session) {
        if (!result.hasErrors()) {
            preferencesService.addLocation(configureLocations.getLocationName().trim());
            model.addAttribute("locationSuccess", true);
            return "redirect:/admin/configureapplication";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("locations", true);
            return "redirect:/admin/configureapplication";
        }
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "deletelocation")
    public String deleteLocation(@ModelAttribute("configureLocations") Location configureLocations, BindingResult result,
                                 Model model, HttpSession session) {
        preferencesService.deleteLocation(configureLocations.getLocationSet());
        return "redirect:/admin/configureapplication";
    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "newSalaries")
    public String configureSalaries(@ModelAttribute("configureSalaries") Salary configureSalaries,
                                    Model model, HttpSession session) {
        preferencesService.updateSalaries(configureSalaries);
        model.addAttribute("salarySuccess", true);
        prePopulateValues(model);
        return "admin/configureapplication";

    }

    @RequestMapping(path = "admin/configureapplication", method = RequestMethod.POST, params = "newJobLengths")
    public String configureJobLengths(@ModelAttribute("configureJobLengths") JobLength configureJobLengths,
                                      Model model, HttpSession session) {
        preferencesService.updateJobLengths(configureJobLengths);
        model.addAttribute("jobLengthSuccess", true);
        prePopulateValues(model);
        return "admin/configureapplication";
    }

    private void prePopulateValues(Model model) {
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
    }


}
