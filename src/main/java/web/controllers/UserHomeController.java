package web.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.domain.User;
import web.domain.Worker;
import web.service.WorkerService;

/**
 * This is responsible for loading the user home page
 *
 * @Author Ross Chalmers
 */
@Controller
public class UserHomeController
{

    private WorkerService workerService;

    /**
     * Configure the service to use
     * @param workerService the {@link WorkerService workerService} that handles the worker's details
     */
    @Autowired
    public UserHomeController(WorkerService workerService) {
        this.workerService = workerService;
    }


    /**
     * This is responsible for loading the user home page
     * @param model the {@link Model model} for view attributes
     * @param session the {@link HttpSession session} for session variables
     * @return the {@link String String} of the view to be returned
     */
    @RequestMapping(path="user/userhome", method=RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session)
    {
        if (session.getAttribute("currentUser") != null) {
            User user = (User)session.getAttribute("currentUser");
            Worker worker = workerService.getWorkerDetails(user);
            model.addAttribute("worker", worker);
            if (worker.getPreviousMatch() != 0) {
                return "redirect:/user/completematch";
            }
            return "user/userhome";
        }
        return "redirect:/newlogin";
    }
}