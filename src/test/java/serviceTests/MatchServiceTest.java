package serviceTests;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import web.repository.MatchDAO;
import web.repository.WorkerDAO;
import web.service.MatchService;
import web.service.MatchServiceImpl;

/**
 * Created by RossChalmers on 04/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {


    private MatchService matchService;
    @Mock
    private MatchDAO matchDAO;
    @Mock
    private WorkerDAO workerDAO;

    @Before
            public void setUp(){
           // this.matchService = new MatchServiceImpl(matchDAO, workerDAO);
    }

    @Test
    public void getEmployerMatchTest(){

    }

}
