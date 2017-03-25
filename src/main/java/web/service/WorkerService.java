package web.service;

import java.util.*;
import web.domain.*;
/**
 * This service is responsible for processing the business logic associated with a worker (Freelancer and Employer)
 */
public interface WorkerService {

    /**
     * This is responsible for adding a new freelancer or employer
     * @param user the {@link User user} object that holds the preferences of the freelancer or employer
     * @return the id of the new worker (Freelancer or Employer)
     */
    int insertWorker(User user);

    /**
     * This is responsible for getting preferences of a user
     * @param user the {@link User user} object to get the preferences for
     * @return the {@link Worker worker} object that contains the preferences of the user
     */
    Worker getWorkerDetails(User user);

    /**
     * This is responsible for deleting employers
     * @param employers the {@link List list} of employers to be deleted
     */
    void deleteEmployer(List<Integer> employers);

    /**
     * This is responsible for deleting freelancers
     * @param freelancers the {@link List list} of freelancers to be deleted
     */
    void deleteFreelancer(List<Integer> freelancers);

}
