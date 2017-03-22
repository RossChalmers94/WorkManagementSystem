package web.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import web.domain.User;
import web.domain.UserPersonal;
import web.domain.application.Admin;
import web.enumconstants.UserDetails;
import web.enumconstants.AdminDetails;
import web.enumconstants.WorkerDetails;

import javax.sql.DataSource;

public class UserDAOImpl implements UserDAO
{
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertUser;
    private SimpleJdbcCall getUser;
    private SimpleJdbcCall insertUserPersonalDetails;
    private SimpleJdbcCall getLogInUser;
    private SimpleJdbcCall checkUser;
    private SimpleJdbcCall updateAdminPassword;
    private SimpleJdbcCall updatePassword;
    private SimpleJdbcCall checkAdmin;
    private SimpleJdbcCall getAdmin;
    private SimpleJdbcCall getUserByEmployer;
    private SimpleJdbcCall getUserByFreelancer;
    private SimpleJdbcCall getIndustryName;

    public UserDAOImpl() {}

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        jdbcTemplate = new org.springframework.jdbc.core.JdbcTemplate(dataSource);
        insertUser = new SimpleJdbcCall(jdbcTemplate);
        getUser = new SimpleJdbcCall(jdbcTemplate);
        insertUserPersonalDetails = new SimpleJdbcCall(jdbcTemplate);
        getLogInUser = new SimpleJdbcCall(jdbcTemplate);
        checkUser = new SimpleJdbcCall(jdbcTemplate);
        updateAdminPassword = new SimpleJdbcCall(jdbcTemplate);
        updatePassword = new SimpleJdbcCall(jdbcTemplate);
        checkAdmin = new SimpleJdbcCall(jdbcTemplate);
        getAdmin = new SimpleJdbcCall(jdbcTemplate);
        getUserByEmployer = new SimpleJdbcCall(jdbcTemplate);
        getUserByFreelancer = new SimpleJdbcCall(jdbcTemplate);
        getIndustryName = new SimpleJdbcCall(jdbcTemplate);
    }


    public void insert(Map<String, String> inParameters)
    {
        insertUser.withProcedureName("insert_user");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertUser.execute(in);
    }

    public void insertPersonal(User user)
    {
        insertUserPersonalDetails.withProcedureName("insert_user_personal");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(UserDetails.USER_NAME.getValue(), user.getUsername())
                .addValue(UserDetails.USER_FIRSTNAME.getValue(), user.getUserPersonal().getFirstname())
                .addValue(UserDetails.USER_LASTNAME.getValue(), user.getUserPersonal().getLastname())
                .addValue(UserDetails.USER_TELEPHONE.getValue(), user.getUserPersonal().getTelephone())
                .addValue(UserDetails.USER_EMAILADDRESS.getValue(), user.getUserPersonal().getEmailaddress())
                .addValue(UserDetails.USER_ADDRESS.getValue(), user.getUserPersonal().getAddress())
                .addValue(UserDetails.USER_POSTCODE.getValue(), user.getUserPersonal().getPostcode())
                .addValue(UserDetails.USER_TOWNCITY.getValue(), user.getUserPersonal().getTowncity())
                .addValue(UserDetails.USER_COMPANY.getValue(), user.getUserPersonal().getCompany());
        insertUserPersonalDetails.execute(in);
    }

    public String checkUserLogIn(String username) {
        getLogInUser.withProcedureName("check_user_exists");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(UserDetails.USER_NAME.getValue(), username);
        Map out = getLogInUser.execute(in);
        return (String)out.get(UserDetails.USER_PASSWORD.getValue());
    }


    public User getLogIn(String username)
    {
        getUser.withProcedureName("user_log_in");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(UserDetails.USER_NAME.getValue(), username);
        Map<String, Object> out = getUser.execute(in);
        User user = configUser(out);
        return user;
    }

    public boolean getUsername(String username)
    {
        boolean check = false;
        checkUser.withProcedureName("check_username");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(UserDetails.USER_NAME.getValue(), username);
        Map<String, Object> out = checkUser.execute(in);
        int userValue = ((Integer)out.get(UserDetails.USER_VALUE.getValue()));
        if (userValue == 1) {
            check = true;
        }
        return check;
    }

    public String checkAdminLogIn() {
        checkAdmin.withProcedureName("check_admin");
        Map out = checkAdmin.execute();
        String password = (String)out.get(AdminDetails.ADMIN_PASSWORD.getValue());
        return password;
    }

    public Admin getAdmin() {
        Admin admin = new Admin();
        getAdmin.withProcedureName("admin_log_in");
        Map out = getAdmin.execute();
        admin.setAdminUsername("admin");
        admin.setIndustryName((String)out.get(AdminDetails.INDUSTRY_NAME.getValue()));
        return admin;
    }

    public User getUserByEmployer(int id) {
        getUserByEmployer.withProcedureName("get_user_by_employer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), id);
        Map out = getUserByEmployer.execute(in);
        User user = configUser(out);
        return user;
    }

    public User getUserByFreelancer(int id) {
        getUserByFreelancer.withProcedureName("get_user_by_freelancer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), id);
        Map out = getUserByFreelancer.execute(in);
        User user = configUser(out);
        return user;
    }

    public String getIndustryName() {
        getIndustryName.withProcedureName("get_industry_name");
        Map out = getIndustryName.execute();
        return (String)out.get(AdminDetails.INDUSTRY_NAME.getValue());
    }

    public void updateAdminPassword(String password) {
        updateAdminPassword.withProcedureName("update_admin_password");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(AdminDetails.ADMIN_PASSWORD.getValue(), password);
        updateAdminPassword.execute(in);
    }

    public List<User> getAllUsers() {
        List<Map<String, Object>> out = jdbcTemplate.queryForList("SELECT username, firstName, lastName, freelancerID, employerID FROM users");

        List<User> users = new ArrayList<User>();
        for (int i = 0; i < out.size(); i++) {
            User user = new User();
            user.setUsername((String)(out.get(i)).get(UserDetails.USER_NAME.getValue()));
            user.getUserPersonal().setFirstname((String)(out.get(i)).get(UserDetails.USER_FIRSTNAME.getValue()));
            user.getUserPersonal().setLastname((String)(out.get(i)).get(UserDetails.USER_LASTNAME.getValue()));
            if ((out.get(i)).get(UserDetails.USER_FREELANCERID.getValue()) != null) {
                user.setFreelancerID(((Integer)(out.get(i)).get(UserDetails.USER_FREELANCERID.getValue())));
            }
            if ((out.get(i)).get(UserDetails.USER_EMPLOYERID.getValue()) != null) {
                user.setEmployerID(((Integer)(out.get(i)).get(UserDetails.USER_EMPLOYERID.getValue())));
            }
            users.add(user);
        }

        return users;
    }

    private User configUser(Map<String, Object> outParameters)
    {
        User user = new User();
        user.setUsername((String)outParameters.get(UserDetails.USER_NAME.getValue()));
        user.getUserPersonal().setFirstname((String)outParameters.get(UserDetails.USER_FIRSTNAME.getValue()));
        user.getUserPersonal().setLastname((String)outParameters.get(UserDetails.USER_LASTNAME.getValue()));
        user.getUserPersonal().setTelephone((String)outParameters.get(UserDetails.USER_TELEPHONE.getValue()));
        user.getUserPersonal().setAddress((String)outParameters.get(UserDetails.USER_ADDRESS.getValue()));
        user.getUserPersonal().setEmailaddress((String)outParameters.get(UserDetails.USER_EMAILADDRESS.getValue()));
        user.getUserPersonal().setPostcode((String)outParameters.get(UserDetails.USER_POSTCODE.getValue()));
        user.getUserPersonal().setTowncity((String)outParameters.get(UserDetails.USER_TOWNCITY.getValue()));
        user.getUserPersonal().setCompany((String)outParameters.get(UserDetails.USER_COMPANY.getValue()));
        user.setRole((String)outParameters.get(UserDetails.USER_ROLE.getValue()));
        if (outParameters.get(UserDetails.USER_EMPLOYERID.getValue()) != null) {
            user.setEmployerID(((Integer)outParameters.get(UserDetails.USER_EMPLOYERID.getValue())));
        }
        if (outParameters.get(UserDetails.USER_FREELANCERID.getValue()) != null) {
            user.setFreelancerID(((Integer)outParameters.get(UserDetails.USER_FREELANCERID.getValue())));
        }

        return user;
    }
}