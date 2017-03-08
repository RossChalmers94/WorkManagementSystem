package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import web.domain.application.Admin;
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

    public Admin getAdmin() {
        Map out = this.jdbcTemplate.queryForMap("SELECT adminUsername, adminPassword, industryName, databaseServer FROM application");
        Admin admin = new Admin();
        admin.setAdminUsername((String)out.get("adminUsername"));
        admin.setPassword((String)out.get("adminPassword"));
        admin.setIndustryName((String)out.get("industryName"));
        admin.setDatabaseServer((String)out.get("databaseServer"));
        return admin;
    }

    public void updateJobLengths(Map<String, Object> inParameters){
        updateJobLengths.withProcedureName("update_joblengths");
        update(updateJobLengths, inParameters);
    }
    public void updateSalarys(Map<String, Object> inParameters){
        updateSalarys.withProcedureName("update_salarys");
        update(updateSalarys, inParameters);
    }

    private void update(SimpleJdbcCall storedProc, Map<String, Object> inParameters){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        storedProc.execute(in);
    }

    public void addSkill(String skillName){
        addSkill.withProcedureName("insert_skill");
        add(addSkill, "skillName", skillName);
    }

    public void deleteSkill(int skillID){
        deleteSkills.withProcedureName("delete_skill");
        delete(deleteSkills, "skillID", skillID);
    }

    public void addLocation(String locationName){
        addLocation.withProcedureName("insert_location");
        add(addLocation, "locationName", locationName);
    }

    public void deleteLocation(int locationID){
        deleteLocation.withProcedureName("delete_location");
        delete(deleteLocation, "locationID", locationID);
    }

    public void updateApplication(String name) {
        updateApplication.withProcedureName("update_admin");
        add(updateApplication, "industryName", name);
    }

    public void updatePassword(String password) {
        updatePassword.withProcedureName("update_admin_password");
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

    public boolean checkAdminPassword(String password){
        getUpdatePassword.withProcedureName("check_admin");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(AdminDetails.ADMIN_PASSWORD.getValue(), password);
        Map<String, Object> out = getUpdatePassword.execute(in);
        int value = (Integer) out.get("adminValue");
        if(value == 1){
            return true;
        } else {
            return false;
        }
    }





}
