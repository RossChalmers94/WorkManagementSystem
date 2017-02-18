package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Skill {

    private Integer skillID;
    private String skillName;

    public Skill(Integer skillID, String skillName){
        this.skillID = skillID;
        this.skillName = skillName;
    }

    public int getSkillID (){
        return skillID;
    }

    public void setSkillID(int skillID){
        this.skillID = skillID;
    }

    public String getSkillName(){
        return skillName;
    }

    public void setSkillName(String skillName){
        this.skillName = skillName.trim();
    }


}
