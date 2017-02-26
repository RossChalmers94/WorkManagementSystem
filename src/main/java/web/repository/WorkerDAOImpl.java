package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import web.enumconstants.MatchDetails;
import web.enumconstants.WorkerDetails;

import javax.sql.DataSource;
import java.util.*;
import web.domain.*;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public class WorkerDAOImpl implements WorkerDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertFreelancer, insertEmployer, insertFreelancerSkills, insertEmployerSkills, deleteFreelancerSkills,
            deleteEmployerSkills, getFreelancerDetails, getEmployerDetails, updateEmployerDetails, updateFreelancerDetails;

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

    public int insertFreelancer(String storedProc, Map<String, Object> inParameters) {
        insertFreelancer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertFreelancer.execute(in);
        return (Integer) out.get(WorkerDetails.FREELANCER_ID.getValue());
    }

    public int insertEmployer(String storedProc, Map<String, Object> inParameters) {
        insertEmployer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertEmployer.execute(in);
        return (Integer) out.get(WorkerDetails.EMPLOYER_ID.getValue());
    }

    public void insertFreelancerSkills(String storedProc, Map<String, Object> inParameters) {
        insertFreelancerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertFreelancerSkills.execute(in);
    }

    public void insertEmployerSkills(String storedProc, Map<String, Object> inParameters) {
        insertEmployerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertEmployerSkills.execute(in);
    }

    public void deleteFreelancerSkills(String storedProc, Map<String, Object> inParameters) {
        deleteFreelancerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        deleteFreelancerSkills.execute(in);
    }

    public void deleteEmployerSkills(String storedProc, Map<String, Object> inParameters) {
        deleteEmployerSkills.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        deleteEmployerSkills.execute(in);
    }

    public Worker getEmployer(String storedProc, int employerID) {
        getEmployerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        Map<String, Object> out = getEmployerDetails.execute(in);
        List<Integer> skills = getSkills("SELECT skillID FROM skill_set WHERE employerID = ?", employerID);
        return configWorker(out, skills);
    }

    public Worker getFreelancer(String storedProc, int freelancerID) {
        getFreelancerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        Map<String, Object> out = getFreelancerDetails.execute(in);
        List<Integer> skills = getSkills("SELECT skillID FROM skill_set WHERE freelancerID = ?", freelancerID);
        return configWorker(out, skills);
    }

    public void updateEmployer(String storedProc, Map<String, Object> inParameters) {
        updateEmployerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        updateEmployerDetails.execute(in);
    }

    public void updateFreelancer(String storedProc, Map<String, Object> inParameters) {
        updateFreelancerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        updateFreelancerDetails.execute(in);
    }

    public List<Integer> getSkills(String storedProc, int id) {
        List<Integer> get = this.jdbcTemplate.queryForList(storedProc, Integer.class, id);
        return get;
    }

    public static Worker configWorker(Map<String, Object> out, List<Integer> skills){
        Worker worker = new Worker();
        if(out.containsKey(WorkerDetails.FREELANCER_ID.getValue())){
            worker.setWorkerID((Integer)out.get(WorkerDetails.FREELANCER_ID.getValue()));
        } else if(out.containsKey(WorkerDetails.EMPLOYER_ID.getValue())){
            worker.setWorkerID((Integer) out.get(WorkerDetails.EMPLOYER_ID.getValue()));
        }
        worker.setSalary((Integer) out.get(WorkerDetails.SALARY.getValue()));
        worker.setLocation((Integer) out.get(WorkerDetails.LOCATION.getValue()));
        worker.setJobLength((Integer) out.get(WorkerDetails.JOB_LENGTH.getValue()));
        worker.setRating((Integer) out.get(WorkerDetails.RATING.getValue()));
        worker.setRelaxPreferences((Integer) out.get(WorkerDetails.RELAX_PREFERENCES.getValue()));
        worker.setMinimumMatch((Integer) out.get(WorkerDetails.MINIMUM_MATCH.getValue()));
        worker.setJobTitle((String) out.get(WorkerDetails.JOB_TITLE.getValue()));
        worker.setJobDescription((String) out.get(WorkerDetails.JOB_DESCRIPTION.getValue()));
        worker.setSkill(skills);
        if(out.get(WorkerDetails.JOB_MATCH.getValue()) != null){
            worker.setJobMatch((Integer) out.get(WorkerDetails.JOB_MATCH.getValue()));
        }

        return worker;
    }
}

