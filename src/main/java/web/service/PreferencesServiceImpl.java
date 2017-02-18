package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.User;
import web.domain.repository.PreferencesDAO;
import web.domain.repository.UserDAO;
import web.enumconstants.UserDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by RossChalmers on 10/02/2017.
 */

@Service()
public class PreferencesServiceImpl implements PreferencesService {

    @Autowired
    private PreferencesDAO preferencesDAO;

    public Map getSkills() {
        List<Map<String, Object>> skills = preferencesDAO.get("SELECT skillID, skillName FROM skills");
        Map<Integer, String> toReturn = new HashMap<Integer,String>();
        for(int i = 0; i < skills.size(); i++){
            toReturn.put((Integer)skills.get(i).get("skillId"), (String)skills.get(i).get("skillName"));
        }

        return toReturn;
    }

    public Map getLocations(){
        List<Map<String, Object>> locations = preferencesDAO.get("SELECT locationID, locationName FROM location");
        Map<Integer, String> toReturn = new HashMap<Integer,String>();
        for(int i = 0; i < locations.size(); i++){
            toReturn.put((Integer)locations.get(i).get("locationID"), (String)locations.get(i).get("locationName"));
        }

        return toReturn;
    }

}
