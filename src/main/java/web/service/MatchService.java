package web.service;

import web.domain.User;
import web.domain.Match;
import web.domain.Worker;

import java.util.List;

/**
 * Created by RossChalmers on 26/02/2017.
 */
public interface MatchService {

    Match getEmployerMatch(User user);
    Match getFreelancerMatch(User user);
    Match getExistingEmployerMatch(int matchID);
    Match getExistingFreelancerMatch(int matchID);
    void completeMatch(User user, int rating, int matchID);
    void setPreviousRating(User user, int rating, int id);
    List<Worker> getFreelancers();
    List<Worker> getEmployers();
    List<Match> getAllMatches();
}
