package web.repository;
import java.util.Map;
import java.util.List;
/**
 * Created by RossChalmers on 17/02/2017.
 */
public interface PreferencesDAO {

    List<Map<String, Object>> getPreferences(String storedProc);
    Map getAdmin(String storedProc);
    void updateJobLengths(String storedProc, Map<String, Object> inParameters);
    void updateSalarys(String storedProc, Map<String, Object> inParameters);
    void addSkill(String storedProc, String skillName);
    void deleteSkill(String storedProc, int skillID);
    void addLocation(String storedProc, String locationName);
    void deleteLocation(String storedProc, int locationID);
    void updateApplication(String storedProc, String name);
    void updatePassword(String storedProc, String password);
    int checkAdminPassword(String storedProc, String password);
}
