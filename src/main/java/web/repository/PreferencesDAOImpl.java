package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.Map;
import java.util.List;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public class PreferencesDAOImpl implements PreferencesDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall updateSkills;
    private SimpleJdbcCall updateLocations;
    private SimpleJdbcCall updateJobLengths;
    private SimpleJdbcCall updateSalarys;
    private SimpleJdbcCall updatePassword;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.updateSkills = new SimpleJdbcCall(jdbcTemplate);
        this.updateLocations = new SimpleJdbcCall(jdbcTemplate);
        this.updateJobLengths = new SimpleJdbcCall(jdbcTemplate);
        this.updateSalarys = new SimpleJdbcCall(jdbcTemplate);
        this.updatePassword = new SimpleJdbcCall(jdbcTemplate);
    }

    public List<Map<String, Object>> getPreferences(String storedProc) {
        List<Map<String, Object>> get = this.jdbcTemplate.queryForList(storedProc);
        return get;
    }

    public Map getAdmin(String storedProc) {
        Map get = this.jdbcTemplate.queryForMap(storedProc);
        return get;
    }

    public void updateSkills(String storedProc, Map<String, Object> inParameters){
        updateSkills.withProcedureName(storedProc);
        update(updateSkills, inParameters);
    }
    public void updateLocations(String storedProc, Map<String, Object> inParameters){
        updateLocations.withProcedureName(storedProc);
        update(updateLocations, inParameters);
    }
    public void updateJobLengths(String storedProc, Map<String, Object> inParameters){
        updateJobLengths.withProcedureName(storedProc);
        update(updateJobLengths, inParameters);
    }
    public void updateSalarys(String storedProc, Map<String, Object> inParameters){
        updateSalarys.withProcedureName(storedProc);
        update(updateSalarys, inParameters);
    }

    public void updatePassword(String storedProc, String parameterName, String parameterValue){
        updatePassword.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(parameterName, parameterValue);
        updatePassword.execute(in);
    }

    private void update(SimpleJdbcCall storedProc, Map<String, Object> inParameters){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        storedProc.execute(in);
    }

}
