package web.service;

import java.util.*;
import web.domain.*;
/**
 * Created by RossChalmers on 20/02/2017.
 */
public interface WorkerService {

    int insertWorker(User user);

    Worker getWorkerDetails(User user);

    void deleteEmployer(List<Integer> employers);

    void deleteFreelancer(List<Integer> freelancers);

}
