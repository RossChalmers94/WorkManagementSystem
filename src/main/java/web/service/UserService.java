package web.service;

import web.domain.User;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public interface UserService {

     void insertUser(User user);

     int checkUser(User user);

     void insertUserPersonal(User user, String username);

     boolean getLogIn(User user);

}
