package web.domain;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Worker
{
    public interface preferencesDetailsEmployer{}
    public interface preferencesDetailsFreelancer{}
    @Size(min=5, max=20, groups={Worker.preferencesDetailsEmployer.class}, message="Job Title must be between 5-20 characters.")
    private String jobTitle;
    @Size(min=5, max=200, groups={Worker.preferencesDetailsEmployer.class}, message="Job Description must be between 5-200 characters.")
    private String jobDescription;
    @NotEmpty(groups={Worker.preferencesDetailsEmployer.class, Worker.preferencesDetailsFreelancer.class}, message="You must select at least one Skill.")
    private List<Integer> skill;
    @NotNull(groups={Worker.preferencesDetailsEmployer.class, Worker.preferencesDetailsFreelancer.class}, message="You must select a Salary.")
    private Integer salary;
    @NotNull(groups={Worker.preferencesDetailsEmployer.class, Worker.preferencesDetailsFreelancer.class}, message="You must select a Location.")
    private Integer location;
    @NotNull(groups={Worker.preferencesDetailsEmployer.class, Worker.preferencesDetailsFreelancer.class}, message="You must select a Time to Complete the Work.")
    private Integer jobLength;
    @NotNull(groups={Worker.preferencesDetailsEmployer.class, Worker.preferencesDetailsFreelancer.class}, message="You must select when to Relax Preferences.")
    private Integer relaxPreferences;
    @NotNull(groups={Worker.preferencesDetailsEmployer.class, Worker.preferencesDetailsFreelancer.class}, message="You must select a Minimum Rating.")
    private Integer rating;
    @NotNull(groups={Worker.preferencesDetailsEmployer.class, Worker.preferencesDetailsFreelancer.class}, message="You must select a Minimum Match Percentage.")
    private Integer minimumMatch;
    private int jobMatch = 0;
    private int workerID = 0;
    private int previousRating = 0;
    private int previousMatch = 0;

    private int compareValue;


    public Worker() {}

    public String getJobTitle()
    {
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

    public List<Integer> getSkill() {
        return skill;
    }

    public void setSkill(List<Integer> skill) {
        this.skill = skill;
    }

    public int getSalary() {
        if (salary == null) {
            return 0;
        }
        return salary.intValue();
    }

    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    public int getLocation() {
        if (location == null) {
            return 0;
        }
        return location.intValue();
    }

    public void setLocation(int location)
    {
        this.location = location;
    }

    public int getJobLength() {
        if (jobLength == null) {
            return 0;
        }
        return jobLength.intValue();
    }

    public void setJobLength(int jobLength)
    {
        this.jobLength = jobLength;
    }

    public int getRelaxPreferences() {
        if (relaxPreferences == null) {
            return 0;
        }
        return relaxPreferences.intValue();
    }

    public void setRelaxPreferences(int relaxPreferences)
    {
        this.relaxPreferences = relaxPreferences;
    }

    public int getRating() {
        if (rating == null) {
            return 0;
        }
        return rating.intValue();
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public int getMinimumMatch() {
        if (minimumMatch == null) {
            return 0;
        }
        return minimumMatch.intValue();
    }

    public void setMinimumMatch(int minimumMatch)
    {
        this.minimumMatch = minimumMatch;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public int getCompareValue() {
        return compareValue;
    }

    public void setCompareValue(int compareValue) {
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

}