package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Salary {

    private int salaryID;
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$\n")
    private int salaryMinValue;
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$\n")
    private int salaryMaxValue;


    public Salary(){
    }

    public int getSalaryID(){
        return salaryID;
    }

    public void setSalaryID(int salaryID){
        this.salaryID = salaryID;
    }

    public int getSalaryMinValue(){
        return salaryMinValue;
    }

    public void setSalaryMinValue(int salaryMinValue){
        this.salaryMinValue = salaryMinValue;
    }

    public int getSalaryMaxValue(){
        return salaryMaxValue;
    }

    public void setSalaryMaxValue(int salaryMaxValue){
        this.salaryMaxValue = salaryMaxValue;
    }


}
