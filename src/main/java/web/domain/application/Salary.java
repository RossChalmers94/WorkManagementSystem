package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Salary {

    private String salaryMinValue;
    private String salaryMaxValue;


    public Salary(){
    }

    public String getSalaryMinValue(){
        return salaryMinValue;
    }

    public void setSalaryMinValue(String salaryMinValue){
        this.salaryMinValue = salaryMinValue.trim();
    }

    public String getSalaryMaxValue(){
        return salaryMaxValue;
    }

    public void setSalaryMaxValue(String salaryMaxValue){
        this.salaryMaxValue = salaryMaxValue.trim();
    }


}
