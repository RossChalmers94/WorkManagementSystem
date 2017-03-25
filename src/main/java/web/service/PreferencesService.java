package web.service;
import java.util.Map;
import java.util.List;

import web.domain.application.*;

/**
 * This service is responsible for processing the business logic associated with preferences
 *
 */
public interface PreferencesService {

    /**
     * This gets the list of skills
     * @return the {@link List list} of {@link Skill skills}
     */
    List<Skill> getSkills();

    /**
     * This gets the list of locations
     * @return the {@link List list} of {@link Location locations}
     */
    List<Location> getLocations();

    /**
     * This gets the list of salaries
     * @return the {@link List list} of {@link Salary salaries}
     */
    List<Salary> getSalarys();

    /**
     * This gets the list of job lengths
     * @return the {@link List list} of {@link JobLength job lengths}
     */
    List<JobLength> getJobLengths();

    /**
     * This gets the administrative details for the application
     * @return the {@link Admin admin} object that holds the application data
     */
    Admin getAdmin();

    /**
     * This updates the job lengths preference
     * @param jobLength the {@link JobLength job lengths} to be updated
     */
    void updateJobLengths(JobLength jobLength);
    /**
     * This updates the salaries preference
     * @param salary the {@link Salary salaries} to be updated
     */
    void updateSalaries(Salary salary);

    /**
     * This adds a new skill as a preference
     * @param skillName the {@link String string} that contains the skill name
     */
    void addSkill(String skillName);

    /**
     * This deletes skills as a preference
     * @param skills the {@link List list} of skills to delete
     */
    void deleteSkill(List<Integer> skills);
    /**
     * This adds a new location as a preference
     * @param locationName the {@link String string} that contains the location name
     */
    void addLocation(String locationName);
    /**
     * This deletes locations as a preference
     * @param locations the {@link List list} of locations to delete
     */
    void deleteLocation(List<Integer> locations);

    /**
     * This updates the industry name
     * @param admin the {@link Admin admin} object that contains the industry name
     */
    void updateApplication(Admin admin);

}
