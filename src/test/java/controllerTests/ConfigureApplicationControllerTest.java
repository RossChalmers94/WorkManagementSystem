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
import web.controllers.AdminController;
import web.controllers.ConfigureApplicationController;
import web.domain.application.Admin;
import web.service.MatchService;
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
public class ConfigureApplicationControllerTest {

    @Mock
    private PreferencesService preferencesService;
    private MockMvc mockMvc;
    private ConfigureApplicationController configureApplicationController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.configureApplicationController = new ConfigureApplicationController(preferencesService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(configureApplicationController).build();
    }

    @Test
    public void viewConfigureApplicationTest() throws Exception {
        this.mockMvc
                .perform(get("/admin/configureapplication").sessionAttr("adminUser", new Admin()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("newApplication", "configureSkills", "configureLocations",
                        "configureSalaries", "configureJobLengths"))
                .andExpect(view().name("admin/configureapplication"));
        this.mockMvc
                .perform(get("/admin/configureapplication"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeDoesNotExist("newApplication", "configureSkills", "configureLocations",
                        "configureSalaries", "configureJobLengths"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void addDeleteSkillsTest() throws Exception {
        this.mockMvc
                .perform(post("/admin/configureapplication?addskill=").param("skillName", ""))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasFieldErrors("configureSkills", "skillName"))
                .andExpect(model().attributeExists("error", "skills"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?addskill=").param("skillName", "sdfasdfdsf221"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasFieldErrors("configureSkills", "skillName"))
                .andExpect(model().attributeExists("error", "skills"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?addskill=").param("skillName", "overthelimitoverthelimit"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasFieldErrors("configureSkills", "skillName"))
                .andExpect(model().attributeExists("error", "skills"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?addskill=").param("skillName", "New York"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("configureSkills"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?deleteskill="))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/configureapplication"));
    }

    @Test
    public void addDeleteLocationsTest() throws Exception {
        this.mockMvc
                .perform(post("/admin/configureapplication?addlocation=").param("locationName", ""))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasFieldErrors("configureLocations", "locationName"))
                .andExpect(model().attributeExists("error", "locations"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?addlocation=").param("locationName", "overthelimitoverthelimit"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasFieldErrors("configureLocations", "locationName"))
                .andExpect(model().attributeExists("error", "locations"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?addlocation=").param("locationName", "dsfsdfs32"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasFieldErrors("configureLocations", "locationName"))
                .andExpect(model().attributeExists("error", "locations"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?addlocation=").param("locationName", "New York"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("configureLocations"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?addlocation=").param("locationName", "banter"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeHasNoErrors("configureLocations"))
                .andExpect(view().name("redirect:/admin/configureapplication"));
        this.mockMvc
                .perform(post("/admin/configureapplication?deletelocation="))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/configureapplication"));
    }

}
