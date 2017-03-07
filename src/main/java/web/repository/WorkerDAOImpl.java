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
            deleteEmployerSkills, getFreelancerDetails, getEmployerDetails, updateEmployerDetails, updateFreelancerDetails,
            deleteEmployer, deleteFreelancer;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertFreelancer = new SimpleJdbcCall(jdbcTemplate);
        this.insertEmployer = new SimpleJdbcCall(jdbcTemplate);
        this.insertFreelancerSkills = new SimpleJdbcCall(jdbcTemplate);
        this.insertEmployerSkills = new SimpleJdbcCall(jdbcTemplate);
        this.deleteEmployer = new SimpleJdbcCall(jdbcTemplate);
        this.deleteFreelancer = new SimpleJdbcCall(jdbcTemplate);
        this.getFreelancerDetails = new SimpleJdbcCall(jdbcTemplate);
        this.getEmployerDetails = new SimpleJdbcCall(jdbcTemplate);
        this.updateEmployerDetails = new SimpleJdbcCall(jdbcTemplate);
        this.updateFreelancerDetails = new SimpleJdbcCall(jdbcTemplate);
    }

    public int insertFreelancer(Map<String, Object> inParameters) {
        insertFreelancer.withProcedureName("insert_freelancer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertFreelancer.execute(in);
        return (Integer) out.get(WorkerDetails.FREELANCER_ID.getValue());
    }

    public int insertEmployer(Map<String, Object> inParameters) {
        insertEmployer.withProcedureName("insert_employer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertEmployer.execute(in);
        return (Integer) out.get(WorkerDetails.EMPLOYER_ID.getValue());
    }

    public void insertFreelancerSkills(Map<String, Object> inParameters) {
        insertFreelancerSkills.withProcedureName("insert_freelancer_skills");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertFreelancerSkills.execute(in);
    }

    public void insertEmployerSkills(Map<String, Object> inParameters) {
        insertEmployerSkills.withProcedureName("insert_employer_skills");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        insertEmployerSkills.execute(in);
    }

    public Worker getEmployer(int employerID) {
        getEmployerDetails.withProcedureName("get_employer_details");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        Map<String, Object> out = getEmployerDetails.execute(in);
        List<Integer> skills = getSkills("SELECT skillID FROM skill_set WHERE employerID = ?", employerID);
        return configWorker(out, skills);
    }

    public Worker getFreelancer(int freelancerID) {
        getFreelancerDetails.withProcedureName("get_freelancer_details");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        Map<String, Object> out = getFreelancerDetails.execute(in);
        List<Integer> skills = getSkills("SELECT skillID FROM skill_set WHERE freelancerID = ?", freelancerID);
        return configWorker(out, skills);
    }

    public void updateEmployer(Map<String, Object> inParameters) {
        updateEmployerDetails.withProcedureName("update_employer_details");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        updateEmployerDetails.execute(in);
    }

    public void updateFreelancer(Map<String, Object> inParameters) {
        updateFreelancerDetails.withProcedureName("update_freelancer_details");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        updateFreelancerDetails.execute(in);
    }

    public List<Integer> getSkills(String storedProc, int id) {
        List<Integer> get = this.jdbcTemplate.queryForList(storedProc, Integer.class, id);
        return get;
    }

    public void deleteEmployer(String storedProc, int employerID) {
        deleteEmployer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        deleteEmployer.execute(in);
    }

    public void deleteFreelancer(String storedProc, int freelancerID) {
        deleteFreelancer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        deleteFreelancer.execute(in);
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
        if(out.get("previousRating") != null){
            worker.setPreviousRating((Integer) out.get(WorkerDetails.PREVIOUS_RATING.getValue()));
        }
        if(out.get("previousMatch") != null) {
            worker.setPreviousMatch((Integer) out.get(WorkerDetails.PREVIOUS_MATCH.getValue()));
        }

        return worker;
    }
}

