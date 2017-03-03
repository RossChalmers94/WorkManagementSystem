package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.UserService;
import org.springframework.ui.Model;
import web.domain.*;
import web.service.WorkerService;

import javax.servlet.http.HttpSession;

/**
 * Created by RossChalmers on 09/02/2017.
 */
@Controller
public class UserHomeController {

    @Autowired
    private WorkerService workerService;


    @RequestMapping(path = "user/userhome", method= RequestMethod.GET)
    public String viewEmployerPreferences(Model model, HttpSession session){
        if(session.getAttribute("currentUser") != null) {
            User user = (User) session.getAttribute("currentUser");
            Worker worker = workerService.getWorkerDetails(user);
            model.addAttribute("worker", worker);
            if (worker.getPreviousMatch() != 0) {
                return "redirect:/user/completematch";
            }
            return "user/userhome";
        } else {
            return "redirect:/login";
        }
    }

}
