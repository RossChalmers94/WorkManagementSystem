package web.domain.application;

import java.util.List;

public class Salary
{
    private int salaryID;
    private int salaryMinValue;
    private int salaryMaxValue;
    private List<Salary> salarys;

    public Salary() {}

    public int getSalaryID()
    {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public int getSalaryMinValue() {
        return salaryMinValue;
    }

    public void setSalaryMinValue(int salaryMinValue) {
        this.salaryMinValue = salaryMinValue;
    }

    public int getSalaryMaxValue() {
        return salaryMaxValue;
    }

    public void setSalaryMaxValue(int salaryMaxValue) {
        this.salaryMaxValue = salaryMaxValue;
    }

    public List<Salary> getSalarys() {
        return salarys;
    }

    public void setSalarys(List<Salary> salarys) {
        this.salarys = salarys;
    }
}