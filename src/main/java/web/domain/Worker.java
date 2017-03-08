package web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by RossChalmers on 10/02/2017.
 */
public class Worker {

    public interface preferencesDetailsFreelancer {}
    public interface preferencesDetailsEmployer {}

    @Size(min = 5, max = 20, groups = {preferencesDetailsEmployer.class}, message = "Job Title must be between 5-20 characters.")
    private String jobTitle;
    @Size(min = 5, max = 200, groups = {preferencesDetailsEmployer.class}, message = "Job Description must be between 5-200 characters.")
    private String jobDescription;
    @NotEmpty(groups = {preferencesDetailsEmployer.class, preferencesDetailsFreelancer.class}, message = "You must select at least one Skill.")
    private List<Integer> skill;
    @NotNull(groups = {preferencesDetailsEmployer.class, preferencesDetailsFreelancer.class}, message = "You must select a Salary.")
    private Integer salary;
    @NotNull(groups = {preferencesDetailsEmployer.class, preferencesDetailsFreelancer.class}, message = "You must select a Location.")
    private Integer location;
    @NotNull(groups = {preferencesDetailsEmployer.class, preferencesDetailsFreelancer.class}, message = "You must select a Time to Complete the Work.")
    private Integer jobLength;
    @NotNull(groups = {preferencesDetailsEmployer.class, preferencesDetailsFreelancer.class}, message = "You must select when to Relax Preferences.")
    private Integer relaxPreferences;
    @NotNull(groups = {preferencesDetailsEmployer.class, preferencesDetailsFreelancer.class}, message = "You must select a Minimum Rating.")
    private Integer rating;
    @NotNull(groups = {preferencesDetailsEmployer.class, preferencesDetailsFreelancer.class}, message = "You must select a Minimum Match Percentage.")
    private Integer minimumMatch;
    private int jobMatch = 0;
    private int workerID = 0;
    private int previousRating = 0;
    private int previousMatch = 0;
    private int compareValue;
    private Match currentMatch;


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

    public int getSalary() {
        if(salary == null) {
            return 0;
        } else {
            return salary;
        }
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getLocation(){
        if(location == null) {
            return 0;
        } else {
            return location;
        }
    }

    public void setLocation(int location){
        this.location = location;
    }

    public int getJobLength(){
        if(jobLength == null) {
            return 0;
        } else {
            return jobLength;
        }
    }

    public void setJobLength(int jobLength){
        this.jobLength = jobLength;
    }

    public int getRelaxPreferences(){
        if(relaxPreferences == null) {
            return 0;
        } else {
            return relaxPreferences;
        }
    }

    public void setRelaxPreferences(int relaxPreferences){
        this.relaxPreferences = relaxPreferences;
    }

    public int getRating(){
        if(rating == null) {
            return 0;
        } else {
            return rating;
        }
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public int getMinimumMatch(){
        if(minimumMatch == null) {
            return 0;
        } else {
            return minimumMatch;
        }
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

    public int getCompareValue(){
        return compareValue;
    }

    public void setCompareValue(int compareValue){
        this.compareValue = compareValue;
    }

    public int getJobMatch() {
        return jobMatch;
    }

    public void setJobMatch(int jobMatch) {
        this.jobMatch = jobMatch;
    }

    public int getPreviousRating() {
        return previousRating;
    }

    public void setPreviousRating(int previousRating) {
        this.previousRating = previousRating;
    }

    public int getPreviousMatch() {
        return previousMatch;
    }

    public void setPreviousMatch(int previousMatch) {
        this.previousMatch = previousMatch;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public float compareTo(Worker worker) {

        float counter = 0;
        float skillSetOne = 0;
        float skillSetTwo = 0;

        if (this.getSalary() == worker.getSalary()) {
            counter = counter + 3;
        }

        if (this.getLocation() == worker.getLocation()) {
            counter = counter + 4;
        }

        if (this.getJobLength() == worker.getJobLength()) {
            counter = counter + 2;
        }

        if (this.getRating() >= worker.getPreviousMatch()) {
            counter = counter + 1;
        }

        for (int i = 0; i < this.getSkill().size(); i++) {
            if (worker.getSkill().contains(this.getSkill().get(i))) {
                skillSetOne = skillSetOne + 1;
            }
        }
        skillSetOne = (skillSetOne/this.getSkill().size());

        for(int j = 0; j < worker.getSkill().size(); j++){
            if(this.getSkill().contains(worker.getSkill().get(j))) {
                skillSetTwo = skillSetTwo + 1;
            }
        }
        skillSetTwo = (skillSetTwo/worker.getSkill().size());
        float skillResult = (skillSetOne + skillSetTwo)/2;
        counter = counter + (skillResult * 10);

        float percentage = (counter / 20) * 100;
        return percentage;
    }
}
