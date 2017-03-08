package web.repository;
import java.util.Map;
import java.util.List;
import web.domain.application.Admin;
/**
 * Created by RossChalmers on 17/02/2017.
 */
public interface PreferencesDAO {

    List<Map<String, Object>> getPreferences(String storedProc);
    Admin getAdmin();
    void updateJobLengths(Map<String, Object> inParameters);
    void updateSalarys(Map<String, Object> inParameters);
    void addSkill(String skillName);
    void deleteSkill(int skillID);
    void addLocation(String locationName);
    void deleteLocation(int locationID);
    void updateApplication(String name);
    void updatePassword(String password);
    boolean checkAdminPassword(String password);
}
