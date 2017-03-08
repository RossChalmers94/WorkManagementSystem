package web.repository;

import java.util.*;
import web.domain.*;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public interface WorkerDAO {
    int insertFreelancer(Map<String, Object> inParameters);
    int insertEmployer(Map<String, Object> inParameters);
    void insertEmployerSkills(Map<String, Object> inParameters);
    void insertFreelancerSkills(Map<String, Object> inParameters);
    Worker getEmployer(int employerID);
    Worker getFreelancer(int freelancerID);
    void updateEmployer(Map<String, Object> inParameters);
    void updateFreelancer(Map<String, Object> inParameters);
    List<Integer> getSkills(String storedProc, int id);
    void deleteEmployer(int employerID);
    void deleteFreelancer(int freelancerID);
}
