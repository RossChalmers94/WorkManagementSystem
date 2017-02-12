package web.domain.repository;

import web.domain.User;

/**
 * Created by RossChalmers on 10/02/2017.
 */
public interface UserRepository {

    public void addUser(User user);

    public User getUser();

}
