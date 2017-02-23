package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.application.*;
import web.repository.PreferencesDAO;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * Created by RossChalmers on 10/02/2017.
 */

@Service
public class PreferencesServiceImpl implements PreferencesService {

    @Autowired
    private PreferencesDAO preferencesDAO;

    public List<Skill> getSkills() {
        List<Map<String, Object>> skills = preferencesDAO.getPreferences("SELECT skillID, skillName FROM skills");
        List<Skill> toReturn = new ArrayList<Skill>();
        for(int i = 0; i < skills.size(); i++){
            Skill skill = new Skill((Integer)skills.get(i).get("skillId"), (String)skills.get(i).get("skillName"));
            toReturn.add(skill);
        }

        return toReturn;
    }

    public List<Location> getLocations(){
        List<Map<String, Object>> locations = preferencesDAO.getPreferences("SELECT locationID, locationName FROM location");
        List<Location> toReturn = new ArrayList<Location>();
        for(int i = 0; i < locations.size(); i++){
            Location location = new Location((Integer)locations.get(i).get("locationID"), (String)locations.get(i).get("locationName"));
            toReturn.add(location);
        }

        return toReturn;
    }

    public List<Salary> getSalarys(){
        List<Map<String, Object>> salarys = preferencesDAO.getPreferences("SELECT salaryID, salaryMinValue, salaryMaxValue FROM salary");
        List<Salary> toReturn = new ArrayList<Salary>();
        for(int i = 0; i < salarys.size(); i++){
            Salary salary = new Salary((Integer)salarys.get(i).get("salaryID"), (Integer)salarys.get(i).get("salaryMinValue"),
                    (Integer)salarys.get(i).get("salaryMaxValue"));
            toReturn.add(salary);
        }

        return toReturn;
    }

    public List<JobLength> getJobLengths(){
        List<Map<String, Object>> jobLengths =
                preferencesDAO.getPreferences("SELECT jobLengthID, jobLengthMin, jobLengthMax FROM job_length");
        List<JobLength> toReturn = new ArrayList<JobLength>();
        for(int i = 0; i < jobLengths.size(); i++){
            JobLength jobLength = new JobLength((Integer)jobLengths.get(i).get("jobLengthID"),
                    (Integer)jobLengths.get(i).get("jobLengthMin"),
                    (Integer)jobLengths.get(i).get("jobLengthMax"));
            toReturn.add(jobLength);
        }

        return toReturn;
    }

    public Admin getAdmin(){

        Map get = preferencesDAO.getAdmin("SELECT adminUsername, adminPassword, industryName, databaseServer FROM application");
        Admin admin = new Admin();
        admin.setAdminUsername((String)get.get("adminUsername"));
        admin.setPassword((String)get.get("adminPassword"));
        admin.setIndustryName((String)get.get("industryName"));
        admin.setDatabaseServer((String)get.get("databaseServer"));
        return admin;
    }

}
