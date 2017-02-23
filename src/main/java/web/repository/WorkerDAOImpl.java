package web.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import web.enumconstants.WorkerDetails;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public class WorkerDAOImpl implements WorkerDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertFreelancer;
    private SimpleJdbcCall insertEmployer;
    private SimpleJdbcCall insertFreelancerSkills;
    private SimpleJdbcCall insertEmployerSkills;
    private SimpleJdbcCall deleteFreelancerSkills;
    private SimpleJdbcCall deleteEmployerSkills;
    private SimpleJdbcCall getFreelancerDetails;
    private SimpleJdbcCall getEmployerDetails;
    private SimpleJdbcCall updateEmployerDetails;
    private SimpleJdbcCall updateFreelancerDetails;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertFreelancer = new SimpleJdbcCall(jdbcTemplate);
        this.insertEmployer = new SimpleJdbcCall(jdbcTemplate);
        this.insertFreelancerSkills = new SimpleJdbcCall(jdbcTemplate);
        this.insertEmployerSkills = new SimpleJdbcCall(jdbcTemplate);
        this.deleteFreelancerSkills = new SimpleJdbcCall(jdbcTemplate);
        this.deleteEmployerSkills = new SimpleJdbcCall(jdbcTemplate);
        this.getFreelancerDetails = new SimpleJdbcCall(jdbcTemplate);
        this.getEmployerDetails = new SimpleJdbcCall(jdbcTemplate);
        this.updateEmployerDetails = new SimpleJdbcCall(jdbcTemplate);
        this.updateFreelancerDetails = new SimpleJdbcCall(jdbcTemplate);
    }

    public Map<String, Object> insertFreelancer(String storedProc, Map<String, Object> inParameters){
        insertFreelancer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertFreelancer.execute(in);
        return out;
    }

    public Map<String, Object> insertEmployer(String storedProc, Map<String, Object> inParameters){
        insertEmployer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertEmployer.execute(in);
        return out;
    }

    public void insertFreelancerSkills(String storedProc, Map<String, Object> inParameters){
        insertFreelancerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertFreelancerSkills.execute(in);
    }

    public void insertEmployerSkills(String storedProc, Map<String, Object> inParameters){
        insertEmployerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertEmployerSkills.execute(in);
    }

    public void deleteFreelancerSkills(String storedProc, Map<String, Object> inParameters){
        deleteFreelancerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        deleteFreelancerSkills.execute(in);
    }

    public void deleteEmployerSkills(String storedProc, Map<String, Object> inParameters){
        deleteEmployerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        deleteEmployerSkills.execute(in);
    }

    public Map<String, Object> getEmployer(String storedProc, int employerID){
        getEmployerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        Map<String, Object> out = getEmployerDetails.execute(in);
        return out;
    }

    public Map<String, Object> getFreelancer(String storedProc, int freelancerID){
        getFreelancerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        Map<String, Object> out = getFreelancerDetails.execute(in);
        return out;
    }

    public Map<String, Object> updateEmployer(String storedProc, Map<String, Object> inParameters){
        updateEmployerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = updateEmployerDetails.execute(in);
        return out;
    }

    public Map<String, Object> updateFreelancer(String storedProc, Map<String, Object> inParameters){
        updateFreelancerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = updateFreelancerDetails.execute(in);
        return out;
    }

}
