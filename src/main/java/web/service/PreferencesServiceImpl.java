package web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.domain.application.Admin;
import web.domain.application.JobLength;
import web.domain.application.Location;
import web.domain.application.Salary;
import web.domain.application.Skill;
import web.enumconstants.PreferenceDetails;
import web.repository.PreferencesDAO;

@Service
public class PreferencesServiceImpl implements PreferencesService
{
    @Autowired
    private PreferencesDAO preferencesDAO;
    public BCryptPasswordEncoder passwordEncoder;

    public PreferencesServiceImpl(PreferencesDAO preferencesDAO)
    {
        this.preferencesDAO = preferencesDAO;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Skill> getSkills() {
        List<Map<String, Object>> skills = preferencesDAO.getPreferences("SELECT skillID, skillName FROM skills");
        List<Skill> toReturn = new ArrayList<Skill>();
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = new Skill();
            skill.setSkillID(((Integer)(skills.get(i)).get("skillId")));
            skill.setSkillName((String)(skills.get(i)).get("skillName"));
            toReturn.add(skill);
        }

        return toReturn;
    }

    public List<Location> getLocations() {
        List<Map<String, Object>> locations = preferencesDAO.getPreferences("SELECT locationID, locationName FROM location");
        List<Location> toReturn = new ArrayList<Location>();
        for (int i = 0; i < locations.size(); i++) {
            Location location = new Location();
            location.setLocationID(((Integer)(locations.get(i)).get("locationID")));
            location.setLocationName((String)(locations.get(i)).get("locationName"));
            toReturn.add(location);
        }

        return toReturn;
    }

    public List<Salary> getSalarys() {
        List<Map<String, Object>> salarys = preferencesDAO.getPreferences("SELECT salaryID, salaryMinValue, salaryMaxValue FROM salary");
        List<Salary> toReturn = new ArrayList<Salary>();
        for (int i = 0; i < salarys.size(); i++) {
            Salary salary = new Salary();
            salary.setSalaryID(((Integer)(salarys.get(i)).get("salaryID")));
            salary.setSalaryMinValue(((Integer)(salarys.get(i)).get("salaryMinValue")));
            salary.setSalaryMaxValue(((Integer)(salarys.get(i)).get("salaryMaxValue")));
            toReturn.add(salary);
        }
        return toReturn;
    }

    public List<JobLength> getJobLengths()
    {
        List<Map<String, Object>> jobLengths = preferencesDAO.getPreferences("SELECT jobLengthID, jobLengthMin, jobLengthMax FROM job_length");
        List<JobLength> toReturn = new ArrayList<JobLength>();
        for (int i = 0; i < jobLengths.size(); i++) {
            JobLength jobLength = new JobLength();
            jobLength.setJobLengthID(((Integer)(jobLengths.get(i)).get("jobLengthID")));
            jobLength.setJobLengthMin(((Integer)(jobLengths.get(i)).get("jobLengthMin")));
            jobLength.setJobLengthMax(((Integer)(jobLengths.get(i)).get("jobLengthMax")));
            toReturn.add(jobLength);
        }

        return toReturn;
    }

    public Admin getAdmin() {
        return preferencesDAO.getAdmin();
    }

    public void updateJobLengths(JobLength jobLength) {
        for (int i = 0; i < jobLength.getJobLengths().size(); i++) {
            Map<String, Object> inParameters = new HashMap<String, Object>();
            inParameters.put(PreferenceDetails.JOB_LENGTH_ID.getValue(), i + 1);
            inParameters.put(PreferenceDetails.JOB_LENGTH_MIN.getValue(), ((JobLength)jobLength.getJobLengths().get(i)).getJobLengthMin());
            inParameters.put(PreferenceDetails.JOB_LENGTH_MAX.getValue(), ((JobLength)jobLength.getJobLengths().get(i)).getJobLengthMax());
            preferencesDAO.updateJobLengths(inParameters);
        }
    }

    public void updateSalaries(Salary salary) {
        for (int i = 0; i < salary.getSalarys().size(); i++) {
            Map<String, Object> inParameters = new HashMap<String, Object>();
            inParameters.put(PreferenceDetails.SALARY_ID.getValue(), i + 1);
            inParameters.put(PreferenceDetails.SALARY_MIN.getValue(), salary.getSalarys().get(i).getSalaryMinValue());
            inParameters.put(PreferenceDetails.SALARY_MAX.getValue(), salary.getSalarys().get(i).getSalaryMaxValue());
            preferencesDAO.updateSalarys(inParameters);
        }
    }

    public void addSkill(String skillName) {
        preferencesDAO.addSkill(skillName);
    }

    public void deleteSkill(List<Integer> skills) {
        for (int i = 0; i < skills.size(); i++) {
            preferencesDAO.deleteSkill(((Integer)skills.get(i)));
        }
    }

    public void addLocation(String locationName) {
        preferencesDAO.addLocation(locationName);
    }

    public void deleteLocation(List<Integer> locations) {
        for (int i = 0; i < locations.size(); i++) {
            preferencesDAO.deleteLocation(((Integer)locations.get(i)));
        }
    }

    public void updateApplication(Admin admin) {
        preferencesDAO.updateApplication(admin.getIndustryName());
    }

    public void updatePassword(Admin admin) {
        preferencesDAO.updatePassword(passwordEncoder.encode(admin.getNewPassword().trim()));
    }

    public boolean checkAdminPassword(String password) {
        String adminPassword = preferencesDAO.checkAdminPassword();
        if (password.equals(adminPassword)) {
            return true;
        }
        return false;
    }
}