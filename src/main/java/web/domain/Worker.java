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

    private Match currentMatch;

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
        this.salary = Integer.valueOf(salary);
    }

    public int getLocation() {
        if (location == null) {
            return 0;
        }
        return location.intValue();
    }

    public void setLocation(int location)
    {
        this.location = Integer.valueOf(location);
    }

    public int getJobLength() {
        if (jobLength == null) {
            return 0;
        }
        return jobLength.intValue();
    }

    public void setJobLength(int jobLength)
    {
        this.jobLength = Integer.valueOf(jobLength);
    }

    public int getRelaxPreferences() {
        if (relaxPreferences == null) {
            return 0;
        }
        return relaxPreferences.intValue();
    }

    public void setRelaxPreferences(int relaxPreferences)
    {
        this.relaxPreferences = Integer.valueOf(relaxPreferences);
    }

    public int getRating() {
        if (rating == null) {
            return 0;
        }
        return rating.intValue();
    }

    public void setRating(int rating)
    {
        this.rating = Integer.valueOf(rating);
    }

    public int getMinimumMatch() {
        if (minimumMatch == null) {
            return 0;
        }
        return minimumMatch.intValue();
    }

    public void setMinimumMatch(int minimumMatch)
    {
        this.minimumMatch = Integer.valueOf(minimumMatch);
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

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public float compareTo(Worker worker)
    {
        float counter = 0.0F;
        float skillSetOne = 0.0F;
        float skillSetTwo = 0.0F;

        if (getSalary() == worker.getSalary()) {
            counter += 3.0F;
        }

        if (getLocation() == worker.getLocation()) {
            counter += 4.0F;
        }

        if (getJobLength() == worker.getJobLength()) {
            counter += 1.0F;
        }

        if (getRating() <= worker.getPreviousRating()) {
            counter += 1.0F;
        }

        if (worker.getRating() <= getPreviousRating()) {
            counter += 1.0F;
        }

        for (int i = 0; i < getSkill().size(); i++) {
            if (worker.getSkill().contains(getSkill().get(i))) {
                skillSetOne += 1.0F;
            }
        }
        skillSetOne /= getSkill().size();

        for (int j = 0; j < worker.getSkill().size(); j++) {
            if (getSkill().contains(worker.getSkill().get(j))) {
                skillSetTwo += 1.0F;
            }
        }
        skillSetTwo /= worker.getSkill().size();
        float skillResult = (skillSetOne + skillSetTwo) / 2.0F;
        counter += skillResult * 10.0F;

        float percentage = counter / 20.0F * 100.0F;
        return percentage;
    }
}