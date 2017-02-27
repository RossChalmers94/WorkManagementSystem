package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import web.domain.application.Salary;
import web.enumconstants.AdminDetails;
import web.enumconstants.WorkerDetails;

import javax.sql.DataSource;
import java.util.Map;
import java.util.List;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public class PreferencesDAOImpl implements PreferencesDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall deleteSkills;
    private SimpleJdbcCall updateLocations;
    private SimpleJdbcCall updateJobLengths;
    private SimpleJdbcCall updateSalarys;
    private SimpleJdbcCall updatePassword;
    private SimpleJdbcCall addSkill;
    private SimpleJdbcCall addLocation;
    private SimpleJdbcCall deleteLocation;
    private SimpleJdbcCall updateApplication;
    private SimpleJdbcCall getUpdatePassword;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.deleteSkills = new SimpleJdbcCall(jdbcTemplate);
        this.updateLocations = new SimpleJdbcCall(jdbcTemplate);
        this.updateJobLengths = new SimpleJdbcCall(jdbcTemplate);
        this.updateSalarys = new SimpleJdbcCall(jdbcTemplate);
        this.updatePassword = new SimpleJdbcCall(jdbcTemplate);
        this.addSkill = new SimpleJdbcCall(jdbcTemplate);
        this.addLocation = new SimpleJdbcCall(jdbcTemplate);
        this.deleteLocation = new SimpleJdbcCall(jdbcTemplate);
        this.updateApplication = new SimpleJdbcCall(jdbcTemplate);
        this.updatePassword = new SimpleJdbcCall(jdbcTemplate);
        this.getUpdatePassword = new SimpleJdbcCall(jdbcTemplate);
    }

    public List<Map<String, Object>> getPreferences(String storedProc) {
        List<Map<String, Object>> get = this.jdbcTemplate.queryForList(storedProc);
        return get;
    }

    public Map getAdmin(String storedProc) {
        Map get = this.jdbcTemplate.queryForMap(storedProc);
        return get;
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

    private void update(SimpleJdbcCall storedProc, Map<String, Object> inParameters){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        storedProc.execute(in);
    }

    public void addSkill(String storedProc, String skillName){
        addSkill.withProcedureName(storedProc);
        add(addSkill, "skillName", skillName);
    }

    public void deleteSkill(String storedProc, int skillID){
        deleteSkills.withProcedureName(storedProc);
        delete(deleteSkills, "skillID", skillID);
    }

    public void addLocation(String storedProc, String locationName){
        addLocation.withProcedureName(storedProc);
        add(addLocation, "locationName", locationName);
    }

    public void deleteLocation(String storedProc, int locationID){
        deleteLocation.withProcedureName(storedProc);
        delete(deleteLocation, "locationID", locationID);
    }

    public void updateApplication(String storedProc, String name) {
        updateApplication.withProcedureName("update_admin");
        add(updateApplication, "industryName", name);
    }

    public void updatePassword(String storedProc, String password) {
        updatePassword.withProcedureName(storedProc);
        add(updatePassword, "adminPassword", password);
    }

    private void add(SimpleJdbcCall storedProc, String parameterName, String name){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(parameterName, name);
        storedProc.execute(in);
    }

    private void delete(SimpleJdbcCall storedProc, String parameterName, int id){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(parameterName, id);
        storedProc.execute(in);
    }

    public int checkAdminPassword(String storedProc, String password){
        getUpdatePassword.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(AdminDetails.ADMIN_PASSWORD.getValue(), password);
        Map<String, Object> out = getUpdatePassword.execute(in);
        return (Integer) out.get("adminValue");
    }





}
