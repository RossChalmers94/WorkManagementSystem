package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Skill {

    private String skillName;

    public Skill(String skillName){
        this.skillName = skillName;
    }

    public String getSkillName(){
        return skillName;
    }

    public void setSkillName(String skillName){
        this.skillName = skillName.trim();
    }


}
