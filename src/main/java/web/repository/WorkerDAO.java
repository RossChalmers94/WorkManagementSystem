package web.repository;

import java.util.*;
import web.domain.*;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public interface WorkerDAO {
    int insertFreelancer(String storedProc, Map<String, Object> inParameters);
    int insertEmployer(String storedProc, Map<String, Object> inParameters);
    void insertEmployerSkills(String storedProc, Map<String, Object> inParameters);
    void insertFreelancerSkills(String storedProc, Map<String, Object> inParameters);
    Worker getEmployer(String storedProc, int employerID);
    Worker getFreelancer(String storedProc, int freelancerID);
    void updateEmployer(String storedProc, Map<String, Object> inParameters);
    void updateFreelancer(String storedProc, Map<String, Object> inParameters);
    List<Integer> getSkills(String storedProc, int id);
    void deleteEmployer(String storedProc, int employerID);
    void deleteFreelancer(String storedProc, int freelancerID);
}
