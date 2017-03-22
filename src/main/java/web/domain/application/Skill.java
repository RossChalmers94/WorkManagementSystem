package web.domain.application;

import java.util.List;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Skill
{
    public interface addSkill{}
    private int skillID;
    @Size(groups={addSkill.class}, min=1, max=20, message="Skill must not be empty and must be less than 20 characters.")
    @Pattern(groups={addSkill.class}, regexp="^[a-zA-Z\\s]+$", message="Skill Name must only consist of letters.")
    private String skillName;
    private List<Skill> skills;
    private List<Integer> skillsSet;

    public Skill() {}

    public int getSkillID()
    {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName.trim();
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Integer> getSkillsSet() {
        return skillsSet;
    }

    public void setSkillsSet(List<Integer> skillsSet) {
        this.skillsSet = skillsSet;
    }
}