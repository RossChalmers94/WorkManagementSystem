package web.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import web.domain.User;

import java.util.Collection;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import web.enumconstants.UserDetails;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertUser;
    private SimpleJdbcCall checkUser;
    private SimpleJdbcCall insertUserPersonalDetails;
    private SimpleJdbcCall getLogInUser;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertUser = new SimpleJdbcCall(jdbcTemplate);
        this.checkUser = new SimpleJdbcCall(jdbcTemplate);
        this.insertUserPersonalDetails = new SimpleJdbcCall(jdbcTemplate);
        this.getLogInUser = new SimpleJdbcCall(jdbcTemplate);
    }

    public void insert(String storedProc, Map<String, String> inParameters) {
        insertUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertUser.execute(in);
    }

    public int get(String storedProc, Map<String, String> inParameters) {

        int value;
        checkUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map out = checkUser.execute(in);
        value = (Integer)(out.get(UserDetails.USER_VALUE.getValue()));
        return value;
    }

    public void insertPersonal(String storedProc, Map<String, String> inParameters) {
        insertUserPersonalDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertUserPersonalDetails.execute(in);
    }

    public boolean getUserLogIn(String storedProc, Map<String, String> inParameters) {

        Boolean login = false;
        int value = 0;
        getLogInUser.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map out = getLogInUser.execute(inParameters);
        value = (Integer)(out.get(UserDetails.USER_VALUE.getValue()));
        if(value > 0){
            login = true;
        }

        return login;

    }

    public void getUsername(User user){
        String username = this.jdbcTemplate.queryForObject("select username from users where username = ?",
                new Object[] { user.getUsername() }, String.class);
    }
}
