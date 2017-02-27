package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import web.service.MatchService;
import web.service.UserService;
import web.service.WorkerService;
import web.domain.User;
import web.domain.*;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class MatchController {

    @Autowired
    private UserService userService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private MatchService matchService;

    @RequestMapping(path = "user/match", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Worker worker = workerService.getWorkerDetails(user);
        Match match = new Match();
        User userMatch = new User();
        if(user.getEmployerID() != 0){
            if(worker.getJobMatch() == 0) {
                match = matchService.getEmployerMatch(user);
                if (match == null) {
                    return "redirect:/user/nomatch";
                } else {
                    userMatch = userService.getUserByFreelancer(match.getMatchID());
                }
            } else {
                match = matchService.getExistingEmployerMatch(worker.getJobMatch());
                userMatch = userService.getUserByFreelancer(match.getMatchID());
            }
        } else if (user.getFreelancerID() != 0){
            if(worker.getJobMatch() == 0) {
                match = matchService.getFreelancerMatch(user);
                if (match == null) {
                    return "redirect:/user/nomatch";
                } else {
                    userMatch = userService.getUserByEmployer(match.getMatchID());
                }
            } else {
                match = matchService.getExistingFreelancerMatch(worker.getJobMatch());
                userMatch = userService.getUserByEmployer(match.getMatchID());
            }
        }

        model.addAttribute("match", match);
        model.addAttribute("userMatch", userMatch);
;
        return "user/match";
    }
    @RequestMapping(path = "user/match", method= RequestMethod.POST)
    public String confirmEmployerPreferences(){
        return "user/match";
    }

    @RequestMapping(path = "user/nomatch", method= RequestMethod.GET)
    public String noMatch(){
        return "user/nomatch";
    }

    @RequestMapping(path = "user/completematch", method= RequestMethod.GET)
    public String completeMatch(Model model, HttpSession session){
        Worker worker = new Worker();
        model.addAttribute("completeMatch", worker);
        return "user/completematch";
    }

    @RequestMapping(path = "user/completematch", method= RequestMethod.POST)
    public String confirmCompleteMatch(@ModelAttribute("completeMatch") Worker completeMatch, Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Worker worker = workerService.getWorkerDetails(user);
        if(worker.getPreviousMatch() == 0) {
            matchService.completeMatch(user, completeMatch.getRating(), worker.getJobMatch());
        } else if(worker.getPreviousMatch() != 0){
            matchService.setPreviousRating(user, completeMatch.getRating(), worker.getPreviousMatch());
        }
        return "redirect:/user/userhome";
    }
}
