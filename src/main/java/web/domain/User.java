package web.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class User {

    public interface createAccount{}

    public interface logIn{}

    public interface personalDetailsFreelancer{}

    public interface personalDetailsEmployer{}

    @Size(min = 5, max = 20, groups = {createAccount.class, logIn.class}, message = "Username must be between 5-20 characters.")
    private String username;
    @Size(min = 5, max = 20, groups = {createAccount.class, logIn.class}, message = "Password must be between 5-20 characters.")
    private String password;
    @NotEmpty(groups = {createAccount.class}, message = "You must select a Role.")
    private String role;
    @Size(min = 5, max = 20, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "First Name must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "First Name must only contain letters.", regexp = "[a-zA-Z]+")
    private String firstname;
    @Size(min = 5, max = 20, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Last Name must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Last Name must only contain letters.", regexp = "[a-zA-Z]+")
    private String lastname;
    @Size(min = 5, max = 20, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Telephone Number must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class}, regexp = "[0-9]+",
            message = "Telephone Number must only consist of digits.")
    private String telephone;
    @Size(min = 5, max = 50, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Email Address must be between 5-50 characters.")
    @Email(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class}, message = "Email Address is not valid.")
    private String emailaddress;
    @Size(min = 5, max = 50, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Address must be between 5-50 characters.")
    private String address;
    @Size(min = 5, max = 10, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Postcode must be between 5-10 characters.")
    private String postcode;
    @Size(min = 5, max = 20, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Town or City must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Town or City must only contain letters.", regexp = "[a-zA-Z]+")
    private String towncity;
    @Size(min = 5, max = 20, groups = {personalDetailsEmployer.class}, message = "Company must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Company must only contain letters.", regexp = "[a-zA-Z]+")
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
