package controllerTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.controllers.AdminController;
import web.domain.ManageUsers;
import web.service.MatchService;
import web.service.PreferencesService;
import web.service.UserService;
import web.service.WorkerService;
import web.domain.application.Admin;

import javax.jws.soap.SOAPBinding;

/**
 * Created by RossChalmers on 05/03/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/DefaultServlet-servlet.xml"})
public class AdminControllerTest {

    @Mock
    private MatchService matchService;
    @Mock
    private WorkerService workerService;
    @Mock
    private UserService userService;
    @Mock
    private PreferencesService preferencesService;
    private MockMvc mockMvc;
    private AdminController adminController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.adminController = new AdminController(preferencesService, matchService, userService, workerService);
        when(preferencesService.checkAdminPassword("password")).thenReturn(true);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void adminHomeTest() throws Exception {
        this.mockMvc
                .perform(get("/admin/adminhome").sessionAttr("adminUser", new Admin()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/adminhome"));
        this.mockMvc
                .perform(get("/admin/adminhome"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/newlogin"));
    }

    @Test
    public void viewManageUsersTest() throws Exception {
        this.mockMvc
                .perform(get("/admin/manageusers").sessionAttr("adminUser", new Admin()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("manageUsers", "users", "freelancers", "employers", "matches"))
                .andExpect(view().name("admin/manageusers"));
        this.mockMvc
                .perform(get("/admin/manageusers"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/newlogin"));
    }

    @Test
    public void viewAdminPasswordTest() throws Exception {
        this.mockMvc
                .perform(get("/admin/adminpassword").sessionAttr("adminUser", new Admin()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("admin", preferencesService.getAdmin()))
                .andExpect(view().name("admin/adminpassword"));
        this.mockMvc
                .perform(post("/admin/adminpassword?passwordconfirmation=")
                        .param("password", "").param("newPassword", "").param("confirmPassword", ""))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("passwordAdmin", "password", "newPassword", "confirmPassword"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("admin/adminpassword"));
        this.mockMvc
                .perform(post("/admin/adminpassword?passwordconfirmation=")
                        .param("password", "password").param("newPassword", "one").param("confirmPassword", "two"))
                .andDo(print())
                .andExpect(model().attributeHasNoErrors("passwordAdmin"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("admin/adminpassword"));
        this.mockMvc
                .perform(post("/admin/adminpassword?passwordconfirmation=")
                        .param("password", "password").param("newPassword", "one").param("confirmPassword", "one"))
                .andDo(print())
                .andExpect(model().attributeHasNoErrors("passwordAdmin"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("success"))
                .andExpect(view().name("admin/adminpassword"));
    }

    @Test
    public void configureApplicationTest() throws Exception {
        this.mockMvc
                .perform(post("/admin/adminpassword?application=")
                        .param("industryName", ""))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("applicationAdmin", "industryName"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("applicationerror"))
                .andExpect(view().name("admin/adminpassword"));
        this.mockMvc
                .perform(post("/admin/adminpassword?application=")
                        .param("industryName", "new industry"))
                .andDo(print())
                .andExpect(model().attributeHasNoErrors("applicationAdmin"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("applicationsuccess"))
                .andExpect(view().name("admin/adminpassword"));
    }

}