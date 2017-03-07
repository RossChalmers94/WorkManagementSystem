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
import web.controllers.MatchController;
import web.controllers.PreferencesController;
import web.domain.User;
import web.domain.Worker;
import web.service.MatchService;
import web.service.PreferencesService;
import web.service.UserService;
import web.service.WorkerService;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by RossChalmers on 05/03/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/DefaultServlet-servlet.xml"})
public class MatchControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private WorkerService workerService;
    @Mock
    private MatchService matchService;
    private MockMvc mockMvc;
    private MatchController matchController;
    User currentUser;
    User employerUser;
    User freelancerUser;
    Worker workerNoMatch;
    Worker workerMatch;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.matchController = new MatchController(userService, workerService, matchService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(matchController).build();
        currentUser = new User();
        employerUser = new User();
        employerUser.setEmployerID(1);
        freelancerUser = new User();
        freelancerUser.setFreelancerID(1);
        workerNoMatch = new Worker();
        workerNoMatch.setJobMatch(1);
        workerMatch = new Worker();
    }

    @Test
    public void viewMatchTest() throws Exception {
        this.mockMvc
                .perform(get("/user/yourmatch"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void viewFreelancerMatchTest() throws Exception {
        when(userService.getLogIn(any(User.class))).thenReturn(freelancerUser);
        when(workerService.getWorkerDetails(any(User.class))).thenReturn(workerNoMatch);
        this.mockMvc
                .perform(get("/user/yourmatch").sessionAttr("currentUser", currentUser))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/nomatch"));
    }

}
