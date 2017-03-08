package web.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class UserPersonal {

    public interface personalDetailsFreelancer{}

    public interface personalDetailsEmployer{}

    @Size(min = 3, max = 20, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "First Name must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "First Name must only contain letters.", regexp = "^[a-zA-Z\\s]+$")
    private String firstname;
    @Size(min = 3 ,max = 20, groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Last Name must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Last Name must only contain letters.", regexp = "^[a-zA-Z\\s]+$")
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
            message = "Town or City must only contain letters.", regexp = "^[a-zA-Z\\s]+$")
    private String towncity;
    @Size(min = 5, max = 20, groups = {personalDetailsEmployer.class}, message = "Company must be between 5-20 characters.")
    @Pattern(groups = {personalDetailsFreelancer.class, personalDetailsEmployer.class},
            message = "Company must only contain letters.", regexp = "^[a-zA-Z\\s]+$")
    private String company;


    public UserPersonal(){

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

}
