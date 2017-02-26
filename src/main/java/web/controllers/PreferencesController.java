package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.application.Application;
import web.service.PreferencesService;
import org.springframework.ui.Model;
import web.domain.*;
import web.service.WorkerService;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class PreferencesController {

    @Autowired
    private PreferencesService preferencesService;
    @Autowired
    private WorkerService workerService;

    @RequestMapping(path = "user/preferences", method= RequestMethod.GET)
    public String viewPreferences(Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Worker newWorker = workerService.getWorkerDetails(user);
        Application application = new Application();
        application.setSkills(preferencesService.getSkills());
        application.setLocations(preferencesService.getLocations());
        application.setJobLengths(preferencesService.getJobLengths());
        application.setSalarys(preferencesService.getSalarys());
        model.addAttribute("application", application);
        model.addAttribute("newWorker", newWorker);
        return "user/preferences";
    }
    @RequestMapping(path = "/user/preferences", method= RequestMethod.POST)
    public String confirmPreferences(@ModelAttribute("newWorker") Worker newWorker, Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        int workerID = workerService.insertWorker(newWorker, user);
        if(user.getRole().equals("Employer")){
            user.setEmployerID(workerID);
        } else if(user.getRole().equals("Freelancer")){
            user.setFreelancerID(workerID);
        }
        session.setAttribute("currentWorker", newWorker);
        return "redirect:/user/match";
    }
}
