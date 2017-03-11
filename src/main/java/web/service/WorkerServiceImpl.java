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

    public WorkerServiceImpl(WorkerDAO workerDAO){
        this.workerDAO = workerDAO;
    }

    public int insertWorker(User user) {

        int workerID = 0;

        Map<String, Object> inParameters = new HashMap<String, Object>();
        inParameters.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        inParameters.put(WorkerDetails.SALARY.getValue(), user.getUserWorker().getSalary());
        inParameters.put(WorkerDetails.LOCATION.getValue(), user.getUserWorker().getLocation());
        inParameters.put(WorkerDetails.JOB_LENGTH.getValue(), user.getUserWorker().getJobLength());
        inParameters.put(WorkerDetails.RELAX_PREFERENCES.getValue(), user.getUserWorker().getRelaxPreferences());
        inParameters.put(WorkerDetails.RATING.getValue(), user.getUserWorker().getRating());
        inParameters.put(WorkerDetails.MINIMUM_MATCH.getValue(), user.getUserWorker().getMinimumMatch());

        if (user.getRole().equals("Employer")) {
            inParameters.put(WorkerDetails.JOB_TITLE.getValue(), user.getUserWorker().getJobTitle());
            inParameters.put(WorkerDetails.JOB_DESCRIPTION.getValue(), user.getUserWorker().getJobDescription());
            workerID = insertEmployer(user, inParameters);
            insertEmployerSkills(workerID, user.getUserWorker().getSkill());
        } else if (user.getRole().equals("Freelancer")) {
            workerID = insertFreelancer(user, inParameters);
            insertFreelancerSkills(workerID, user.getUserWorker().getSkill());
        }

        return workerID;
    }

    // Insert a Employer's preferences and then add their employerID to the users table
    private int insertEmployer(User user, Map<String, Object> inParameters) {

        int employerID;

        if (user.getEmployerID() != 0) {
            inParameters.put(WorkerDetails.EMPLOYER_ID.getValue(), user.getEmployerID());
            workerDAO.updateEmployer(inParameters);
            employerID = user.getEmployerID();
        } else {
            employerID = workerDAO.insertEmployer(inParameters);
        }
        return employerID;
    }

    // Insert a Freelancer's preferences and then add their freelancerID to the users table
    private int insertFreelancer(User user, Map<String, Object> inParameters) {

        int freelancerID;

        if (user.getFreelancerID() != 0) {
            inParameters.put(WorkerDetails.FREELANCER_ID.getValue(), user.getFreelancerID());
            workerDAO.updateFreelancer(inParameters);
            freelancerID = user.getFreelancerID();
        } else {
            //Insert Freelancer Details. Return freelancerID
            freelancerID = workerDAO.insertFreelancer(inParameters);
        }

        return freelancerID;
    }

    // Insert an Employer's skill set
    private void insertEmployerSkills(int employerID, List<Integer> skills) {

        workerDAO.deleteEmployerSkills(employerID);

        for (int i = 0; i < skills.size(); i++) {
            Map<String, Object> inParameters = new HashMap<String, Object>();
            inParameters.put(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
            inParameters.put(WorkerDetails.SKILL_ID.getValue(), skills.get(i));
            workerDAO.insertEmployerSkills(inParameters);
        }
    }

    // Insert a Freelancer's skill set
    private void insertFreelancerSkills(int freelancerID, List<Integer> skills) {

        workerDAO.deleteFreelancerSkills(freelancerID);

        // Insert each new Freelancer skill into the skill_set table
        for (int i = 0; i < skills.size(); i++) {
            Map<String, Object> inParameters = new HashMap<String, Object>();
            inParameters.put(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
            inParameters.put(WorkerDetails.SKILL_ID.getValue(), skills.get(i));
            workerDAO.insertFreelancerSkills(inParameters);
        }
    }

    public Worker getWorkerDetails(User user) {

        Worker worker = new Worker();
        if (user.getEmployerID() != 0) {
            worker = workerDAO.getEmployer(user.getEmployerID());
        } else if (user.getFreelancerID() != 0) {
            worker = workerDAO.getFreelancer(user.getFreelancerID());
        }
        return worker;
    }

    public void deleteEmployer(List<Integer> employers) {
        for(int i = 0; i < employers.size(); i++){
            workerDAO.deleteEmployer(employers.get(i));
        }
    }

    public void deleteFreelancer(List<Integer> freelancers) {
        for(int i = 0; i < freelancers.size(); i++){
            workerDAO.deleteFreelancer(freelancers.get(i));
        }
    }
}
