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

    public int insertWorker(Worker worker, User user) {

        int workerID = 0;

        Map<String, Object> inParameters = new HashMap<String, Object>();
        inParameters.put(WorkerDetails.SALARY.getValue(), worker.getSalary());
        inParameters.put(WorkerDetails.LOCATION.getValue(), worker.getLocation());
        inParameters.put(WorkerDetails.JOB_LENGTH.getValue(), worker.getJobLength());
        inParameters.put(WorkerDetails.RELAX_PREFERENCES.getValue(), worker.getRelaxPreferences());
        inParameters.put(WorkerDetails.RATING.getValue(), worker.getRating());
        inParameters.put(WorkerDetails.MINIMUM_MATCH.getValue(), worker.getMinimumMatch());

        if (user.getRole().equals("Employer")) {
            inParameters.put(WorkerDetails.JOB_TITLE.getValue(), worker.getJobTitle());
            inParameters.put(WorkerDetails.JOB_DESCRIPTION.getValue(), worker.getJobDescription());
            workerID = insertEmployer(user, inParameters, worker.getSkill());
        } else if (user.getRole().equals("Freelancer")) {
            workerID = insertFreelancer(user, inParameters, worker.getSkill());
        }

        return workerID;
    }

    // Insert a Employer's preferences and then add their employerID to the users table
    private int insertEmployer(User user, Map<String, Object> inParameters, List<Integer> skills) {

        int employerID;

        if (user.getEmployerID() != 0) {
            inParameters.put(WorkerDetails.EMPLOYER_ID.getValue(), user.getEmployerID());
            workerDAO.updateEmployer("update_employer_details", inParameters);
            employerID = user.getEmployerID();
        } else {
            employerID = workerDAO.insertEmployer("insert_employer", inParameters);
            // Insert EmployerID into row that corresponds to the User in the Users table
            Map<String, Object> userParameters = new HashMap<String, Object>();
            userParameters.put(UserDetails.USER_NAME.getValue(), user.getUsername());
            userParameters.put(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
            userDAO.insertEmployerID("insert_user_employer", userParameters);
        }

        // Insert Employer Skills
        insertEmployerSkills(employerID, skills);
        return employerID;
    }

    // Insert a Freelancer's preferences and then add their freelancerID to the users table
    private int insertFreelancer(User user, Map<String, Object> inParameters, List<Integer> skills) {

        int freelancerID;

        if (user.getFreelancerID() != 0) {
            inParameters.put(WorkerDetails.FREELANCER_ID.getValue(), user.getFreelancerID());
            workerDAO.updateFreelancer("update_freelancer_details", inParameters);
            freelancerID = user.getFreelancerID();
        } else {
            //Insert Freelancer Details. Return freelancerID
            freelancerID = workerDAO.insertFreelancer("insert_freelancer", inParameters);

            //Insert FreelancerID into row that corresponds to the User in the Users table
            Map<String, Object> userParameters = new HashMap<String, Object>();
            userParameters.put(UserDetails.USER_NAME.getValue(), user.getUsername());
            userParameters.put(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
            userDAO.insertFreelancerID("insert_user_freelancer", userParameters);
        }

        //Insert Freelancer Skills
        insertFreelancerSkills(freelancerID, skills);
        return freelancerID;
    }

    // Insert an Employer's skill set
    private void insertEmployerSkills(int employerID, List<Integer> skills) {

        // Delete any skills related to a specific freelancer before inserting new skills
        Map<String, Object> deleteParameters = new HashMap<String, Object>();
        deleteParameters.put(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        workerDAO.deleteEmployerSkills("delete_employer_skills", deleteParameters);

        for (int i = 0; i < skills.size(); i++) {
            Map<String, Object> inParameters = new HashMap<String, Object>();
            inParameters.put(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
            inParameters.put(WorkerDetails.SKILL_ID.getValue(), skills.get(i));
            workerDAO.insertEmployerSkills("insert_employer_skills", inParameters);
        }
    }

    // Insert a Freelancer's skill set
    private void insertFreelancerSkills(int freelancerID, List<Integer> skills) {

        // Delete any skills related to a specific freelancer before inserting new skills
        Map<String, Object> deleteParameters = new HashMap<String, Object>();
        deleteParameters.put(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        workerDAO.deleteFreelancerSkills("delete_freelancer_skills", deleteParameters);

        // Insert each new Freelancer skill into the skill_set table
        for (int i = 0; i < skills.size(); i++) {
            Map<String, Object> inParameters = new HashMap<String, Object>();
            inParameters.put(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
            inParameters.put(WorkerDetails.SKILL_ID.getValue(), skills.get(i));
            workerDAO.insertFreelancerSkills("insert_freelancer_skills", inParameters);
        }
    }

    public Worker getWorkerDetails(User user) {

        Worker worker = new Worker();
        if (user.getEmployerID() != 0) {
            worker = workerDAO.getEmployer("get_employer_details", user.getEmployerID());
        } else if (user.getFreelancerID() != 0) {
            worker = workerDAO.getFreelancer("get_freelancer_details", user.getFreelancerID());
        }
        return worker;
    }

    private List<Integer> getEmployerSkills(int employerID) {
        List<Integer> skillList = workerDAO.getSkills("SELECT skillID FROM skill_set WHERE employerID = ?", employerID);
        return skillList;
    }

    private List<Integer> getFreelancerSkills(int freelancerID) {
        List<Integer> skillList = workerDAO.getSkills("SELECT skillID FROM skill_set WHERE freelancerID = ?", freelancerID);
        return skillList;
    }
}
