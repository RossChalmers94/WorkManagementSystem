package web.repository;
import java.util.Map;
import java.util.List;
/**
 * Created by RossChalmers on 17/02/2017.
 */
public interface PreferencesDAO {

    List<Map<String, Object>> getPreferences(String storedProc);
    Map getAdmin(String storedProc);
    void updateSkills(String storedProc, Map<String, Object> inParameters);
    void updateLocations(String storedProc, Map<String, Object> inParameters);
    void updateJobLengths(String storedProc, Map<String, Object> inParameters);
    void updateSalarys(String storedProc, Map<String, Object> inParameters);
}
