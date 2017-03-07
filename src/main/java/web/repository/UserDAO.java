package web.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import web.enumconstants.AdminDetails;

import java.util.Map;
import web.domain.*;
import web.domain.application.Admin;
import java.util.List;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public interface UserDAO {

    void insert(String storedProcedure, Map<String, String> inParameters);

    void insertPersonal(User user);

    String checkUserLogIn(String username);

    User getLogIn(String username);

    boolean getUsername(String username);

    boolean checkAdminLogIn(String adminPassword);

    Admin getAdmin(Map<String, Object> inParameters);

    User getUserByEmployer(int id);

    User getUserByFreelancer(int id);

    String getIndustryName();

    List<User> getAllUsers();


}
