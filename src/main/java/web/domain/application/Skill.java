package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Skill {


    private int skillID;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]+")
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
