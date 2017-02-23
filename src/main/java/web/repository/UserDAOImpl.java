package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import web.enumconstants.AdminDetails;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertUser;
    private SimpleJdbcCall getUser;
    private SimpleJdbcCall insertUserPersonalDetails;
    private SimpleJdbcCall getLogInUser;
    private SimpleJdbcCall checkUser;
    private SimpleJdbcCall insertEmployerID;
    private SimpleJdbcCall insertFreelancerID;
    private SimpleJdbcCall checkAdmin;
    private SimpleJdbcCall getAdmin;


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

    public Map<String, Object> checkUserLogIn(String storedProc, Map<String, String> inParameters) {

        getLogInUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map out = getLogInUser.execute(inParameters);
        return out;
    }

    // Get a user from the users table
    public Map<String, Object> get(String storedProc, Map<String, String> inParameters){

        getUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = getUser.execute(in);
        return out;
    }

    // To check if a username already exists
    public Map<String, Object> getUsername(String storedProc, Map<String, String> inParameters){

        checkUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = checkUser.execute(in);
        return out;
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

    public Map<String, Object> checkAdminLogIn(String storedProc, String adminPassword){
        checkAdmin.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(AdminDetails.ADMIN_PASSWORD.getValue(), adminPassword);
        Map out = checkAdmin.execute(in);
        return out;
    }

    public Map<String, Object> getAdmin(String storedProc, Map<String, Object> inParameters){
        getAdmin.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map out = getAdmin.execute(in);
        return out;
    }
}
