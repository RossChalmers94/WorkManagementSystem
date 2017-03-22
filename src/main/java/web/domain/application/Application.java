package web.domain.application;

import java.util.List;
import javax.validation.Valid;

public class Application
{
    private List<Location> locations;
    private List<Skill> skills;
    private List<Salary> salarys;
    private List<JobLength> jobLengths;
    @Valid
    private Skill skill;
    private List<Integer> skillsSet;
    private Location location;
    private List<Integer> locationSet;

    public Application() {}

    public List<Location> getLocations()
    {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Salary> getSalarys() {
        return salarys;
    }

    public void setSalarys(List<Salary> salarys) {
        this.salarys = salarys;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<JobLength> getJobLengths() {
        return jobLengths;
    }

    public void setJobLengths(List<JobLength> jobLengths) {
        this.jobLengths = jobLengths;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Integer> getSkillsSet() {
        return skillsSet;
    }

    public void setSkillsSet(List<Integer> skillsSet) {
        this.skillsSet = skillsSet;
    }

    public List<Integer> getLocationSet() {
        return locationSet;
    }

    public void setLocationSet(List<Integer> locationSet) {
        this.locationSet = locationSet;
    }
}