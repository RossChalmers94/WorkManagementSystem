package web.service;

import web.domain.User;
import web.domain.application.Admin;

import java.util.*;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public interface UserService {

     void insertUser(User user);

     void insertUserPersonal(User user);

     User getLogIn(User user);

     boolean checkUserLogIn(String username, String password);

     boolean checkUsername(String username);

     boolean checkAdminLogIn(String password);

     Admin getAdminLogIn(User user);

     User getUserByEmployer(int id);

     User getUserByFreelancer(int id);

     String getIndustryName();

     List<User> getAllUsers();

}
