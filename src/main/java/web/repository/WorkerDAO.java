package web.repository;

import java.util.*;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public interface WorkerDAO {
    Map<String, Object> insertFreelancer(String storedProc, Map<String, Object> inParameters);
    Map<String, Object> insertEmployer(String storedProc, Map<String, Object> inParameters);
    void insertEmployerSkills(String storedProc, Map<String, Object> inParameters);
    void insertFreelancerSkills(String storedProc, Map<String, Object> inParameters);
    void deleteEmployerSkills(String storedProc, Map<String, Object> inParameters);
    void deleteFreelancerSkills(String storedProc, Map<String, Object> inParameters);
    Map<String, Object> getEmployer(String storedProc, int employerID);
    Map<String, Object> getFreelancer(String storedProc, int freelancerID);
    Map<String, Object> updateEmployer(String storedProc, Map<String, Object> inParameters);
    Map<String, Object> updateFreelancer(String storedProc, Map<String, Object> inParameters);
    List<Integer> getSkills(String storedProc, int id);
}
