package serviceTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.domain.User;
import org.junit.Assert;
import web.enumconstants.AdminDetails;
import web.repository.UserDAO;
import web.service.UserService;
import web.service.UserServiceImpl;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import web.domain.application.Admin;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

/**
 * Created by RossChalmers on 05/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User user;
    private User returnedUser;
    private Admin admin;
    private Admin notAdmin;
    private UserService userService;
    @Mock
    private UserDAO userDAO;
    @InjectMocks
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp(){
        this.userService = new UserServiceImpl(userDAO);
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("Employer");
        returnedUser = new User();
        returnedUser.setUsername("returnedUsername");
        returnedUser.setPassword("notpassword");
        returnedUser.getUserPersonal().setFirstname("Ross");

    }

//    @Test
//    public void testCheckUserLogIn(){
//        when(userDAO.checkUserLogIn("username")).thenReturn("password");
//        when(passwordEncoder.matches("password", "password")).thenReturn(true);
//        Assert.assertTrue(userService.checkUserLogIn(user.getUsername(), user.getPassword()));
//        Assert.assertFalse(userService.checkUserLogIn(user.getUsername(), "notpassword"));
//    }

    @Test
    public void getLogInTest(){
        when(userDAO.getLogIn(user.getUsername())).thenReturn(returnedUser);
        User newUser = userService.getLogIn(user);
        Assert.assertEquals(returnedUser, newUser);
    }

    @Test
    public void getUsernameTest(){
        when(userDAO.getUsername(user.getUsername())).thenReturn(true);
        Assert.assertTrue(userService.checkUsername(user.getUsername()));
        Assert.assertFalse(userService.checkUsername("notusername"));
    }

//    @Test
//    public void checkAdminLogInTest(){
//        Assert.assertTrue(userService.checkAdminLogIn(user.getPassword()));
//        Assert.assertFalse(userService.checkAdminLogIn("notpassword"));
//    }
//
//    @Test
//    public void getAdminLogInTest(){
//        Map<String, Object> adminDetails = new HashMap<String, Object>();
//        adminDetails.put(AdminDetails.ADMIN_USERNAME.getValue(), "admin");
//        adminDetails.put(AdminDetails.ADMIN_PASSWORD.getValue(), "password");
//        Map<String, Object> notAdminDetails = new HashMap<String, Object>();
//        adminDetails.put(AdminDetails.ADMIN_USERNAME.getValue(), "admin");
//        adminDetails.put(AdminDetails.ADMIN_PASSWORD.getValue(), "notpassword");
//        when(userDAO.getAdmin(adminDetails)).thenReturn(admin);
//        Assert.assertEquals(userService.getAdminLogIn(user), admin);
//        when(userDAO.getAdmin(notAdminDetails)).thenReturn(notAdmin);
//        Assert.assertEquals(userService.getAdminLogIn(returnedUser), notAdmin);
//    }

    @Test
    public void getUserBy(){
        when(userDAO.getUserByEmployer(1)).thenReturn(user);
        Assert.assertEquals(userService.getUserByEmployer(1), user);
        when(userDAO.getUserByFreelancer(1)).thenReturn(returnedUser);
        Assert.assertEquals(userService.getUserByFreelancer(1), returnedUser);
    }

}