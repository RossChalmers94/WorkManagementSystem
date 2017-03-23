package domainTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.domain.User;
import web.repository.UserDAO;
import web.service.UserService;
import web.service.UserServiceImpl;

import static org.mockito.Mockito.when;

/**
 * Created by RossChalmers on 05/03/2017.
 */

public class UserTest {

    User user;

    @Before
    public void setUp(){
        this.user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("Employer");
    }

    @Test
    public void testCheckUserLogIn(){
        Assert.assertSame(user.getUsername(), "username");
    }

}