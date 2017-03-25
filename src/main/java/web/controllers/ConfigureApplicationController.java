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
import web.domain.application.Application;
import web.domain.application.JobLength;
import web.domain.application.Location;
import web.domain.application.Location.addLocation;
import web.domain.application.Salary;
import web.domain.application.Skill;
import web.domain.application.Skill.addSkill;
import web.service.PreferencesService;


/**
 * This controller is responsible for handling requests from an admin user.
 * Specifically, it will handle requests for configuring the preferences for the application.
 *
 *
 */
@Controller
public class ConfigureApplicationController
{

    private PreferencesService preferencesService;

    /**
     * Configuring the service to use
     * @param preferencesService the {@link #preferencesService preferencesService} interface
     */
    @Autowired
    public ConfigureApplicationController(PreferencesService preferencesService)
    {
        this.preferencesService = preferencesService;
    }

    /**
     * This is responsible for loading the configure application page.
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/configureapplication", method= RequestMethod.GET)
    public String viewConfigureApplication(Model model, HttpSession session) {
        if (session.getAttribute("adminUser") != null) {
            prePopulateValues(model);
            return "admin/configureapplication";
        }
        return "redirect:/newlogin";
    }


    /**
     * This is responsible for adding a new skill
     * @param configureSkills the {@link Skill configureSkills} that holds the new skill name
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/configureapplication", method=RequestMethod.POST, params="newSkill")
    public String addSkill(@Validated(addSkill.class) @ModelAttribute("configureSkills") Skill configureSkills, BindingResult result,
                           Model model, HttpSession session)
    {
        if (!result.hasErrors()) {
            preferencesService.addSkill(configureSkills.getSkillName().trim());
            model.addAttribute("skillSuccess", true);
            return "redirect:/admin/configureapplication";
        }
        model.addAttribute("error", true);
        model.addAttribute("skills", true);
        return "redirect:/admin/configureapplication";
    }


    /**
     * This is responsible for deleting skills
     * @param configureSkills the {@link Skill configureSkills} that holds the skills to be deleted
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/configureapplication", method=RequestMethod.POST, params="deleteskill")
    public String deleteSkills(@ModelAttribute("configureSkills") Skill configureSkills, BindingResult result,
                               Model model, HttpSession session)
    {
        preferencesService.deleteSkill(configureSkills.getSkillsSet());
        return "redirect:/admin/configureapplication";
    }

    /**
     * This is responsible for adding a new location
     * @param configureLocations the {@link Location configureLocations} that holds the new location name
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/configureapplication", method=RequestMethod.POST, params="newLocation")
    public String addLocation(@Validated(addLocation.class) @ModelAttribute("configureLocations") Location configureLocations, BindingResult result,
                              Model model, HttpSession session)
    {
        if (!result.hasErrors()) {
            preferencesService.addLocation(configureLocations.getLocationName().trim());
            model.addAttribute("locationSuccess", true);
            return "redirect:/admin/configureapplication";
        }
        model.addAttribute("error", true);
        model.addAttribute("locations", true);
        return "redirect:/admin/configureapplication";
    }


    /**
     * This is responsible for deleting locations
     * @param configureLocations the {@link Location configureLocations} that holds the locations to be deleted
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/configureapplication", method=RequestMethod.POST, params="deletelocation")
    public String deleteLocation(@ModelAttribute("configureLocations") Location configureLocations, BindingResult result,
                                 Model model, HttpSession session)
    {
        preferencesService.deleteLocation(configureLocations.getLocationSet());
        return "redirect:/admin/configureapplication";
    }

    /**
     * This is responsible for updating salary ranges.
     * @param configureSalaries the {@link Salary configureSalaries} that holds the salaries to be updated
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/configureapplication", method=RequestMethod.POST, params="newSalaries")
    public String configureSalaries(@ModelAttribute("configureSalaries") Salary configureSalaries,
                                    Model model, HttpSession session)
    {
        preferencesService.updateSalaries(configureSalaries);
        model.addAttribute("salarySuccess", true);
        prePopulateValues(model);
        return "admin/configureapplication";
    }

    /**
     * This is responsible for updating job length ranges
     * @param configureJobLengths the {@link JobLength configureJobLengths} that holds the salaries to be updated
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="admin/configureapplication", method=RequestMethod.POST, params="newJobLengths")
    public String configureJobLengths(@ModelAttribute("configureJobLengths") JobLength configureJobLengths, Model model, HttpSession session)
    {
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