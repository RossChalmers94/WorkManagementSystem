package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.User;
import web.enumconstants.AdminDetails;
import web.repository.UserDAO;
import web.enumconstants.UserDetails;
import java.util.Map;
import web.domain.application.Admin;

import java.util.HashMap;

/**
 * Created by RossChalmers on 10/02/2017.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public void insertUser(User user) {
        Map<String, String> userDetails = new HashMap<String,String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername().trim());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), user.getPassword().trim());
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
        boolean login = false;
        Map<String,String> userDetails = new HashMap<String, String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), user.getPassword());
        Map<String, Object> out = userDAO.checkUserLogIn("check_user_exists", userDetails);
        int value = (Integer)(out.get(UserDetails.USER_VALUE.getValue()));
        if(value == 1){
            login = true;
        }

        return login;
    }

    public User getLogIn(User user){
        User currentUser = new User();
        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), user.getPassword());
        Map<String, Object> out = userDAO.get("user_log_in", userDetails);
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setFirstname((String) out.get(UserDetails.USER_FIRSTNAME.getValue()));
        currentUser.setLastname((String) out.get(UserDetails.USER_LASTNAME.getValue()));
        currentUser.setTelephone((String) out.get(UserDetails.USER_TELEPHONE.getValue()));
        currentUser.setEmailaddress((String) out.get(UserDetails.USER_EMAILADDRESS.getValue()));
        currentUser.setAddress((String) out.get(UserDetails.USER_ADDRESS.getValue()));
        currentUser.setPostcode((String) out.get(UserDetails.USER_POSTCODE.getValue()));
        currentUser.setTowncity((String) out.get(UserDetails.USER_TOWNCITY.getValue()));
        currentUser.setRole((String) out.get(UserDetails.USER_ROLE.getValue()));
        currentUser.setCompany((String) out.get(UserDetails.USER_COMPANY.getValue()));
        if(out.get(UserDetails.USER_RATING.getValue()) != null){
            currentUser.setRating((Integer) out.get(UserDetails.USER_RATING.getValue()));
        }
        if(out.get(UserDetails.USER_EMPLOYERID.getValue()) != null){
            currentUser.setEmployerID((Integer)out.get(UserDetails.USER_EMPLOYERID.getValue()));
        }
        if(out.get(UserDetails.USER_FREELANCERID.getValue()) != null){
            currentUser.setFreelancerID((Integer)out.get(UserDetails.USER_FREELANCERID.getValue()));
        }

        return currentUser;
    }

    public boolean checkUsername(User user){
        boolean check = false;
        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        Map<String, Object> out = userDAO.getUsername("check_username", userDetails);
        int value = (Integer) out.get(UserDetails.USER_VALUE.getValue());
        if(value == 1){
            check = true;
        }

        return check;
    }

    // Check to ensure admin password given is correct
    public boolean checkAdminLogIn(User user) {
        boolean adminLogIn = false;
        Map<String, Object> out = userDAO.checkAdminLogIn("check_admin", user.getPassword());
        int adminLogInValue = (Integer) out.get(AdminDetails.ADMIN_USERVALUE.getValue());
        if(adminLogInValue == 1){
            adminLogIn = true;
        }

        return adminLogIn;
    }

    // Get Admin Details
    public Admin getAdminLogIn(User user) {
        Admin admin = new Admin();
        Map<String, Object> adminDetails = new HashMap<String, Object>();
        adminDetails.put(AdminDetails.ADMIN_USERNAME.getValue(), "admin");
        adminDetails.put(AdminDetails.ADMIN_PASSWORD.getValue(), user.getPassword());
        Map<String, Object> out = userDAO.getAdmin("admin_log_in", adminDetails);
        admin.setAdminUsername("admin");
        admin.setPassword(user.getPassword());
        admin.setIndustryName((String) out.get(AdminDetails.INDUSTRY_NAME.getValue()));
        admin.setDatabaseServer((String) out.get(AdminDetails.DATABASE_SERVER.getValue()));
        return admin;
    }


}
