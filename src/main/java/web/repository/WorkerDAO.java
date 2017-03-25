package web.repository;

import java.util.*;
import web.domain.*;

/**
 * This is responsible for communicating with the database concerning workers
 * @Author Ross Chalmers
 */
public interface WorkerDAO {
    /**
     * This is responsible for inserting a new freelancer
     * @param inParameters the freelancer's preferences
     * @return the freelancer's ID
     */
    int insertFreelancer(Map<String, Object> inParameters);
    /**
     * This is responsible for inserting a new employer
     * @param inParameters the employer's preferences
     * @return the employer's ID
     */
    int insertEmployer(Map<String, Object> inParameters);

    /**
     * This is responsible for inserting an employer's skills
     * @param inParameters the employer's skills
     */
    void insertEmployerSkills(Map<String, Object> inParameters);
    /**
     * This is responsible for inserting a freelancer's skills
     * @param inParameters the freelancer's skills
     */
    void insertFreelancerSkills(Map<String, Object> inParameters);

    /**
     * This is responsible for deleting an employer's skills
     * @param employerID the employerID to delete skills for
     */
    void deleteEmployerSkills(int employerID);
    /**
     * This is responsible for deleting a freelancer's skills
     * @param freelancerID the freelancerID to delete skills for
     */
    void deleteFreelancerSkills(int freelancerID);

    /**
     * This is responsible for getting an employer's preferences
     * @param employerID the employerID to retrieve preferences for
     * @return the {@link Worker worker} object that contains the employer's preferences
     */
    Worker getEmployer(int employerID);
    /**
     * This is responsible for getting a freelancer's preferences
     * @param freelancerID the freelancerID to retrieve preferences for
     * @return the {@link Worker worker} object that contains the freelancer's preferences
     */
    Worker getFreelancer(int freelancerID);

    /**
     * This is responsible for updating an employer's preferences
     * @param inParameters the employer's preferences
     */
    void updateEmployer(Map<String, Object> inParameters);
    /**
     * This is responsible for updating a freelancer's preferences
     * @param inParameters the freelancer's preferences
     */
    void updateFreelancer(Map<String, Object> inParameters);

    /**
     * This is responsible for getting a worker's skills (freelancer or employer)
     * @param storedProc the {@link String string} containing a query
     * @param id the employer or freelancer id to retrieve skills for
     * @return the {@link List list} of skills
     */
    List<Integer> getSkills(String storedProc, int id);

    /**
     * This is responsible for deleting an employer
     * @param employerID the employerID of the employer to be deleted
     */
    void deleteEmployer(int employerID);

    /**
     * This is responsible for deleting a freelancer
     * @param freelancerID the freelancerID of the freelancer to be deleted
     */
    void deleteFreelancer(int freelancerID);
}
