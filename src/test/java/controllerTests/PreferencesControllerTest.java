package controllerTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Matchers.any;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import web.controllers.PreferencesController;
import web.domain.User;
import web.domain.Worker;
import web.service.PreferencesService;
import web.service.UserService;
import web.service.WorkerService;
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
public class PreferencesControllerTest {

    @Mock
    private PreferencesService preferencesService;
    @Mock
    private WorkerService workerService;
    private MockMvc mockMvc;
    private PreferencesController preferencesController;
    User currentUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.preferencesController = new PreferencesController(preferencesService, workerService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(preferencesController).build();
        currentUser = new User();
        currentUser.setUsername("username");
        currentUser.setPassword("password");
        currentUser.setRole("Freelancer");
        Worker worker = new Worker();
        currentUser.setUserWorker(worker);
        when(workerService.getWorkerDetails(currentUser)).thenReturn(worker);
        when(workerService.insertWorker(currentUser)).thenReturn(1);
    }

    @Test
    public void viewPreferencesTest() throws Exception {
        this.mockMvc
                .perform(get("/user/preferencesdetails").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("newWorker", "application"))
                .andExpect(view().name("user/preferences"));
        this.mockMvc
                .perform(get("/user/preferencesdetails"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/newlogin"));
    }

    @Test
    public void submitPreferencesDetailsFreelancerTest() throws Exception {
        this.mockMvc
                .perform(post("/user/preferencesdetails?freelancer=").param("skill", "").param("salary", "")
                        .param("location", "").param("jobLength", "")
                        .param("relaxPreferences", "").param("rating", "")
                        .param("minimumMatch", "").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("newWorker", "skill", "salary", "location",
                "jobLength", "relaxPreferences", "rating", "minimumMatch"))
                .andExpect(model().attributeExists("freelancererror"))
                .andExpect(view().name("user/preferences"));
        this.mockMvc
                .perform(post("/user/preferencesdetails?freelancer=").param("skill", "1").param("salary", "2")
                        .param("location", "3").param("jobLength", "1")
                        .param("relaxPreferences", "2").param("rating", "2")
                        .param("minimumMatch", "4").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("newWorker"))
                .andExpect(view().name("redirect:/user/yourmatch"));
    }

    @Test
    public void submitPreferencesDetailsEmployerTest() throws Exception {
        this.mockMvc
                .perform(post("/user/preferencesdetails?employer=").param("jobTitle", "").param("jobDescription", "")
                        .param("skill", "").param("salary", "")
                        .param("location", "").param("jobLength", "")
                        .param("relaxPreferences", "").param("rating", "")
                        .param("minimumMatch", "").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("newWorker", "jobTitle", "jobDescription",
                        "skill", "salary", "location",
                        "jobLength", "relaxPreferences", "rating", "minimumMatch"))
                .andExpect(model().attributeExists("employererror"))
                .andExpect(view().name("user/preferences"));
        this.mockMvc
                .perform(post("/user/preferencesdetails?employer=").param("jobTitle", "test test").param("jobDescription", "test test")
                        .param("skill", "1").param("salary", "2")
                        .param("location", "3").param("jobLength", "1")
                        .param("relaxPreferences", "2").param("rating", "2")
                        .param("minimumMatch", "4").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("newWorker"))
                .andExpect(view().name("redirect:/user/yourmatch"));
    }

}
