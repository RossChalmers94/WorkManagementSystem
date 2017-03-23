package serviceTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.domain.User;
import web.service.WorkerServiceImpl;
import web.enumconstants.AdminDetails;
import web.repository.WorkerDAO;
import web.service.WorkerService;
import web.domain.Worker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * Created by RossChalmers on 05/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class WorkerServiceTest {

    private WorkerService workerService;
    @Mock
    private WorkerDAO workerDAO;
    private User userEmployer;
    private User userNewEmployer;
    private User userFreelancer;
    private User userNewFreelancer;
    private Worker employer;
    private Worker freelancer;

    @Before
    public void setUp(){
        this.workerService = new WorkerServiceImpl(workerDAO);
        userEmployer = new User();
        userEmployer.setRole("Employer");
        userEmployer.setEmployerID(1);
        userNewEmployer = new User();
        userNewEmployer.setRole("Employer");
        userFreelancer = new User();
        userFreelancer.setFreelancerID(1);
        userFreelancer.setRole("Freelancer");
        userNewFreelancer = new User();
        userNewFreelancer.setRole("Freelancer");
        freelancer = new Worker();
        freelancer.setWorkerID(1);
        freelancer.setSkill(Arrays.asList(1,2,3));
        employer = new Worker();
        employer.setWorkerID(1);
        employer.setSkill(Arrays.asList(1,3,2));
        userEmployer.setUserWorker(employer);
        userNewEmployer.setUserWorker(employer);
        userFreelancer.setUserWorker(freelancer);
        userNewFreelancer.setUserWorker(freelancer);
    }

    @Test
    public void getWorkerDetailsTest(){
        when(workerDAO.getEmployer(1)).thenReturn(employer);
        when(workerDAO.getFreelancer(1)).thenReturn(freelancer);
        Assert.assertEquals(workerService.getWorkerDetails(userEmployer), employer);
        Assert.assertNotEquals(workerService.getWorkerDetails(userEmployer), freelancer);
        Assert.assertEquals(workerService.getWorkerDetails(userFreelancer), freelancer);
        Assert.assertNotEquals(workerService.getWorkerDetails(userFreelancer), employer);
    }

    @Test
    public void insertWorkerTest(){
        int workerID;
        workerID = workerService.insertWorker(userEmployer);
        Assert.assertSame(workerID, 1);
        when(workerDAO.insertEmployer(Matchers.any(Map.class))).thenReturn(2);
        workerID = workerService.insertWorker(userNewEmployer);
        Assert.assertSame(workerID, 2);
        workerID = workerService.insertWorker(userFreelancer);
        Assert.assertSame(workerID, 1);
        when(workerDAO.insertFreelancer(Matchers.any(Map.class))).thenReturn(2);
        workerID = workerService.insertWorker(userNewFreelancer);
        Assert.assertSame(workerID, 2);
    }

    @Test
    public void deleteTest(){
        workerService.deleteEmployer(Arrays.asList(employer.getWorkerID()));
        workerService.deleteFreelancer(Arrays.asList(freelancer.getWorkerID()));
    }


}