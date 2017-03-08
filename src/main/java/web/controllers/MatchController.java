package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import web.service.MatchService;
import web.service.UserService;
import web.service.WorkerService;
import web.domain.User;
import web.domain.*;
import web.domain.application.Rating;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class MatchController {

    private UserService userService;
    private WorkerService workerService;
    private MatchService matchService;

    @Autowired
    public MatchController(UserService userService, WorkerService workerService, MatchService matchService) {
        this.userService = userService;
        this.workerService = workerService;
        this.matchService = matchService;
    }

    @RequestMapping(path = "user/yourmatch", method = RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            User getUser = (User) session.getAttribute("currentUser");
            User user = userService.getLogIn(getUser);
            Worker worker = workerService.getWorkerDetails(user);
            user.setUserWorker(worker);
            Match match = new Match();
            if (user.getEmployerID() != 0) {
                match = getEmployerMatch(user);
            } else if (user.getFreelancerID() != 0) {
                match = getFreelancerMatch(user);
            }
            if(match != null) {
                model.addAttribute("match", match);
                return "user/match";
            } else {
                return "redirect:/user/nomatch";
            }
        } else {
            return "redirect:/newlogin";
        }

    }


    private Match getEmployerMatch(User user) {
        Match match;
        if (user.getUserWorker().getJobMatch() == 0) {
            match = matchService.getEmployerMatch(user);
            if (match == null) {
                return null;
            } else {
                return match;
            }
        } else {
            return matchService.getExistingEmployerMatch(user.getUserWorker().getJobMatch());
        }
    }

    private Match getFreelancerMatch(User user) {
        Match match;
        if (user.getUserWorker().getJobMatch() == 0) {
            match = matchService.getFreelancerMatch(user);
            if (match == null) {
                return null;
            } else {
                return match;
            }
        } else {
            return matchService.getExistingFreelancerMatch(user.getUserWorker().getJobMatch());
        }
    }


    @RequestMapping(path = "user/yourmatch", method = RequestMethod.POST)
    public String confirmEmployerPreferences() {
        return "user/match";
    }

    @RequestMapping(path = "user/nomatch", method = RequestMethod.GET)
    public String noMatch(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "user/nomatch";
        }
        return "redirect:/newlogin";
    }

    @RequestMapping(path = "user/completematch", method = RequestMethod.GET)
    public String completeMatch(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            Rating giveRating = new Rating();
            model.addAttribute("giveRating", giveRating);
            return "user/completematch";
        } else {
            return "redirect:/newlogin";
        }
    }

    @RequestMapping(path = "user/completematch", method = RequestMethod.POST)
    public String confirmCompleteMatch(@Valid @ModelAttribute("giveRating") Rating giveRating, BindingResult result, Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        Worker worker = workerService.getWorkerDetails(user);
        if (!result.hasErrors()) {
            if (worker.getPreviousMatch() == 0) {
                matchService.completeMatch(user, giveRating.getRating(), worker.getJobMatch());
            } else if (worker.getPreviousMatch() != 0) {
                matchService.setPreviousRating(user, giveRating.getRating(), worker.getPreviousMatch());
            }
            return "redirect:/user/userhome";
        } else {
            return "user/completematch";
        }
    }
}
