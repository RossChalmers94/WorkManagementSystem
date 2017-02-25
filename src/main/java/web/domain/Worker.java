package web.domain;

import java.util.*;

/**
 * Created by RossChalmers on 10/02/2017.
 */
public class Worker {

    private String jobTitle;
    private String jobDescription;
    private List<Integer> skill;
    private int salary;
    private int location;
    private int jobLength;
    private int relaxPreferences;
    private int rating;
    private int minimumMatch;
    private int workerID;
    private int compareValue;


    public Worker(){

    }

    public String getJobTitle(){
        return jobTitle;
    }

    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    public String getJobDescription(){
        return jobDescription;
    }

    public void setJobDescription(String jobDescription){
        this.jobDescription = jobDescription;
    }

    public List<Integer> getSkill(){
        return skill;
    }

    public void setSkill(List<Integer> skill){
        this.skill = skill;
    }

    public int getSalary(){
        return salary;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public int getLocation(){
        return location;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public int getJobLength(){
        return jobLength;
    }

    public void setJobLength(int jobLength){
        this.jobLength = jobLength;
    }

    public int getRelaxPreferences(){
        return relaxPreferences;
    }

    public void setRelaxPreferences(int relaxPreferences){
        this.relaxPreferences = relaxPreferences;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public int getMinimumMatch(){
        return minimumMatch;
    }

    public void setMinimumMatch(int minimumMatch){
        this.minimumMatch = minimumMatch;
    }

    public int getWorkerID(){
        return workerID;
    }

    public void setWorkerID(int workerID){
        this.workerID = workerID;
    }

    public int compareTo(Worker worker){

        int counter = 0;

        if(this.salary == worker.getSalary()){
            counter = counter + 1;
        }

        if(this.location == worker.getLocation()){
            counter = counter + 1;
        }
        if(this.jobLength == worker.getJobLength()){
            counter = counter + 1;
        }

        int percentage = (counter/3) * 100;
        return percentage;
    }

    public int getCompareValue(){
        return compareValue;
    }

    public void setCompareValue(int compareValue){
        this.compareValue = compareValue;
    }


}
