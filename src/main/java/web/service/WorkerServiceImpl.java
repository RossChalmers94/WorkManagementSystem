package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.enumconstants.UserDetails;
import web.enumconstants.WorkerDetails;
import web.repository.UserDAO;
import web.repository.WorkerDAO;
import web.domain.*;
import java.util.*;

import java.util.HashMap;

/**
 * Created by RossChalmers on 20/02/2017.
 */

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerDAO workerDAO;
    @Autowired
    private UserDAO userDAO;

    public void insertWorker(Worker worker, User user){

        Map<String, Object> inParameters = new HashMap<String, Object>();
        inParameters.put(WorkerDetails.SALARY.getValue(), worker.getSalary());
        inParameters.put(WorkerDetails.LOCATION.getValue(), worker.getLocation());
        inParameters.put(WorkerDetails.JOB_LENGTH.getValue(), worker.getJobLength());
        inParameters.put(WorkerDetails.RELAX_PREFERENCES.getValue(), worker.getRelaxPreferences());
        inParameters.put(WorkerDetails.RATING.getValue(), worker.getRating());

        if(user.getRole().equals("Employer")){
            insertEmployer(user.getUsername(), inParameters);
        } else if(user.getRole().equals("Freelancer")){
            inParameters.put(WorkerDetails.JOB_TITLE.getValue(), worker.getJobTitle());
            inParameters.put(WorkerDetails.JOB_DESCRIPTION.getValue(), worker.getJobDescription());
            insertFreelancer(user.getUsername(), inParameters);
        }
    }

    private void insertEmployer(String username, Map<String, Object> inParameters){
        Map<String, Object> out = workerDAO.insertEmployer("insert_employer", inParameters);
        Map<String, Object> userParameters = new HashMap<String, Object>();
        userParameters.put(UserDetails.USER_NAME.getValue(), username);
        userParameters.put(WorkerDetails.EMPLOYER_ID.getValue(), (Integer) out.get(WorkerDetails.EMPLOYER_ID.getValue()));
        userDAO.insertEmployerID("insert_user_employer", userParameters);
    }

    private void insertFreelancer(String username, Map<String, Object> inParameters){
        Map<String, Object> out = workerDAO.insertFreelancer("insert_freelancer", inParameters);
        Map<String, Object> userParameters = new HashMap<String, Object>();
        userParameters.put(UserDetails.USER_NAME.getValue(), username);
        userParameters.put(WorkerDetails.FREELANCER_ID.getValue(), (Integer) out.get(WorkerDetails.FREELANCER_ID.getValue()));
        userDAO.insertFreelancerID("insert_user_freelancer", userParameters);
    }
}
