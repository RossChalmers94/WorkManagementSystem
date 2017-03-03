package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.domain.User;
import web.enumconstants.AdminDetails;
import web.repository.UserDAO;
import web.enumconstants.UserDetails;
import java.util.*;
import web.domain.application.Admin;

import java.util.HashMap;

/**
 * Created by RossChalmers on 10/02/2017.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void insertUser(User user) {
        Map<String, String> userDetails = new HashMap<String,String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername().trim());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), passwordEncoder.encode(user.getPassword().trim()));
        userDetails.put(UserDetails.USER_ROLE.getValue(), user.getRole());
        userDAO.insert("insert_user", userDetails);
    }


    public void insertUserPersonal(User user, String username){
        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put("firstName", user.getFirstname());
        userDetails.put("lastName", user.getLastname());
        userDetails.put("telephoneNo", user.getTelephone());
        userDetails.put("emailAddress", user.getEmailaddress());
        userDetails.put("address", user.getAddress());
        userDetails.put("postcode", user.getPostcode());
        userDetails.put("townCity", user.getTowncity());
        userDetails.put("company", user.getCompany());
        userDetails.put("username", username);
        userDAO.insertPersonal("insert_user_personal", userDetails);
    }

    public boolean checkUserLogIn(User user){
        String password;
        Map<String,String> userDetails = new HashMap<String, String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        password = userDAO.checkUserLogIn("check_user_exists", userDetails);
        if(passwordEncoder.matches(user.getPassword().trim(), password)){
            return true;
        } else {
            return false;
        }
    }

    public User getLogIn(User user){
        User currentUser;
        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        currentUser = userDAO.get("user_log_in", userDetails);
        return currentUser;
    }

    public boolean checkUsername(User user){
        boolean check = false;
        check = userDAO.getUsername("check_username", user.getUsername());
        return check;
    }

    // Check to ensure admin password given is correct
    public boolean checkAdminLogIn(User user) {
        boolean adminLogIn = false;
        adminLogIn = userDAO.checkAdminLogIn("check_admin", user.getPassword());
        return adminLogIn;
    }

    // Get Admin Details
    public Admin getAdminLogIn(User user) {
        Admin admin = new Admin();
        Map<String, Object> adminDetails = new HashMap<String, Object>();
        adminDetails.put(AdminDetails.ADMIN_USERNAME.getValue(), "admin");
        adminDetails.put(AdminDetails.ADMIN_PASSWORD.getValue(), user.getPassword());
        admin = userDAO.getAdmin("admin_log_in", adminDetails);
        return admin;
    }

    public User getUserByEmployer(int id){
        User user = userDAO.getUserByEmployer("get_user_by_employer", id);
        return user;
    }

    public User getUserByFreelancer(int id){
        User user = userDAO.getUserByFreelancer("get_user_by_freelancer", id);
        return user;
    }

    public String getIndustryName() {
        return userDAO.getIndustryName();
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
}
