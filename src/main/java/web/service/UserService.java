package web.service;

import web.domain.User;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public interface UserService {

     void addUser(User user);

     void updateUser(User user);

     int getUserID(User user);

}
