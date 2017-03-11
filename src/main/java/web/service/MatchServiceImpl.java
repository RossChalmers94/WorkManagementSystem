package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.Match;
import web.domain.User;
import web.domain.Worker;

import java.util.HashMap;
import java.util.Map;

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


    private MatchDAO matchDAO;
    private WorkerDAO workerDAO;
    private UserDAO userDAO;

    @Autowired
    public MatchServiceImpl(MatchDAO matchDAO, WorkerDAO workerDAO, UserDAO userDAO) {
        this.matchDAO = matchDAO;
        this.workerDAO = workerDAO;
        this.userDAO = userDAO;
    }

    public Match getEmployerMatch(User user) {
        List<Worker> workers = matchDAO.getFreelancers("SELECT freelancerID, salary, location, " +
                "jobLength, rating, minimumMatch, previousRating FROM freelancer WHERE jobMatch IS NULL AND previousMatch IS NULL");

        List<Worker> possibleWorkers = new ArrayList<Worker>();
        for (Worker worker : workers) {
            int compareValue = getWorker(worker, user.getUserWorker());
            if(compareValue > 0) {
                worker.setCompareValue(compareValue);
                possibleWorkers.add(worker);
            }
        }

        if(possibleWorkers.isEmpty()){
            return null;
        } else {
            Worker bestWorker = findBestWorker(possibleWorkers);
            matchDAO.insertMatch(user.getEmployerID(), bestWorker.getWorkerID());
            Match match = matchDAO.getMatch(bestWorker);
            match.setUserMatch(userDAO.getUserByFreelancer(match.getMatchID()).getUserPersonal());
            return match;
        }
    }

    public Match getFreelancerMatch(User user) {

        List<Worker> workers = matchDAO.getEmployers("SELECT employerID, salary, location, " +
                "jobLength, jobTitle, jobDescription, minimumMatch, previousRating FROM employer WHERE jobMatch IS NULL AND previousMatch IS NULL");

        List<Worker> possibleWorkers = new ArrayList<Worker>();
        for (Worker worker : workers) {
            int compareValue = getWorker(worker, user.getUserWorker());
            if(compareValue > 0) {
                worker.setCompareValue(compareValue);
                possibleWorkers.add(worker);
            }
        }

        if(possibleWorkers.isEmpty()){
            return null;
        } else {
            Worker bestWorker = findBestWorker(possibleWorkers);
            matchDAO.insertMatch(bestWorker.getWorkerID(), user.getFreelancerID());
            Match match = matchDAO.getMatch(bestWorker);
            match.setUserMatch(userDAO.getUserByEmployer(match.getMatchID()).getUserPersonal());
            return match;
        }
    }

    private int getWorker(Worker worker, Worker currentWorker) {

        float compareWorkers = compareTo(worker, currentWorker);
        if (compareWorkers >= currentWorker.getMinimumMatch() && compareWorkers >= worker.getMinimumMatch() ) {
            return Math.round(compareWorkers);
        } else {
            return 0;
        }
    }

    public Match getExistingEmployerMatch(int matchID) {
        int freelancerID = matchDAO.getEmployerMatch(matchID);
        Worker worker = workerDAO.getFreelancer(freelancerID);
        Match match = matchDAO.getMatch(worker);
        match.setUserMatch(userDAO.getUserByFreelancer(freelancerID).getUserPersonal());
        return match;
    }

    public Match getExistingFreelancerMatch(int matchID) {
        int employerID = matchDAO.getFreelancerMatch(matchID);
        Worker worker = workerDAO.getEmployer(employerID);
        Match match = matchDAO.getMatch(worker);
        match.setUserMatch(userDAO.getUserByEmployer(employerID).getUserPersonal());
        return match;
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
        for (int i = 0; i < matches.size(); i++) {
            matchDAO.deleteMatch(matches.get(i));
        }
    }


    private Worker findBestWorker(List<Worker> possibleWorkers){
        int counter = 0;
        Worker bestWorker = new Worker();
        for(Worker worker : possibleWorkers){
            if(worker.getCompareValue() > counter){
                counter = worker.getCompareValue();
                bestWorker = worker;
            }
        }

        return bestWorker;
    }

    private float compareTo(Worker worker, Worker currentWorker) {

        float counter = 0;
        float skillSetOne = 0;
        float skillSetTwo = 0;

        if (currentWorker.getSalary() == worker.getSalary()) {
            counter = counter + 3;
        }

        if (currentWorker.getLocation() == worker.getLocation()) {
            counter = counter + 4;
        }

        if (currentWorker.getJobLength() == worker.getJobLength()) {
            counter = counter + 1;
        }

        if (currentWorker.getRating() <= worker.getPreviousRating()) {
            counter = counter + 1;
        }

        if (worker.getRating() <= currentWorker.getPreviousRating()){
            counter = counter + 1;
        }

        for (int i = 0; i < currentWorker.getSkill().size(); i++) {
            if (worker.getSkill().contains(currentWorker.getSkill().get(i))) {
                skillSetOne = skillSetOne + 1;
            }
        }
        skillSetOne = (skillSetOne/currentWorker.getSkill().size());

        for(int j = 0; j < worker.getSkill().size(); j++){
            if(currentWorker.getSkill().contains(worker.getSkill().get(j))) {
                skillSetTwo = skillSetTwo + 1;
            }
        }
        skillSetTwo = (skillSetTwo/worker.getSkill().size());
        float skillResult = (skillSetOne + skillSetTwo)/2;
        counter = counter + (skillResult * 10);

        float percentage = (counter / 20) * 100;
        return percentage;
    }
}
