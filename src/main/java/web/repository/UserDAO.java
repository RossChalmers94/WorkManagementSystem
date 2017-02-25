package web.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import web.enumconstants.AdminDetails;

import java.util.Map;
import web.domain.*;
import web.domain.application.Admin;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public interface UserDAO {

    void insert(String storedProcedure, Map<String, String> inParameters);

    void insertPersonal(String storedProcedure, Map<String, String> inParameters);

    boolean checkUserLogIn(String storedProcedure, Map<String, String> inParameters);

    User get(String storedProc, Map<String, String> inParameters);

    boolean getUsername(String storedProc, String username);

    void insertEmployerID(String storedProc, Map<String, Object> inParameters);

    void insertFreelancerID(String storedProc, Map<String, Object> inParameters);

    boolean checkAdminLogIn(String storedProc, String adminPassword);

    Admin getAdmin(String storedProc, Map<String, Object> inParameters);

    User getUserByEmployer(String storedProc, int id);

    User getUserByFreelancer(String storedProc, int id);


}
