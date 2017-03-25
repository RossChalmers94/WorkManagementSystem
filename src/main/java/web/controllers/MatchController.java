package web.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.Match;
import web.domain.User;
import web.domain.Worker;
import web.domain.application.Rating;
import web.service.MatchService;
import web.service.UserService;
import web.service.WorkerService;

/**
 * This is responsible for handling all requests concerned with a match
 *
 */
@Controller
public class MatchController
{
    private UserService userService;
    private WorkerService workerService;
    private MatchService matchService;

    /**
     * Configuring the services to be used
     * @param userService the {@link UserService userService} for user details
     * @param workerService the {@link WorkerService workerService} for worker details
     * @param matchService the {@link MatchService matchService} for match details
     */
    @Autowired
    public MatchController(UserService userService, WorkerService workerService, MatchService matchService)
    {
        this.userService = userService;
        this.workerService = workerService;
        this.matchService = matchService;
    }

    /**
     * This is responsible for calculating a match for a user.
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned. This will be dependent on the result
     * of the match calculation
     */
    @RequestMapping(path="user/yourmatch", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            User getUser = (User)session.getAttribute("currentUser");
            User user = userService.getLogIn(getUser);
            Worker worker = workerService.getWorkerDetails(user);
            user.setUserWorker(worker);
            Match match = new Match();
            if (user.getEmployerID() != 0) {
                match = getEmployerMatch(user);
            } else if (user.getFreelancerID() != 0) {
                match = getFreelancerMatch(user);
            }
            if (match != null) {
                model.addAttribute("match", match);
                return "user/match";
            }
            return "redirect:/user/nomatch";
        }

        return "redirect:/newlogin";
    }


    /**
     * This is responsible for getting an employer's match.
     * @param user the {@link User user} that is searching for a match
     * @return the {@link String String} of the view to be returned
     */
    private Match getEmployerMatch(User user)
    {
        if (user.getUserWorker().getJobMatch() == 0) {
            Match match = matchService.getEmployerMatch(user);
            if (match == null) {
                return null;
            }
            return match;
        }

        return matchService.getExistingEmployerMatch(user.getUserWorker().getJobMatch());
    }


    /**
     * This is responsible for getting an freelancer's match.
     * @param user the {@link User user} that is searching for a match
     * @return the {@link String String} of the view to be returned
     */
    private Match getFreelancerMatch(User user)
    {
        if (user.getUserWorker().getJobMatch() == 0) {
            Match match = matchService.getFreelancerMatch(user);
            if (match == null) {
                return null;
            }
            return match;
        }

        return matchService.getExistingFreelancerMatch(user.getUserWorker().getJobMatch());
    }


    @RequestMapping(path="user/yourmatch", method=RequestMethod.POST)
    public String confirmEmployerPreferences()
    {
        return "user/match";
    }

    /**
     * This is responsible for loading the no match page.
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="user/nomatch", method=RequestMethod.GET)
    public String noMatch(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "user/nomatch";
        }
        return "redirect:/newlogin";
    }

    /**
     * This is responsible for loading the complete match page.
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="user/completematch", method=RequestMethod.GET)
    public String completeMatch(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            Rating giveRating = new Rating();
            model.addAttribute("giveRating", giveRating);
            return "user/completematch";
        }
        return "redirect:/newlogin";
    }

    /**
     * This is responsible for completing a match
     * @param giveRating the {@link Rating giveRating} object that holds the rating to complete match
     * @param result the validation binding {@link BindingResult result}
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="user/completematch", method=RequestMethod.POST)
    public String confirmCompleteMatch(@Valid @ModelAttribute("giveRating") Rating giveRating,
                                       BindingResult result, Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("currentUser");
        Worker worker = workerService.getWorkerDetails(user);
        if (!result.hasErrors()) {
            if (worker.getPreviousMatch() == 0) {
                matchService.completeMatch(user, giveRating.getRating(), worker.getJobMatch());
            } else if (worker.getPreviousMatch() != 0) {
                matchService.setPreviousRating(user, giveRating.getRating(), worker.getPreviousMatch());
            }
            return "redirect:/user/userhome";
        }
        return "user/completematch";
    }
}