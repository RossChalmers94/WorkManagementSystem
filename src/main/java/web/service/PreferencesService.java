package web.service;
import java.util.Map;
import java.util.List;

import web.domain.application.*;

/**
 * Created by RossChalmers on 13/02/2017.
 */
public interface PreferencesService {

    List<Skill> getSkills();
    List<Location> getLocations();
    List<Salary> getSalarys();
    List<JobLength> getJobLengths();
    Admin getAdmin();
    void updatePreferences(Application application);
    void addSkill(String skillName);
    void deleteSkill(List<Integer> skills);
    void addLocation(String locationName);
    void deleteLocation(List<Integer> locations);
    void updateApplication(Admin admin);
    boolean checkAdminPassword(String password);
    void updatePassword(Admin admin);

}
