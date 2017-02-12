package web.domain;

/**
 * Created by RossChalmers on 10/02/2017.
 */
public class Freelancer {

    private int salary;
    private int location;
    private int relaxpreferences;
    private int rating;

    public Freelancer(){

    }

    public int getSalary(){
        return salary;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public int getLocation(){
        return location;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public int getRelaxpreferences(){
        return relaxpreferences;
    }

    public void setRelaxpreferences(int relaxpreferences){
        this.relaxpreferences = relaxpreferences;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

}
