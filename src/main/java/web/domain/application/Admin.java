package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Admin {

    public interface application{};
    public interface password{};


    private String databaseServer;
    @Size(groups = application.class, min = 1, max = 50, message = "Industry Name must not be empty and must be less than 50 characters.")
    private String industryName;
    private String adminUsername;
    @NotEmpty(groups = password.class)
    private String password;
    @NotEmpty(groups = password.class)
    private String newPassword;
    @NotEmpty(groups = password.class)
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
