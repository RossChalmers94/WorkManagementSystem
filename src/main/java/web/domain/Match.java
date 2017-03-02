package web.domain;

import web.domain.application.Skill;

import java.util.*;

/**
 * Created by RossChalmers on 24/02/2017.
 */
public class Match {

    private int matchID;
    private String salary;
    private String location;
    private String jobLength;
    private String rating;
    private String minimumMatch;
    private String skills;
    private String jobTitle;
    private String jobDescription;
    private int freelancerID;
    private int employerID;

    public Match() {
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobLength() {
        return jobLength;
    }

    public void setJobLength(String jobLength) {
        this.jobLength = jobLength;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMinimumMatch() {
        return minimumMatch;
    }

    public void setMinimumMatch(String minimumMatch) {
        this.minimumMatch = minimumMatch;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills){
        this.skills = skills;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }
}
