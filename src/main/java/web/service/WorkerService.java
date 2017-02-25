package web.service;

import java.util.*;
import web.domain.*;
/**
 * Created by RossChalmers on 20/02/2017.
 */
public interface WorkerService {

    void insertWorker(Worker worker, User user);

    Worker getWorkerDetails(User user);

    Match getFreelancerMatch(User user);

    Match getEmployerMatch(User user);
}
