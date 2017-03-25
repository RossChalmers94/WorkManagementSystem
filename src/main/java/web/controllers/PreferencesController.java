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
import web.domain.Worker;
import web.domain.Worker.preferencesDetailsEmployer;
import web.domain.Worker.preferencesDetailsFreelancer;
import web.domain.application.Application;
import web.service.PreferencesService;
import web.service.WorkerService;

/**
 * This is responsible for gathering and entering a user's preferences
 * @Author Ross Chalmers
 */
@Controller
public class PreferencesController
{
    private PreferencesService preferencesService;
    private WorkerService workerService;

    /**
     * Configuring the services to use
     * @param preferencesService the {@link PreferencesService preferencesService} interface will gather all preferences
     *                           for selection
     * @param workerService the {@link WorkerService workerService} interface will handle worker details
     */
    @Autowired
    public PreferencesController(PreferencesService preferencesService, WorkerService workerService)
    {
        this.preferencesService = preferencesService;
        this.workerService = workerService;
    }

    /**
     * This is responsible for loading the preferences page
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="user/preferencesdetails", method=RequestMethod.GET)
    public String viewPreferences(Model model, HttpSession session)
    {
        if (session.getAttribute("currentUser") != null) {
            User user = (User)session.getAttribute("currentUser");
            Worker newWorker = workerService.getWorkerDetails(user);
            user.setUserWorker(newWorker);
            model.addAttribute("application", setApplication());
            model.addAttribute("newWorker", newWorker);
            return "user/preferences";
        }
        return "redirect:/newlogin";
    }

    /**
     * This is responsible for inserting an employer's personal details
     * @param newWorker the {@link Worker newWorker} object that holds the employer's preferences
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="user/preferencesdetails", method=RequestMethod.POST, params="employer")
    public String confirmEmployerPreferences(@Validated({preferencesDetailsEmployer.class}) @ModelAttribute("newWorker")
                                                         Worker newWorker, BindingResult result, Model model, HttpSession session)
    {
        if (!result.hasErrors()) {
            User user = (User)session.getAttribute("currentUser");
            user.setUserWorker(newWorker);
            int workerID = workerService.insertWorker(user);
            user.setEmployerID(workerID);
            session.removeAttribute("currentUser");
            session.setAttribute("currentUser", user);
            session.setAttribute("currentWorker", newWorker);
            return "redirect:/user/yourmatch";
        }
        model.addAttribute("employererror", true);
        model.addAttribute(setApplication());
        return "user/preferences";
    }

    /**
     * This is responsible for inserting a freelancer's personal details
     * @param newWorker the {@link Worker newWorker} object that holds the freelancer's preferences
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="user/preferencesdetails", method=RequestMethod.POST, params="freelancer")
    public String confirmFreelancerPreferences(@Validated({preferencesDetailsFreelancer.class}) @ModelAttribute("newWorker")
                                                           Worker newWorker, BindingResult result, Model model, HttpSession session)
    {
        if (!result.hasErrors()) {
            User user = (User)session.getAttribute("currentUser");
            user.setUserWorker(newWorker);
            int workerID = workerService.insertWorker(user);
            user.setFreelancerID(workerID);
            session.removeAttribute("currentUser");
            session.setAttribute("currentUser", user);
            session.setAttribute("currentWorker", newWorker);
            return "redirect:/user/yourmatch";
        }
        model.addAttribute("freelancererror", true);
        model.addAttribute(setApplication());
        return "user/preferences";
    }

    /**
     * This is responsible for loading the preferences set by the administrator
     * @return the {@link Application application} object that holds the preferences
     */
    private Application setApplication()
    {
        Application application = new Application();
        application.setSkills(preferencesService.getSkills());
        application.setLocations(preferencesService.getLocations());
        application.setJobLengths(preferencesService.getJobLengths());
        application.setSalarys(preferencesService.getSalarys());
        return application;
    }
}