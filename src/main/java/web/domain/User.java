package web.domain;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class User
{

    public interface createAccount{}
    public interface logIn{}

    @Size(min=5, max=20, groups={createAccount.class, logIn.class}, message="Username must be between 5-20 characters.")
    private String username;
    @Size(min=5, max=20, groups={createAccount.class, logIn.class}, message="Password must be between 5-20 characters.")
    private String password;
    @NotEmpty(groups={createAccount.class}, message="You must select a Role.")
    private String role;
    private UserPersonal userPersonal;
    private Worker userWorker;
    private int freelancerID = 0;
    private int employerID = 0;

    public User()
    {
        userPersonal = new UserPersonal();
        userWorker = new Worker();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getRole() {
        return role;
    }

    public UserPersonal getUserPersonal() {
        return userPersonal;
    }

    public void setUserPersonal(UserPersonal userPersonal) {
        this.userPersonal = userPersonal;
    }

    public Worker getUserWorker() {
        return userWorker;
    }

    public void setUserWorker(Worker userWorker) {
        this.userWorker = userWorker;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }

    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }
}