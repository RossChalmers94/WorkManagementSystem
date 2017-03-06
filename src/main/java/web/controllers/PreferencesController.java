package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.application.Application;
import web.service.PreferencesService;
import org.springframework.ui.Model;
import web.domain.*;
import web.service.WorkerService;
import web.domain.Worker.*;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class PreferencesController {

    private PreferencesService preferencesService;
    private WorkerService workerService;

    @Autowired
    public PreferencesController(PreferencesService preferencesService, WorkerService workerService){
        this.preferencesService = preferencesService;
        this.workerService = workerService;
    }


    @RequestMapping(path = "user/preferencesdetails", method= RequestMethod.GET)
    public String viewPreferences(Model model, HttpSession session){
        if(session.getAttribute("currentUser") != null) {
            User user = (User) session.getAttribute("currentUser");
            Worker newWorker = workerService.getWorkerDetails(user);
            model.addAttribute("application", setApplication());
            model.addAttribute("newWorker", newWorker);
            return "user/preferences";
        } else {
            return "redirect:/login";
        }
    }
    @RequestMapping(path = "user/preferencesdetails", method= RequestMethod.POST, params = "employer")
    public String confirmEmployerPreferences(@Validated({preferencesDetailsEmployer.class})@ModelAttribute("newWorker") Worker newWorker,
                                             BindingResult result, Model model, HttpSession session){
        if(!result.hasErrors()) {
            User user = (User) session.getAttribute("currentUser");
            int workerID = workerService.insertWorker(newWorker, user);
            user.setEmployerID(workerID);
            session.removeAttribute("currentUser");
            session.setAttribute("currentUser", user);
            session.setAttribute("currentWorker", newWorker);
            return "redirect:/user/match";
        } else {
            model.addAttribute("employererror", true);
            model.addAttribute(setApplication());
            return "user/preferences";
        }
    }

    @RequestMapping(path = "user/preferencesdetails", method= RequestMethod.POST, params = "freelancer")
    public String confirmFreelancerPreferences(@Validated({preferencesDetailsFreelancer.class})@ModelAttribute("newWorker")
                                                           Worker newWorker, BindingResult result, Model model, HttpSession session){
        if(!result.hasErrors()) {
            User user = (User) session.getAttribute("currentUser");
            int workerID = workerService.insertWorker(newWorker, user);
            user.setFreelancerID(workerID);
            session.removeAttribute("currentUser");
            session.setAttribute("currentUser", user);
            session.setAttribute("currentWorker", newWorker);
            return "redirect:/user/match";
        } else {
            model.addAttribute("freelancererror", true);
            model.addAttribute(setApplication());
            return "user/preferences";
        }
    }

    private Application setApplication(){
        Application application = new Application();
        application.setSkills(preferencesService.getSkills());
        application.setLocations(preferencesService.getLocations());
        application.setJobLengths(preferencesService.getJobLengths());
        application.setSalarys(preferencesService.getSalarys());
        return application;
    }
}
