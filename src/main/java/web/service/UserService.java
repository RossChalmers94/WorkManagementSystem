package web.service;

import web.domain.User;
import java.util.*;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public interface UserService {

     void insertUser(User user);

     void insertUserPersonal(User user, String username);

     Map<String, Object> getLogIn(User user);

     boolean checkUserLogIn(User user);

     boolean checkUsername(User user);

}
