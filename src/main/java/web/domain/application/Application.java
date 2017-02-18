package web.domain.application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Application {

    private Admin admin;
    private List<Location> locations;
    private List<Skill> skills;
    private List<Salary> salarys;
    private List<JobLength> jobLengths;

    public Application(){

    }

    public Admin getAdmin(){
        return admin;
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    public List<Location> getLocations(){
        return locations;
    }

    public void setLocations(List<Location> locations){
        this.locations = locations;
    }

    public List<Salary> getSalary(){
        return salarys;
    }

    public void setSalarys(List<Salary> salarys){
        this.salarys = salarys;
    }

    public List<Skill> getSkills(){
        return skills;
    }

    public void setSkills(List<Skill> skills){
        this.skills = skills;
    }

    public List<JobLength> getJobLengths(){
        return jobLengths;
    }

    public void setJobLengths(List<JobLength> jobLengths){
        this.jobLengths = jobLengths;
    }


}
