package web.enumconstants;

/**
 * Created by RossChalmers on 15/02/2017.
 */
public enum UserDetails {

    USER_ID("userId"),
    USER_NAME("username"),
    USER_PASSWORD("userpassword"),
    USER_FIRSTNAME("firstName"),
    USER_LASTNAME("lastName"),
    USER_TELEPHONE("telephoneNo"),
    USER_EMAILADDRESS("emailAddress"),
    USER_ADDRESS("address"),
    USER_POSTCODE("postcode"),
    USER_TOWNCITY("townCity"),
    USER_COMPANY("company"),
    USER_VALUE("uservalue");


    private String value;

    UserDetails(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
