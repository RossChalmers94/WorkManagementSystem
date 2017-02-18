package web.domain.application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Application {

    private String databaseServer;
    private String industryName;
    private List<Location> locations;
    private List<Skill> skills;
    private List<Salary> salarys;
    private List<Rating> ratings;
    private List<JobLength> jobLengths;

    public Application(){

    }

    public String getDatabaseServer(){
        return databaseServer;
    }

    public void setDatabaseServer(String databaseServer){
        this.databaseServer = databaseServer.trim();
    }

    public String getIndustryName(){
        return industryName;
    }

    public void setIndustryName (String industryName){
        this.industryName = industryName.trim();
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

    public List<Rating> getRatings(){
        return ratings;
    }

    public void setRatings(List<Rating> ratings){
        this.ratings = ratings;
    }

    public List<JobLength> getJobLengths(){
        return jobLengths;
    }

    public void setJobLengths(List<JobLength> jobLengths){
        this.jobLengths = jobLengths;
    }


}
