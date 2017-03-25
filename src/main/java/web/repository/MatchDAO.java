package web.repository;

import java.util.*;
import web.domain.*;
/**
 * This is responsible for communicating with the database concerning matches
 *
 */
public interface MatchDAO {

    /**
     * This is responsible for getting a user's skills
     * @param storedProc the {@link String string} that contains a query
     * @param id the id of the user to retrieve skills for
     * @return a {@link List list} of integers that represent skills
     */
    List<Integer> getSkills(String storedProc, int id);

    /**
     * This is responsible for getting a list of freelancers
     * @param storedProc the {@link String string} that contains a query
     * @return a {@link List list} of freelancers
     */
    List<Worker> getFreelancers(String storedProc);
    /**
     * This is responsible for getting a list of employers
     * @param storedProc the {@link String string} that contains a query
     * @return a {@link List list} of employers
     */
    List<Worker> getEmployers(String storedProc);

    /**
     * This is responsible for returning a match object based on a worker object.
     * @param worker the {@link Worker worker} to use to produce a match
     * @return the new {@link Match match}
     */
    Match getMatch(Worker worker);

    /**
     * This is responsible for inserting a new match
     * @param employerID the employer's ID for this new match
     * @param freelancerID the freelancer's ID for this new match
     * @return the new MatchID
     */
    int insertMatch(int employerID, int freelancerID);

    /**
     * This is responsible for getting the freelancer of a match
     * @param matchID the match that the freelancer will be part of
     * @return the freelancerID
     */
    int getEmployerMatch(int matchID);
    /**
     * This is responsible for getting the employer of a match
     * @param matchID the match that the employer will be part of
     * @return the employerID
     */
    int getFreelancerMatch(int matchID);

    /**
     * This is responsible for completing an employer's match
     * @param employerID the employerID to complete the match for
     * @param freelancerID the freelancerID to attach a rating to
     * @param rating the rating to attach to the freelancerID
     * @param matchID the matchID of the match to be removed
     */
    void completeEmployerMatch(int employerID, int freelancerID, int rating, int matchID);
    /**
     * This is responsible for completing a freelancer's match
     * @param freelancerID the freelancerID to complete the match for
     * @param employerID the employerID to attach a rating to
     * @param rating the rating to attach to the employerID
     * @param matchID the matchID of the match to be removed
     */
    void completeFreelancerMatch(int freelancerID, int employerID, int rating, int matchID);

    /**
     * This is responsible for setting a rating for a freelancer that was previously matched to an employer
     * @param employerID the employerID that is setting the rating
     * @param freelancerID the freelancerID that is being set with a rating
     * @param rating the rating to be set
     */
    void setPreviousEmployerRating(int employerID, int freelancerID, int rating);
    /**
     * This is responsible for setting a rating for an employer that was previously matched to a freelancer
     * @param employerID the employerID that is being set with a rating
     * @param freelancerID the freelancerID that is setting the rating
     * @param rating the rating to be set
     */
    void setPreviousFreelancerRating(int employerID, int freelancerID, int rating);

    /**
     * This is responsible for getting a list of all matches
     * @return a {@link List list} of all matches
     */
    List<Match> getAllMatches();

    /**
     * This is responsible for deleting a match
     * @param matchID the matchID to be deleted
     */
    void deleteMatch(int matchID);


}
