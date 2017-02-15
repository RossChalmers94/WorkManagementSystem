package web.domain;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class User {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String telephone;
    private String emailaddress;
    private String address;
    private String postcode;
    private String towncity;
    private String company;
    private String role;


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

    public String setPassword(String password){
        return this.password = password.trim();
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

    public String setLastname(String lastname){
        return this.lastname = lastname;
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

    public String setEmailaddress(String emailaddress){
        return this.emailaddress = emailaddress;
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

    public String setPostcode(String postcode){
        return this.postcode = postcode;
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

    public String setCompany(String company){
        return this.company = company;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }


}
