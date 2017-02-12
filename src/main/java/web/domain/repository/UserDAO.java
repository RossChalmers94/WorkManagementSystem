package web.domain.repository;

import org.springframework.stereotype.Repository;
import web.domain.User;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public interface UserDAO {

    void insert(User user);

    int getUserID(User user);

    void update(User user);

    void delete(User user);

    void getUsername(User user);


}
