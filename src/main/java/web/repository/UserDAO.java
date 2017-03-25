package web.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import web.enumconstants.AdminDetails;

import java.util.Map;
import web.domain.*;
import web.domain.application.Admin;
import java.util.List;

/**
 * This is responsible for communicating with the database concerning users
 * @Author Ross Chalmers
 */

public interface UserDAO {
    /**
     * This is responsible for inserting a new user
     * @param inParameters the user details to be entered
     */
    void insert(Map<String, String> inParameters);

    /**
     * This is responsible for inserting a user's personal details
     * @param user the {@link User user} object containing the user's personal details
     */
    void insertPersonal(User user);

    /**
     * This is responsible for getting the password associated with a user
     * @param username the {@link String string} containing the username
     * @return the password for the username
     */
    String checkUserLogIn(String username);

    /**
     * This is responsible for retrieving user details when logging in
     * @param username the {@link String string} containing the username
     * @return the {@link User user} object containing the user details
     */
    User getLogIn(String username);

    /**
     * This is responsible for checking whether a username already exists
     * @param username the {@link String string} containing the username
     * @return <code>true</code> if the username exists. Otherwise, <code>false</code>
     */
    boolean getUsername(String username);

    /**
     * This is responsible for getting the admin password
     * @return the {@link String string} containing the password
     */
    String checkAdminLogIn();

    /**
     * This is responsible for returning the admin details
     * @return the {@link Admin admin} object containing the admin details
     */
    Admin getAdmin();

    /**
     * This is responsible for getting a user based on their employerID
     * @param id the employerID of the user to return
     * @return the {@link User user} object containing the requested user's details
     */
    User getUserByEmployer(int id);

    /**
     * This is responsible for getting a user based on their freelancerID
     * @param id the freelancerID of the user to return
     * @return the {@link User user} object containing the requested user's details
     */
    User getUserByFreelancer(int id);

    /**
     * This is responsible for retrieving the industry name
     * @return the {@link String string} containing the industry name
     */
    String getIndustryName();

    /**
     * This is responsible for getting all users
     * @return the {@link List list} of users
     */
    List<User> getAllUsers();

    /**
     * This is responsible for updating the admin password
     * @param password the {@link String string} containing the password
     */
    void updateAdminPassword(String password);


}
