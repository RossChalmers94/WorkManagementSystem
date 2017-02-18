package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Admin {

    private String databaseServer;
    private String industryName;
    private String adminUsername;
    private String password;
    private String newPassword;
    private String confirmPassword;


    public Admin(){
    }

    public String getDatabaseServer(){
        return databaseServer;
    }

    public void setDatabaseServer(String databaseServer){
        this.databaseServer = databaseServer.trim();
    }

    public String getIndustryName(){
        return industryName;
    }

    public void setIndustryName(String industryName){
        this.industryName = industryName.trim();
    }

    public String getAdminUsername(){
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername){
        this.adminUsername = adminUsername;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getNewPassword(){
        return newPassword;
    }

    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }


}
