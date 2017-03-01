package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Skill {


    private int skillID;
    /*@Pattern(regexp = "[a-zA-Z]+")
    @Size(min = 1, max = 20, message = "Skill Name must not be empty and must be less than 20 characters.")*/
    //@NotEmpty
    private String skillName;

    public Skill(){

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
