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
            getSkill, getLocation, getSalary, getJobLength;

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
        this.getSkill = new SimpleJdbcCall(jdbcTemplate);
        this.getLocation = new SimpleJdbcCall(jdbcTemplate);
        this.getSalary = new SimpleJdbcCall(jdbcTemplate);
        this.getJobLength = new SimpleJdbcCall(jdbcTemplate);
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
                "jobLength, jobTitle, jobDescription FROM employer WHERE jobMatch IS NULL", WorkerDetails.EMPLOYER_ID.getValue());
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
            if(parameterName.equals(WorkerDetails.EMPLOYER_ID.getValue())){
                worker.setJobTitle((String) allWorkers.get(i).get(WorkerDetails.JOB_TITLE.getValue()));
                worker.setJobDescription((String) allWorkers.get(i).get(WorkerDetails.JOB_DESCRIPTION.getValue()));
            }
            workers.add(worker);
        }

        return workers;
    }

    public Match getMatch(Worker worker){
        Match match = new Match();
        match.setMatchID(worker.getWorkerID());
        match.setSalary(getSalary(worker.getSalary()));
        match.setLocation(getLocation(worker.getLocation()));
        match.setJobLength(getJobLength(worker.getJobLength()));
        match.setJobTitle(worker.getJobTitle());
        match.setJobDescription(worker.getJobDescription());
        return match;
    }

    private String getSalary(int salaryID){
        getSalary.withProcedureName("get_salary");
        Map<String, Object> out = getPreferences(getSalary, MatchDetails.SALARY_ID.getValue(), salaryID);
        return (String) out.get("salaryMinValue") + " - " + out.get("salaryMaxValue");
    }

    private String getLocation(int locationID){
        getLocation.withProcedureName("get_location");
        Map<String, Object> out = getPreferences(getLocation, MatchDetails.LOCATION_ID.getValue(), locationID);
        return (String) out.get("locationName");
    }

    private String getJobLength(int jobLengthID){
        getJobLength.withProcedureName("get_joblength");
        Map<String, Object> out = getPreferences(getJobLength, MatchDetails.JOB_LENGTH_ID.getValue(),jobLengthID);
        return (String) out.get("jobLengthMin") + " - " + (String) out.get("jobLengthMax");
    }

    private String getSkill(int skillID){
        getSkill.withProcedureName("get_skill");
        Map<String, Object> out = getPreferences(getSkill, MatchDetails.SKILL_ID.getValue(), skillID);
        return (String) out.get("skillName");
    }

    private Map<String, Object> getPreferences(SimpleJdbcCall simpleJdbcCall, String parameterName, int id){
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(parameterName, id);
        Map<String, Object> out = simpleJdbcCall.execute(in);
        return out;
    }
}

