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

@Controller
public class UserHomeController
{
    @Autowired
    private WorkerService workerService;

    public UserHomeController() {}

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