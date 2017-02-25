package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
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

    @RequestMapping(path = "user/match", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        if(user.getEmployerID() != 0){
            Match match = workerService.getEmployerMatch(user);
            model.addAttribute("match", match);
            User userMatch = userService.getUserByFreelancer(match.getMatchID());
            model.addAttribute("userMatch", userMatch);
        } else if (user.getFreelancerID() != 0){
            Match match = workerService.getFreelancerMatch(user);
            model.addAttribute("match", match);
            User userMatch = userService.getUserByEmployer(match.getMatchID());
            model.addAttribute("userMatch", userMatch);
        }

        return "user/match";
    }
    @RequestMapping(path = "user/match", method= RequestMethod.POST)
    public String confirmEmployerPreferences(){
        return "user/match";
    }

}
