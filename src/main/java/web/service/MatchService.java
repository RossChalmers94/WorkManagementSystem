package web.service;

import web.domain.User;
import web.domain.Match;
import web.domain.Worker;

import java.util.List;

/**
 * This service is responsible for processing the business logic associated with a match
 * @Author Ross Chalmers
 */
public interface MatchService {

    /**
     * This gets an employer's match
     * @param user the {@link User user} object to find a match for
     * @return an employer's {@link Match match}
     */
    Match getEmployerMatch(User user);
    /**
     * This gets a freelancer's match
     * @param user the {@link User user} object to find a match for
     * @return a freelancer's {@link Match match}
     */
    Match getFreelancerMatch(User user);

    /**
     * This retrieves an employer's match if it has already been allocated
     * @param matchID the match ID for the match
     * @return an employer's {@link Match match}
     */
    Match getExistingEmployerMatch(int matchID);
    /**
     * This retrieves a freelancer's match if it has already been allocated
     * @param matchID the match ID for the match
     * @return a freelancer's {@link Match match}
     */
    Match getExistingFreelancerMatch(int matchID);

    /**
     * This completes a match for a user
     * @param user the {@link User user} to complete the match for
     * @param rating the rating to be given to a user's match
     * @param matchID the match to be removed
     */
    void completeMatch(User user, int rating, int matchID);

    /**
     * This sets the previous rating of a user's match after their match has been complete
     * @param user the {@link User user} to set a previous rating for
     * @param rating the rating to be given to a user's match
     * @param id the id of the match to have their previous rating set
     */
    void setPreviousRating(User user, int rating, int id);

    /**
     * This gets all the freelancers
     * @return the {@link List list} of freelancers
     */
    List<Worker> getFreelancers();
    /**
     * This gets all the employers
     * @return the {@link List list} of employers
     */
    List<Worker> getEmployers();
    /**
     * This gets all the matches
     * @return the {@link List list} of matches
     */
    List<Match> getAllMatches();

    /**
     * This deletes matches that are currently active
     * @param matches the {@link List list} of matches to delete
     */
    void deleteMatch(List<Integer> matches);
}
