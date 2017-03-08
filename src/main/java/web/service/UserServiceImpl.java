package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void insertUser(User user) {
        Map<String, String> userDetails = new HashMap<String,String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername().trim());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), passwordEncoder.encode(user.getPassword().trim()));
        userDetails.put(UserDetails.USER_ROLE.getValue(), user.getRole());
        userDAO.insert(userDetails);
    }


    public void insertUserPersonal(User user){
        userDAO.insertPersonal(user);
    }

    public boolean checkUserLogIn(String username, String password){
        String outPassword;
        outPassword = userDAO.checkUserLogIn(username);
        if(passwordEncoder.matches(password.trim(), outPassword)){
            return true;
        } else {
            return false;
        }
    }

    public User getLogIn(User user){
        User currentUser;
        currentUser = userDAO.getLogIn(user.getUsername());
        return currentUser;
    }

    public boolean checkUsername(String username){
        boolean check = false;
        check = userDAO.getUsername(username);
        return check;
    }

    // Check to ensure admin password given is correct
    public boolean checkAdminLogIn(String password) {
        boolean adminLogIn;
        adminLogIn = userDAO.checkAdminLogIn(password);
        return adminLogIn;
    }

    // Get Admin Details
    public Admin getAdminLogIn(User user) {
        Admin admin = new Admin();
        Map<String, Object> adminDetails = new HashMap<String, Object>();
        adminDetails.put(AdminDetails.ADMIN_USERNAME.getValue(), "admin");
        adminDetails.put(AdminDetails.ADMIN_PASSWORD.getValue(), user.getPassword());
        admin = userDAO.getAdmin(adminDetails);
        return admin;
    }

    public User getUserByEmployer(int id){
        User user = userDAO.getUserByEmployer(id);
        return user;
    }

    public User getUserByFreelancer(int id){
        User user = userDAO.getUserByFreelancer(id);
        return user;
    }

    public String getIndustryName() {
        return userDAO.getIndustryName();
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
}
