package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.Match;
import web.domain.User;
import web.domain.Worker;
import web.repository.MatchDAO;
import web.repository.UserDAO;
import web.repository.WorkerDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RossChalmers on 26/02/2017.
 */
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchDAO matchDAO;
    @Autowired
    private WorkerDAO workerDAO;

    public Match getEmployerMatch(User user) {

        Worker currentWorker = workerDAO.getEmployer("get_employer_details", user.getEmployerID());
        List<Worker> workers = matchDAO.getFreelancers("SELECT freelancerID, salary, location, " +
                "jobLength, rating, minimumMatch FROM freelancer WHERE jobMatch IS NULL AND previousMatch IS NULL");

        Worker forProcess = getWorker(workers, currentWorker);
        if (forProcess == null) {
            return null;
        } else {
            matchDAO.insertMatch(user.getEmployerID(), forProcess.getWorkerID());
            return matchDAO.getMatch(forProcess);
        }
    }

    public Match getFreelancerMatch(User user) {

        Worker currentWorker = workerDAO.getFreelancer("get_freelancer_details", user.getFreelancerID());
        List<Worker> workers = matchDAO.getEmployers("SELECT employerID, salary, location, " +
                "jobLength, jobTitle, jobDescription, minimumMatch FROM employer WHERE jobMatch IS NULL AND previousMatch IS NULL");

        Worker forProcess = getWorker(workers, currentWorker);
        if (forProcess == null) {
            return null;
        } else {
            matchDAO.insertMatch(forProcess.getWorkerID(), user.getFreelancerID());
            return matchDAO.getMatch(forProcess);
        }
    }

    private Worker getWorker(List<Worker> workers, Worker currentWorker) {

        List<Worker> possibleMatches = new ArrayList<Worker>();
        Worker worker = new Worker();

        if (!workers.isEmpty()) {
            for (int i = 0; i < workers.size(); i++) {
                float compareWorkers = compareTo(currentWorker, workers.get(i));
                if (compareWorkers >= currentWorker.getMinimumMatch() && compareWorkers >= workers.get(i).getMinimumMatch()) {
                    Worker possibleWorker = workers.get(i);
                    possibleWorker.setCompareValue(compareWorkers);
                    possibleMatches.add(possibleWorker);
                }
            }

            if (possibleMatches.isEmpty()) {
                return null;
            } else {

                float counter = 0;
                for (int j = 0; j < possibleMatches.size(); j++) {
                    if (possibleMatches.get(j).getCompareValue() > counter) {
                        counter = possibleMatches.get(j).getCompareValue();
                        worker = possibleMatches.get(j);
                    }
                }

                return worker;
            }
        } else {
            return null;
        }
    }

    public Match getExistingEmployerMatch(int matchID) {
        int freelancerID = matchDAO.getEmployerMatch(matchID);
        Worker worker = workerDAO.getFreelancer("get_freelancer_details", freelancerID);
        return matchDAO.getMatch(worker);
    }

    public Match getExistingFreelancerMatch(int matchID) {
        int employerID = matchDAO.getFreelancerMatch(matchID);
        Worker worker = workerDAO.getEmployer("get_employer_details", employerID);
        return matchDAO.getMatch(worker);
    }

    public void completeMatch(User user, int rating, int matchID) {
        if (user.getEmployerID() != 0) {
            int freelancerID = matchDAO.getEmployerMatch(matchID);
            matchDAO.completeEmployerMatch(user.getEmployerID(), freelancerID, rating, matchID);
        } else if (user.getFreelancerID() != 0) {
            int employerID = matchDAO.getFreelancerMatch(matchID);
            matchDAO.completeFreelancerMatch(user.getFreelancerID(), employerID, rating, matchID);
        }
    }

    public void setPreviousRating(User user, int rating, int id) {
        if (user.getEmployerID() != 0) {
            matchDAO.setPreviousFreelancerRating(user.getEmployerID(), id, rating);
        } else if (user.getFreelancerID() != 0) {
            matchDAO.setPreviousEmployerRating(id, user.getFreelancerID(), rating);
        }
    }

    public List<Worker> getEmployers() {
        return matchDAO.getEmployers("SELECT employerID, salary, location, " +
                "jobLength, jobTitle, minimumMatch, jobDescription FROM employer");
    }

    public List<Worker> getFreelancers() {
        return matchDAO.getFreelancers("SELECT freelancerID, salary, location, " +
                "jobLength, minimumMatch FROM freelancer");
    }

    public List<Match> getAllMatches() {
        return matchDAO.getAllMatches();
    }

    public void deleteMatch(List<Integer> matches) {
        for(int i = 0; i < matches.size(); i++){
            matchDAO.deleteMatch(matches.get(i));
        }
    }

    private float compareTo(Worker currentWorker, Worker worker){

        float counter = 0;

        if(currentWorker.getSalary() == worker.getSalary()){
            counter = counter + 1;
        }

        if(currentWorker.getLocation() == worker.getLocation()){
            counter = counter + 1;
        }

        if(currentWorker.getJobLength() == worker.getJobLength()){
            counter = counter + 1;
        }

        if(currentWorker.getRating() >= worker.getPreviousMatch()){
            counter = counter + 1;
        }

        for(int i = 0; i < currentWorker.getSkill().size(); i++){
            if(worker.getSkill().contains(currentWorker.getSkill().get(i))){
                counter = counter + 1;
            }
        }

        float size = (currentWorker.getSkill().size() + 4);
        float percentage = (counter/size) * 100;
        return percentage;
    }
}
