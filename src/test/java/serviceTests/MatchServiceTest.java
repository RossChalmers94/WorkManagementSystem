package serviceTests;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import web.domain.Worker;
import web.repository.MatchDAO;
import web.repository.UserDAO;
import web.repository.WorkerDAO;
import web.service.MatchService;
import web.service.MatchServiceImpl;
import web.domain.User;
import web.domain.Match;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RossChalmers on 04/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private MatchDAO matchDAO;
    @Mock
    private WorkerDAO workerDAO;
    private MatchService matchService;
    User currentUser;
    Match matchOne;
    Match matchTwo;
    List<Worker> freelancers;
    List<Worker> employers;
    Worker currentFreelancer;
    Worker currentEmployer;
    Worker freelancerOne;
    Worker freelancerTwo;
    Worker freelancerThree;
    Worker employerOne;
    Worker employerTwo;
    Worker employerThree;

    @Before
    public void setUp(){
        this.matchService = new MatchServiceImpl(matchDAO,workerDAO,userDAO);
        currentUser = new User();
        currentEmployer = new Worker();
        matchOne = new Match();
        matchTwo = new Match();
        freelancerOne = new Worker();
        freelancerOne.setWorkerID(1); freelancerOne.setSalary(1); freelancerOne.setLocation(1); freelancerOne.setSalary(1);
        freelancerOne.setJobLength(1); freelancerOne.setSkill(Arrays.asList(1,2,3)); freelancerOne.setMinimumMatch(100);
        freelancerOne.setPreviousRating(1);
        freelancerTwo = new Worker();
        freelancerTwo.setWorkerID(2); freelancerTwo.setSalary(1); freelancerTwo.setLocation(2); freelancerTwo.setSalary(1);
        freelancerTwo.setJobLength(1); freelancerTwo.setSkill(Arrays.asList(1,2,3)); freelancerTwo.setMinimumMatch(80);
        freelancerTwo.setPreviousRating(1);
        freelancerThree = new Worker();
        freelancerThree.setWorkerID(3); freelancerThree.setSalary(1); freelancerThree.setLocation(2); freelancerThree.setSalary(1);
        freelancerThree.setJobLength(1); freelancerThree.setSkill(Arrays.asList(1,2,3)); freelancerThree.setMinimumMatch(60);
        freelancerThree.setPreviousRating(1);
        freelancers = new ArrayList<Worker>();
        freelancers.add(freelancerOne); freelancers.add(freelancerTwo); freelancers.add(freelancerThree);
    }

    @Test
    public void getEmployerMatchTest(){

        when(matchDAO.getFreelancers(any(String.class))).thenReturn(freelancers);

        currentEmployer.setWorkerID(1); currentEmployer.setSalary(1); currentEmployer.setLocation(1); currentEmployer.setSalary(1);
        currentEmployer.setJobLength(1); currentEmployer.setSkill(Arrays.asList(1,2,3)); currentEmployer.setMinimumMatch(100);
        currentEmployer.setPreviousRating(1);
        currentUser.setUserWorker(currentEmployer);
        when(matchDAO.getMatch(freelancerOne)).thenReturn(matchOne);
        Match newMatch = matchService.getEmployerMatch(currentUser);
        Assert.assertEquals(newMatch, matchOne);

        currentEmployer.setWorkerID(1); currentEmployer.setSalary(1); currentEmployer.setLocation(2); currentEmployer.setSalary(1);
        currentEmployer.setJobLength(1); currentEmployer.setSkill(Arrays.asList(1,2,3)); currentEmployer.setMinimumMatch(100);
        currentEmployer.setPreviousRating(1);
        currentUser.setUserWorker(currentEmployer);
        when(matchDAO.getMatch(freelancerTwo)).thenReturn(matchTwo);
        Match newMatchTwo = matchService.getEmployerMatch(currentUser);
        Assert.assertEquals(newMatchTwo, matchTwo);
    }

}
