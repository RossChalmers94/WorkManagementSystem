package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
            getFreelancers, getEmployers;

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
        this.getFreelancers = new SimpleJdbcCall(jdbcTemplate);
        this.getEmployers = new SimpleJdbcCall(jdbcTemplate);
    }

    public Map<String, Object> insertFreelancer(String storedProc, Map<String, Object> inParameters) {
        insertFreelancer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertFreelancer.execute(in);
        return out;
    }

    public Map<String, Object> insertEmployer(String storedProc, Map<String, Object> inParameters) {
        insertEmployer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertEmployer.execute(in);
        return out;
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

    public Map<String, Object> getEmployer(String storedProc, int employerID) {
        getEmployerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        Map<String, Object> out = getEmployerDetails.execute(in);
        return out;
    }

    public Map<String, Object> getFreelancer(String storedProc, int freelancerID) {
        getFreelancerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        Map<String, Object> out = getFreelancerDetails.execute(in);
        return out;
    }

    public Map<String, Object> updateEmployer(String storedProc, Map<String, Object> inParameters) {
        updateEmployerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = updateEmployerDetails.execute(in);
        return out;
    }

    public Map<String, Object> updateFreelancer(String storedProc, Map<String, Object> inParameters) {
        updateFreelancerDetails.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = updateFreelancerDetails.execute(in);
        return out;
    }

    public List<Integer> getSkills(String storedProc, int id) {
        List<Integer> get = this.jdbcTemplate.queryForList(storedProc, Integer.class, id);
        return get;
    }

    public List<Worker> getFreelancers(){
        List<Worker> freelancers = getWorkers("SELECT freelancerID, salary, location, " +
                "jobLength, rating, minimumMatch FROM freelancer WHERE jobMatch IS NULL", WorkerDetails.FREELANCER_ID.getValue());
        return freelancers;

    }

    public List<Worker> getEmployers(){
        List<Worker> employers = getWorkers("SELECT employerID, salary, location, " +
                "jobLength, rating, minimumMatch FROM employer WHERE jobMatch IS NULL", WorkerDetails.EMPLOYER_ID.getValue());
        return employers;
    }

    private List<Worker> getWorkers(String storedProc, String parameterName){
        List<Map<String, Object>> allWorkers = this.jdbcTemplate.queryForList(storedProc);
        List<Worker> workers = new ArrayList<Worker>();
        for(int i = 0; i < allWorkers.size(); i++){
            Worker worker = new Worker();
            worker.setWorkerID((Integer) allWorkers.get(i).get(parameterName));
            worker.setSalary((Integer) allWorkers.get(i).get(WorkerDetails.SALARY.getValue()));
            worker.setLocation((Integer) allWorkers.get(i).get(WorkerDetails.LOCATION.getValue()));
            worker.setJobLength((Integer) allWorkers.get(i).get(WorkerDetails.JOB_LENGTH.getValue()));
            worker.setRating((Integer) allWorkers.get(i).get(WorkerDetails.RATING.getValue()));
            worker.setMinimumMatch((Integer) allWorkers.get(i).get(WorkerDetails.MINIMUM_MATCH.getValue()));
            workers.add(worker);
        }

        return workers;
    }
}

