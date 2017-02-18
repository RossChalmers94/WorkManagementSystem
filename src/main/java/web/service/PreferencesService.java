package web.service;
import java.util.Map;
import java.util.List;
import web.domain.application.Admin;
import web.domain.application.Location;
import web.domain.application.Skill;

/**
 * Created by RossChalmers on 13/02/2017.
 */
public interface PreferencesService {

    List<Skill> getSkills();
    List<Location> getLocations();
    Admin getAdmin();

}
