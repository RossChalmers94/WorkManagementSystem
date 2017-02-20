package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Salary {

    private int salaryID;
    private int salaryMinValue;
    private int salaryMaxValue;


    public Salary(int salaryID, int salaryMinValue, int salaryMaxValue){
        this.salaryID = salaryID;
        this.salaryMinValue = salaryMinValue;
        this.salaryMaxValue = salaryMaxValue;
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
