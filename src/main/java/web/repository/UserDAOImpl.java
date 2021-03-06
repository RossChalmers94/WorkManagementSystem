package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.ArrayList;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import web.enumconstants.AdminDetails;
import web.domain.User;
import web.enumconstants.UserDetails;
import web.enumconstants.WorkerDetails;
import web.domain.application.Admin;
import java.util.List;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertUser, getUser,insertUserPersonalDetails,getLogInUser,checkUser,
            insertEmployerID,insertFreelancerID,checkAdmin,getAdmin,getUserByEmployer, getUserByFreelancer, getIndustryName;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertUser = new SimpleJdbcCall(jdbcTemplate);
        this.getUser = new SimpleJdbcCall(jdbcTemplate);
        this.insertUserPersonalDetails = new SimpleJdbcCall(jdbcTemplate);
        this.getLogInUser = new SimpleJdbcCall(jdbcTemplate);
        this.checkUser = new SimpleJdbcCall(jdbcTemplate);
        this.insertEmployerID = new SimpleJdbcCall(jdbcTemplate);
        this.insertFreelancerID = new SimpleJdbcCall(jdbcTemplate);
        this.checkAdmin = new SimpleJdbcCall(jdbcTemplate);
        this.getAdmin = new SimpleJdbcCall(jdbcTemplate);
        this.getUserByEmployer = new SimpleJdbcCall(jdbcTemplate);
        this.getUserByFreelancer = new SimpleJdbcCall(jdbcTemplate);
        this.getIndustryName = new SimpleJdbcCall(jdbcTemplate);

    }

    // Insert username, password and role after a user creates their account
    public void insert(String storedProc, Map<String, String> inParameters) {
        insertUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertUser.execute(in);
    }

    // Insert Personal Details into the database
    public void insertPersonal(String storedProc, Map<String, String> inParameters) {
        insertUserPersonalDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertUserPersonalDetails.execute(in);
    }

    public String checkUserLogIn(String storedProc, Map<String, String> inParameters) {
        getLogInUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map out = getLogInUser.execute(inParameters);
        return (String) out.get(UserDetails.USER_PASSWORD.getValue());
    }

    // Get a user from the users table
    public User get(String storedProc, Map<String, String> inParameters){

        getUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = getUser.execute(in);
        User user = configUser(out);
        return user;
    }

    // To check if a username already exists
    public boolean getUsername(String storedProc, String username){
        boolean check = false;
        checkUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(UserDetails.USER_NAME.getValue(), username);
        Map<String, Object> out = checkUser.execute(in);
        int userValue = (Integer) out.get(UserDetails.USER_VALUE.getValue());
        if(userValue == 1){
            check = true;
        }
        return check;
    }

    // Insert a user's EmployerID that relates to their record in the Employer table
    public void insertEmployerID(String storedProc, Map<String, Object> inParameters){
        insertEmployerID.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertEmployerID.execute(in);
    }

    // Insert a user's FreelancerID that relates to their record in the Freelancer table
    public void insertFreelancerID(String storedProc, Map<String, Object> inParameters){
        insertFreelancerID.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertFreelancerID.execute(in);
    }

    public boolean checkAdminLogIn(String storedProc, String adminPassword){
        boolean check = false;
        checkAdmin.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(AdminDetails.ADMIN_PASSWORD.getValue(), adminPassword);
        Map out = checkAdmin.execute(in);
        int adminLogInValue = (Integer) out.get(AdminDetails.ADMIN_USERVALUE.getValue());
        if(adminLogInValue == 1){
            check = true;
        }
        return check;
    }

    public Admin getAdmin(String storedProc, Map<String, Object> inParameters){
        Admin admin = new Admin();
        getAdmin.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map out = getAdmin.execute(in);
        admin.setAdminUsername("admin");
        admin.setPassword((String) out.get(AdminDetails.ADMIN_PASSWORD.getValue()));
        admin.setIndustryName((String) out.get(AdminDetails.INDUSTRY_NAME.getValue()));
        admin.setDatabaseServer((String) out.get(AdminDetails.DATABASE_SERVER.getValue()));
        return admin;
    }

    public User getUserByEmployer(String storedProc, int id){
        getUserByEmployer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), id);
        Map out = getUserByEmployer.execute(in);
        User user = configUser(out);
        return user;
    }

    public User getUserByFreelancer(String storedProc, int id){
        getUserByFreelancer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), id);
        Map out = getUserByFreelancer.execute(in);
        User user = configUser(out);
        return user;
    }

    public String getIndustryName(){
        getIndustryName.withProcedureName("get_industry_name");
        Map out = getIndustryName.execute();
        return (String) out.get(AdminDetails.INDUSTRY_NAME.getValue());
    }

    public List<User> getAllUsers(){
        List<Map<String, Object>> out = this.jdbcTemplate.queryForList("SELECT username, firstName, lastName, " +
                "freelancerID, employerID FROM users");
        List<User> users = new ArrayList<User>();
        for(int i = 0; i < out.size(); i++){
            User user = new User();
            user.setUsername((String) out.get(i).get(UserDetails.USER_NAME.getValue()));
            user.setFirstname((String) out.get(i).get(UserDetails.USER_FIRSTNAME.getValue()));
            user.setLastname((String) out.get(i).get(UserDetails.USER_LASTNAME.getValue()));
            if(out.get(i).get(UserDetails.USER_FREELANCERID.getValue()) != null) {
                user.setFreelancerID((Integer) out.get(i).get(UserDetails.USER_FREELANCERID.getValue()));
            }
            if(out.get(i).get(UserDetails.USER_EMPLOYERID.getValue()) != null) {
                user.setEmployerID((Integer) out.get(i).get(UserDetails.USER_EMPLOYERID.getValue()));
            }
            users.add(user);
        }

        return users;

    }

    private User configUser(Map<String, Object> outParameters){
        User user = new User();
        user.setUsername((String) outParameters.get(UserDetails.USER_NAME.getValue()));
        user.setFirstname((String) outParameters.get(UserDetails.USER_FIRSTNAME.getValue()));
        user.setLastname((String) outParameters.get(UserDetails.USER_LASTNAME.getValue()));
        user.setTelephone((String) outParameters.get(UserDetails.USER_TELEPHONE.getValue()));
        user.setAddress((String) outParameters.get(UserDetails.USER_ADDRESS.getValue()));
        user.setEmailaddress((String) outParameters.get(UserDetails.USER_EMAILADDRESS.getValue()));
        user.setPostcode((String) outParameters.get(UserDetails.USER_POSTCODE.getValue()));
        user.setTowncity((String) outParameters.get(UserDetails.USER_TOWNCITY.getValue()));
        user.setCompany((String) outParameters.get(UserDetails.USER_COMPANY.getValue()));
        user.setRole((String) outParameters.get(UserDetails.USER_ROLE.getValue()));
        if(outParameters.get(UserDetails.USER_RATING.getValue()) != null){
            user.setRating((Integer) outParameters.get(UserDetails.USER_RATING.getValue()));
        }
        if(outParameters.get(UserDetails.USER_EMPLOYERID.getValue()) != null){
            user.setEmployerID((Integer)outParameters.get(UserDetails.USER_EMPLOYERID.getValue()));
        }
        if(outParameters.get(UserDetails.USER_FREELANCERID.getValue()) != null){
            user.setFreelancerID((Integer)outParameters.get(UserDetails.USER_FREELANCERID.getValue()));
        }

        return user;
    }
}
