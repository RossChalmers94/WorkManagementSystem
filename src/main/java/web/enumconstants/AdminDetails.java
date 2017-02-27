package web.enumconstants;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public enum AdminDetails {

    ADMIN_USERNAME("adminUsername"),
    ADMIN_PASSWORD("adminPassword"),
    INDUSTRY_NAME("industryName"),
    DATABASE_SERVER("databaseServer"),
    ADMIN_USERVALUE("adminValue");


    private String value;

    AdminDetails(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
