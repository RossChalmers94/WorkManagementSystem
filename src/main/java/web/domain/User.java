package web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class User {

    public interface createAccount{}

    public interface logIn{}

    public interface personalDetails{}

    @NotEmpty(groups = {createAccount.class, logIn.class}, message = "You must enter a Username.")
    private String username;
    @NotEmpty(groups = {createAccount.class, logIn.class}, message = "You must enter a Password.")
    private String password;
    @NotEmpty(groups = {createAccount.class}, message = "You must select a Role.")
    private String role;
    private String firstname;
    private String lastname;
    private String telephone;
    private String emailaddress;
    private String address;
    private String postcode;
    private String towncity;
    private String company;
    private int freelancerID = 0;
    private int employerID = 0;
    private int rating = 0;


    public User(){

    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username.trim();
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
         this.password = password.trim();
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getEmailaddress(){
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress){
        this.emailaddress = emailaddress;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPostcode(){
        return postcode;
    }

    public void setPostcode(String postcode){
         this.postcode = postcode;
    }

    public String getTowncity(){
        return towncity;
    }

    public void setTowncity(String towncity){
        this.towncity = towncity;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
         this.company = company;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public int getFreelancerID(){
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID){
        this.freelancerID = freelancerID;
    }

    public int getEmployerID(){
        return employerID;
    }

    public void setEmployerID(int employerID){
        this.employerID = employerID;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

}
