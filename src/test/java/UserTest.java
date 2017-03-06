import org.junit.Before;
import org.junit.Test;
import web.domain.User;
import org.junit.Assert;
/**
 * Created by RossChalmers on 05/03/2017.
 */

public class UserTest {

    User user;

    @Before
    public void setUp(){
        user = new User();
    }

    @Test
    public void personalDetailsTest(){
        user.setFirstname("Ross");
        Assert.assertEquals("Ross", user.getFirstname());
        user.setLastname("Chalmers");
        Assert.assertEquals("Chalmers", user.getLastname());
        user.setTelephone("07912345432");
        Assert.assertEquals("07912345432", user.getTelephone());
        user.setEmailaddress("tester@email.com");
        Assert.assertEquals("tester@email.com", user.getEmailaddress());
        user.setAddress("1 Yew Tree");
        Assert.assertEquals("1 Yew Tree", user.getAddress());
        user.setPostcode("G84 GBY");
        Assert.assertEquals("G84 GBY", user.getPostcode());
        user.setTowncity("Glasgow");
        Assert.assertEquals("Glasgow", user.getTowncity());
    }

    @Test
    public void userInformationTest(){
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("Freelancer");
        Assert.assertEquals("username", user.getUsername());
        Assert.assertEquals("password", user.getPassword());
        Assert.assertEquals("Freelancer", user.getRole());
        user.setUsername("change");
        Assert.assertNotEquals("username", user.getUsername());
        Assert.assertEquals("change", user.getUsername());
        user.setPassword("changepassword");
        Assert.assertNotEquals("password", user.getPassword());
        Assert.assertEquals("changepassword", user.getPassword());
        user.setRole("Employer");
        Assert.assertNotEquals("Freelancer", user.getRole());
        Assert.assertEquals("Employer", user.getRole());
    }
}
