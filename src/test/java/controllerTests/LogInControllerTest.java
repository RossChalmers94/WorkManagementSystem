package controllerTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.domain.User;
import web.controllers.LoginController;
import web.service.UserService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by RossChalmers on 05/03/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/DefaultServlet-servlet.xml"})
public class LogInControllerTest {

    @Mock
    private UserService userService;
    private MockMvc mockMvc;
    private LoginController loginController;
    User currentUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.loginController = new LoginController(userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        currentUser = new User();
        currentUser.setUsername("username");
        currentUser.setPassword("password");
        currentUser.setRole("Freelancer");
        when(userService.checkUserLogIn("username", "password")).thenReturn(true);
        when(userService.checkAdminLogIn("password")).thenReturn(true);
    }

    @Test
    public void viewLogInTest() throws Exception {
        this.mockMvc
                .perform(get("/newlogin").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userhome"));
        this.mockMvc
                .perform(get("/newlogin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("loginUser"))
                .andExpect(view().name("login"));
    }

    @Test
    public void newLogInTest() throws Exception {
        this.mockMvc
                .perform(post("/newlogin").param("username", "").param("password", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("loginUser", "username", "password"))
                .andExpect(model().attributeExists("details"))
                .andExpect(view().name("login"));
        this.mockMvc
                .perform(post("/newlogin").param("username", "username").param("password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("loginUser"))
                .andExpect(view().name("redirect:/user/userhome"));
        this.mockMvc
                .perform(post("/newlogin").param("username", "notusername").param("password", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors("loginUser"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("login"));
        this.mockMvc
                .perform(post("/newlogin").param("username", "admin").param("password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("loginUser"))
                .andExpect(view().name("redirect:/admin/adminhome"));
        this.mockMvc
                .perform(post("/newlogin").param("username", "admin").param("password", "notpassword"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors("loginUser"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("login"));
    }

    @Test
    public void logoutTest() throws Exception{
        this.mockMvc
                .perform(get("/logoutuser"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));
    }
}
