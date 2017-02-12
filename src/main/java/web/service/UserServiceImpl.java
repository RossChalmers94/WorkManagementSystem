package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.domain.User;
import web.domain.repository.UserDAO;
import web.domain.repository.UserRepository;

/**
 * Created by RossChalmers on 10/02/2017.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public void addUser(User user) {
        userDAO.insert(user);
    }

    public void updateUser(User user){

    }

    public int getUserID(User user){
        int id = userDAO.getUserID(user);
        return id;
    }
}
