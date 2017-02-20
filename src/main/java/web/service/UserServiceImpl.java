package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.User;
import web.domain.repository.UserDAO;
import web.enumconstants.UserDetails;
import java.util.Map;

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
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername().toString());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), user.getPassword().toString());
        userDetails.put(UserDetails.USER_ROLE.getValue(), user.getRole().toString());
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

    public Map<String, Object> getLogIn(User user){
        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), user.getPassword());
        Map<String, Object> out = userDAO.get("user_log_in", userDetails);

        return out;
    }

    public boolean checkUsername(User user){
        boolean check = false;
        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername());
        Map<String, Object> out = userDAO.get("check_username", userDetails);
        int value = (Integer) out.get(UserDetails.USER_VALUE.getValue());
        if(value == 1){
            check = true;
        }

        return check;
    }
}
