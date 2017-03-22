package web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.domain.User;
import web.domain.application.Admin;
import web.enumconstants.UserDetails;
import web.repository.UserDAO;




@Service
public class UserServiceImpl implements UserService
{
    private UserDAO userDAO;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO)
    {
        this.userDAO = userDAO;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public void insertUser(User user) {
        Map<String, String> userDetails = new HashMap();
        userDetails.put(UserDetails.USER_NAME.getValue(), user.getUsername().trim());
        userDetails.put(UserDetails.USER_PASSWORD.getValue(), passwordEncoder.encode(user.getPassword().trim()));
        userDetails.put(UserDetails.USER_ROLE.getValue(), user.getRole());
        userDAO.insert(userDetails);
    }

    public void insertUserPersonal(User user)
    {
        userDAO.insertPersonal(user);
    }

    public boolean checkUserLogIn(String username, String password)
    {
        String outPassword = userDAO.checkUserLogIn(username);
        if (outPassword != null) {
            if (passwordEncoder.matches(password.trim(), outPassword)) {
                return true;
            }
            return false;
        }

        return false;
    }


    public User getLogIn(User user)
    {
        User currentUser = userDAO.getLogIn(user.getUsername());
        return currentUser;
    }

    public boolean checkUsername(String username) {
        boolean check = false;
        check = userDAO.getUsername(username);
        return check;
    }

    public boolean checkAdminLogIn(String password)
    {
        String adminLogIn = userDAO.checkAdminLogIn();
        if (adminLogIn != null) {
            if (passwordEncoder.matches(password, adminLogIn)) {
                return true;
            }
            return false;
        }

        return false;
    }


    public Admin getAdminLogIn(User user)
    {
        Admin admin = userDAO.getAdmin();
        return admin;
    }

    public User getUserByEmployer(int id) {
        User user = userDAO.getUserByEmployer(id);
        return user;
    }

    public User getUserByFreelancer(int id) {
        User user = userDAO.getUserByFreelancer(id);
        return user;
    }

    public String getIndustryName() {
        return userDAO.getIndustryName();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateAdminPassword(String password) {
        userDAO.updateAdminPassword(passwordEncoder.encode(password));
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}