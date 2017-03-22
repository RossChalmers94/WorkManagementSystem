package web.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import web.domain.Worker;
import web.enumconstants.WorkerDetails;
import javax.sql.DataSource;

public class WorkerDAOImpl implements WorkerDAO
{
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertFreelancer;
    private SimpleJdbcCall insertEmployer;
    private SimpleJdbcCall insertFreelancerSkills;
    private SimpleJdbcCall insertEmployerSkills;
    private SimpleJdbcCall getFreelancerDetails;
    private SimpleJdbcCall getEmployerDetails;
    private SimpleJdbcCall updateEmployerDetails;
    private SimpleJdbcCall updateFreelancerDetails;
    private SimpleJdbcCall deleteEmployer;
    private SimpleJdbcCall deleteFreelancer;
    private SimpleJdbcCall deleteEmployerSkills;
    private SimpleJdbcCall deleteFreelancerSkills;

    public WorkerDAOImpl() {}

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        jdbcTemplate = new org.springframework.jdbc.core.JdbcTemplate(dataSource);
        insertFreelancer = new SimpleJdbcCall(jdbcTemplate);
        insertEmployer = new SimpleJdbcCall(jdbcTemplate);
        insertFreelancerSkills = new SimpleJdbcCall(jdbcTemplate);
        insertEmployerSkills = new SimpleJdbcCall(jdbcTemplate);
        deleteEmployer = new SimpleJdbcCall(jdbcTemplate);
        deleteFreelancer = new SimpleJdbcCall(jdbcTemplate);
        getFreelancerDetails = new SimpleJdbcCall(jdbcTemplate);
        getEmployerDetails = new SimpleJdbcCall(jdbcTemplate);
        updateEmployerDetails = new SimpleJdbcCall(jdbcTemplate);
        updateFreelancerDetails = new SimpleJdbcCall(jdbcTemplate);
        deleteEmployerSkills = new SimpleJdbcCall(jdbcTemplate);
        deleteFreelancerSkills = new SimpleJdbcCall(jdbcTemplate);
    }

    public int insertFreelancer(Map<String, Object> inParameters) {
        insertFreelancer.withProcedureName("insert_freelancer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertFreelancer.execute(in);
        return ((Integer)out.get(WorkerDetails.FREELANCER_ID.getValue()));
    }

    public int insertEmployer(Map<String, Object> inParameters) {
        insertEmployer.withProcedureName("insert_employer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertEmployer.execute(in);
        return ((Integer)out.get(WorkerDetails.EMPLOYER_ID.getValue()));
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
        List<Integer> get = jdbcTemplate.queryForList(storedProc, Integer.class, id);
        return get;
    }

    public void deleteEmployer(int employerID) {
        deleteEmployer.withProcedureName("delete_employer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        deleteEmployer.execute(in);
    }

    public void deleteFreelancer(int freelancerID) {
        deleteFreelancer.withProcedureName("delete_freelancer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        deleteFreelancer.execute(in);
    }

    public void deleteEmployerSkills(int employerID) {
        deleteEmployerSkills.withProcedureName("delete_employer_skills");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID);
        deleteEmployerSkills.execute(in);
    }

    public void deleteFreelancerSkills(int freelancerID) {
        deleteFreelancerSkills.withProcedureName("delete_freelancer_skills");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        deleteFreelancerSkills.execute(in);
    }

    public static Worker configWorker(Map<String, Object> out, List<Integer> skills) {
        web.domain.Worker worker = new web.domain.Worker();
        if (out.containsKey(WorkerDetails.FREELANCER_ID.getValue())) {
            worker.setWorkerID(((Integer)out.get(WorkerDetails.FREELANCER_ID.getValue())));
        } else if (out.containsKey(WorkerDetails.EMPLOYER_ID.getValue())) {
            worker.setWorkerID(((Integer)out.get(WorkerDetails.EMPLOYER_ID.getValue())));
        }
        worker.setSalary(((Integer)out.get(WorkerDetails.SALARY.getValue())));
        worker.setLocation(((Integer)out.get(WorkerDetails.LOCATION.getValue())));
        worker.setJobLength(((Integer)out.get(WorkerDetails.JOB_LENGTH.getValue())));
        worker.setRating(((Integer)out.get(WorkerDetails.RATING.getValue())));
        worker.setRelaxPreferences(((Integer)out.get(WorkerDetails.RELAX_PREFERENCES.getValue())));
        worker.setMinimumMatch(((Integer)out.get(WorkerDetails.MINIMUM_MATCH.getValue())));
        worker.setJobTitle((String)out.get(WorkerDetails.JOB_TITLE.getValue()));
        worker.setJobDescription((String)out.get(WorkerDetails.JOB_DESCRIPTION.getValue()));
        worker.setSkill(skills);
        if (out.get(WorkerDetails.JOB_MATCH.getValue()) != null) {
            worker.setJobMatch(((Integer)out.get(WorkerDetails.JOB_MATCH.getValue())));
        }
        if (out.get("previousRating") != null) {
            worker.setPreviousRating(((Integer)out.get(WorkerDetails.PREVIOUS_RATING.getValue())));
        }
        if (out.get("previousMatch") != null) {
            worker.setPreviousMatch(((Integer)out.get(WorkerDetails.PREVIOUS_MATCH.getValue())));
        }

        return worker;
    }
}