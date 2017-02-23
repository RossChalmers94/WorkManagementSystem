package web.repository;

import java.util.Map;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public interface UserDAO {

    void insert(String storedProcedure, Map<String, String> inParameters);

    void insertPersonal(String storedProcedure, Map<String, String> inParameters);

    Map<String, Object> checkUserLogIn(String storedProcedure, Map<String, String> inParameters);

    Map<String, Object> get(String storedProc, Map<String, String> inParameters);

    Map<String, Object> getUsername(String storedProc, Map<String, String> inParameters);

    void insertEmployerID(String storedProc, Map<String, Object> inParameters);

    void insertFreelancerID(String storedProc, Map<String, Object> inParameters);


}