package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Salary {

    private int salaryID;
    /*@NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$\n", message = "Salary Minimum Value must be a number")*/
    private int salaryMinValue;
    /*@NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$\n", message = "Salary Maximum Value must be a number")*/
    private int salaryMaxValue;

    private List<Salary> salarys;



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

    public List<Salary> getSalarys() {
        return salarys;
    }

    public void setSalarys(List<Salary> salarys) {
        this.salarys = salarys;
    }
}
