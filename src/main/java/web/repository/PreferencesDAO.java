package web.repository;
import java.util.Map;
import java.util.List;
import web.domain.application.Admin;
/**
 * This is responsible for communicating with the database concerning preferences
 * @Author Ross Chalmers
 */
public interface PreferencesDAO {

    /**
     * This is responsible for getting preferences
     * @param storedProc the {@link String string} containing a query
     * @return a list of preferences (based on the query)
     */
    List<Map<String, Object>> getPreferences(String storedProc);

    /**
     * This is responsible for getting admin details
     * @return the {@link Admin admin} object containing admin details
     */
    Admin getAdmin();

    /**
     *  This is responsible for updating the job lengths preference
     * @param inParameters the {@link Map map} of job lengths to insert
     */
    void updateJobLengths(Map<String, Object> inParameters);
    /**
     *  This is responsible for updating the salaries preference
     * @param inParameters the {@link Map map} of salaries to insert
     */
    void updateSalarys(Map<String, Object> inParameters);

    /**
     * This is responsible for adding a new skill as a preference
     * @param skillName the {@link String string} that will contain the new skill name
     */
    void addSkill(String skillName);

    /**
     * This is responsible for deleting a skill as a preference
     * @param skillID the skillID to be deleted
     */
    void deleteSkill(int skillID);
    /**
     * This is responsible for adding a new location as a preference
     * @param locationName the {@link String string} that will contain the new location name
     */
    void addLocation(String locationName);
    /**
     * This is responsible for deleting a location as a preference
     * @param locationID the locationID to be deleted
     */
    void deleteLocation(int locationID);

    /**
     * This is responsible for updating the industry name for the application
     * @param name the {@link String string} that contains the industry name
     */
    void updateApplication(String name);
}
