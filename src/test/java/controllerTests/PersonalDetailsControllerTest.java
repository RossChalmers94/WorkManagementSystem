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
import web.controllers.PersonalDetailsController;
import web.domain.User;
import web.service.UserService;

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
public class PersonalDetailsControllerTest {

    @Mock
    private UserService userService;
    private MockMvc mockMvc;
    private PersonalDetailsController personalDetailsController;
    User currentUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.personalDetailsController = new PersonalDetailsController(userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(personalDetailsController).build();
        currentUser = new User();
        currentUser.setUsername("username");
        currentUser.setPassword("password");
        currentUser.setRole("Freelancer");
        when(userService.getLogIn(currentUser)).thenReturn(currentUser);
    }

    @Test
    public void viewPersonalDetailsTest() throws Exception {
        this.mockMvc
                .perform(get("/user/personaldetails").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userPersonal"))
                .andExpect(view().name("/user/personal"));
        this.mockMvc
                .perform(get("/user/personaldetails"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void submitPersonalDetailsFreelancerTest() throws Exception {
        this.mockMvc
                .perform(post("/user/personaldetails?freelancer=").param("firstname", "").param("lastname", "")
                        .param("telephone", "").param("emailaddress", "")
                        .param("address", "").param("postcode", "")
                        .param("towncity", "").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userPersonal", "firstname", "lastname", "telephone",
                "emailaddress", "address", "postcode", "towncity"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("user/personal"));
        this.mockMvc
                .perform(post("/user/personaldetails?freelancer=").param("firstname", "Ross Andrew").param("lastname", "Chalmers")
                        .param("telephone", "checkingforerrors").param("emailaddress", "checkingforerrors")
                        .param("address", "checkingforerrors").param("postcode", "checkingforerrors")
                        .param("towncity", "checkingforerrors").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userPersonal", "telephone", "emailaddress", "postcode"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("user/personal"));
        this.mockMvc
                .perform(post("/user/personaldetails?freelancer=").param("firstname", "Ross Andrew").param("lastname", "Chalmers")
                        .param("telephone", "0749320943").param("emailaddress", "testeremail@hotmail.com")
                        .param("address", "checkingforerrors").param("postcode", "G74 BYT")
                        .param("towncity", "checkingforerrors").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("userPersonal"))
                .andExpect(view().name("redirect:/user/preferences"));
    }

    @Test
    public void submitPersonalDetailsEmployerTest() throws Exception {
        this.mockMvc
                .perform(post("/user/personaldetails?employer=").param("firstname", "").param("lastname", "")
                        .param("telephone", "").param("emailaddress", "")
                        .param("address", "").param("postcode", "")
                        .param("towncity", "").param("company", "").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userPersonal", "firstname", "lastname", "telephone",
                        "emailaddress", "address", "postcode", "towncity", "company"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("user/personal"));
        this.mockMvc
                .perform(post("/user/personaldetails?employer=").param("firstname", "Ross Andrew").param("lastname", "Chalmers")
                        .param("telephone", "checkingforerrors").param("emailaddress", "checkingforerrors")
                        .param("address", "checkingforerrors").param("postcode", "checkingforerrors")
                        .param("towncity", "checkingforerrors").param("company", "A new company").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("userPersonal", "telephone", "emailaddress", "postcode"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("user/personal"));
        this.mockMvc
                .perform(post("/user/personaldetails?employer=").param("firstname", "Ross Andrew").param("lastname", "Chalmers")
                        .param("telephone", "0749320943").param("emailaddress", "testeremail@hotmail.com")
                        .param("address", "checkingforerrors").param("postcode", "G74 BYT")
                        .param("towncity", "checkingforerrors").param("company", "A new company").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("userPersonal"))
                .andExpect(view().name("redirect:/user/preferences"));
    }

}
