package web.domain.repository;

import org.springframework.stereotype.Repository;
import web.domain.User;
import java.util.Map;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public interface UserDAO {

    void insert(String storedProcedure, Map<String, String> inParameters);

    int get(String storedProcedure, Map<String, String> inParameters);

    void insertPersonal(String storedProcedure, Map<String, String> inParameters);

    boolean getUserLogIn(String storedProcedure, Map<String, String> inParameters);

    void getUsername(User user);


}
