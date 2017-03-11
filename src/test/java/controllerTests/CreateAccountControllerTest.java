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
import static org.mockito.Mockito.*;
import web.controllers.CreateAccountController;
import web.service.UserService;

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
public class CreateAccountControllerTest {

    @Mock
    private UserService userService;
    private MockMvc mockMvc;
    private CreateAccountController createAccountController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.createAccountController = new CreateAccountController(userService);
        when(userService.checkUsername("username")).thenReturn(true);
        this.mockMvc = MockMvcBuilders.standaloneSetup(createAccountController).build();
    }

    @Test
    public void viewCreateAccountTest() throws Exception {
        this.mockMvc
                .perform(get("/newaccount"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("newUser"))
                .andExpect(view().name("createaccount"));
    }

    @Test
    public void createNewAccountTest() throws Exception {
        this.mockMvc
                .perform(post("/newaccount").param("username", "").param("password", "").param("role", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("newUser", "username", "password", "role"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("createaccount"));
        this.mockMvc
                .perform(post("/newaccount").param("username", "username").param("password", "password")
                        .param("role", "Employer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors("newUser"))
                .andExpect(view().name("createaccount"));
        this.mockMvc
                .perform(post("/newaccount").param("username", "notusername").param("password", "password")
                        .param("role", "Employer"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("newUser"))
                .andExpect(view().name("redirect:/user/personaldetails"));
    }
}
