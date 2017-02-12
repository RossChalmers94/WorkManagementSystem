package web.domain.repository;

import web.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by RossChalmers on 10/02/2017.
 */
@Repository
public class UserImpl implements UserRepository {

    private User user;

    public void addUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
