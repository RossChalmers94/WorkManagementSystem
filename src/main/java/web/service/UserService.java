package web.service;

import web.domain.User;
import web.domain.application.Admin;

import java.util.*;

/**
 * This service is responsible for processing the business logic associated with a user
 *
 */
public interface UserService {
     /**
      * This creates a new account with the user details
      * @param user the {@link User user} object that holds the user details
      */
     void insertUser(User user);
     /**
      * This inserts or alters a user's personal details
      * @param user the {@link User user} object that holds the user personal details
      */
     void insertUserPersonal(User user);

     /**
      * This gets a user's details after logging in
      * @param user the {@link User user} object that holds the user's username
      * @return the {@link User user} object that has been updated with the user's details
      */
     User getLogIn(User user);

     /**
      * This checks that a username and password given is an account that has been created
      * @param username the {@link String string} that holds the username to check
      * @param password the {@link String string} that holds the password to check
      * @return <code>true</code> if the username and password is correct. Otherwise <code>false</code>.
      */
     boolean checkUserLogIn(String username, String password);

     /**
      * This checks if a username already exists
      * @param username the {@link String string} that holds the username to check
      * @return <code>true</code> if the username exists. Otherwise <code>false</code>.
      */
     boolean checkUsername(String username);

     /**
      * This checks if the admin password entered is correct
      * @param password the {@link String string} that holds the password to check
      * @return <code>true</code> if the password is correct. Otherwise <code>false</code>.
      */
     boolean checkAdminLogIn(String password);

     /**
      * This gets the admin details
      * @return the {@link Admin admin} object that holds all the admin details
      */
     Admin getAdminLogIn();

     /**
      * This gets the industry name set in the application
      * @return the {@link String string} that contains the industry name
      */
     String getIndustryName();

     /**
      * This gets all the users stored
      * @return returns a {@link List list} of users
      */
     List<User> getAllUsers();

     /**
      * This updates the admin password
      * @param password the {@link String string} that contains the password to update
      */
     void updateAdminPassword(String password);

}
