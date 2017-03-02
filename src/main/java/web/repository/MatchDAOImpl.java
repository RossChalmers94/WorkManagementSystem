package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import web.enumconstants.MatchDetails;
import web.enumconstants.UserDetails;
import web.enumconstants.WorkerDetails;

import javax.sql.DataSource;
import java.util.*;
import web.domain.*;
/**
 * Created by RossChalmers on 26/02/2017.
 */
public class MatchDAOImpl implements MatchDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall getSkill, getLocation, getSalary, getJobLength, insertMatch, getEmployerMatch, getFreelancerMatch,
    completeEmployerMatch, completeFreelancerMatch, deleteMatch, setPreviousEmployer, setPreviousFreelancer;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.getSkill = new SimpleJdbcCall(jdbcTemplate);
        this.getLocation = new SimpleJdbcCall(jdbcTemplate);
        this.getSalary = new SimpleJdbcCall(jdbcTemplate);
        this.getJobLength = new SimpleJdbcCall(jdbcTemplate);
        this.insertMatch = new SimpleJdbcCall(jdbcTemplate);
        this.getEmployerMatch = new SimpleJdbcCall(jdbcTemplate);
        this.getFreelancerMatch = new SimpleJdbcCall(jdbcTemplate);
        this.completeEmployerMatch = new SimpleJdbcCall(jdbcTemplate);
        this.completeFreelancerMatch = new SimpleJdbcCall(jdbcTemplate);
        this.deleteMatch = new SimpleJdbcCall(jdbcTemplate);
        this.setPreviousEmployer = new SimpleJdbcCall(jdbcTemplate);
        this.setPreviousFreelancer = new SimpleJdbcCall(jdbcTemplate);
    }

    public List<Integer> getSkills(String storedProc, int id) {
        List<Integer> get = this.jdbcTemplate.queryForList(storedProc, Integer.class, id);
        return get;
    }

    public List<Worker> getFreelancers(String storedProc){
        List<Worker> freelancers = getWorkers(storedProc, WorkerDetails.FREELANCER_ID.getValue());
        return freelancers;

    }

    public List<Worker> getEmployers(String storedProc){
        List<Worker> employers = getWorkers(storedProc, WorkerDetails.EMPLOYER_ID.getValue());
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
                worker.setSkill(getSkills("SELECT skillID FROM skill_set WHERE employerID = ?", worker.getWorkerID()));
            } else {
                worker.setSkill(getSkills("SELECT skillID FROM skill_set WHERE freelancerID = ?", worker.getWorkerID()));
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
        StringBuffer skillSet = new StringBuffer();
        for(int i = 0; i < worker.getSkill().size(); i++){
            if(i == worker.getSkill().size() - 1){
                skillSet.append(getSkill(worker.getSkill().get(i)));
            } else {
                skillSet.append(getSkill(worker.getSkill().get(i)) + ", ");
            }
        }
        match.setSkills(skillSet.toString());
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

    public int insertMatch(int employerID, int freelancerID){
        insertMatch.withProcedureName("insert_job_match_id");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID)
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID);
        Map<String, Object> out = insertMatch.execute(in);
        return (Integer) out.get("matchID");
    }

    public int getEmployerMatch(int matchID){
        getEmployerMatch.withProcedureName("get_employer_match");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("matchID", matchID);
        Map<String, Object> out = getEmployerMatch.execute(in);
        return (Integer) out.get(WorkerDetails.FREELANCER_ID.getValue());
    }

    public int getFreelancerMatch(int matchID){
        getFreelancerMatch.withProcedureName("get_freelancer_match");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("matchID", matchID);
        Map<String, Object> out = getFreelancerMatch.execute(in);
        return (Integer) out.get(WorkerDetails.EMPLOYER_ID.getValue());
    }

    public void completeEmployerMatch(int employerID, int freelancerID, int rating, int matchID){
        completeEmployerMatch.withProcedureName("insert_previous_employer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID)
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID)
                .addValue("previousRating", rating);
        completeEmployerMatch.execute(in);
        deleteMatch(matchID);
    }

    public void completeFreelancerMatch(int freelancerID, int employerID, int rating, int matchID){
        completeFreelancerMatch.withProcedureName("insert_previous_freelancer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID)
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID)
                .addValue("previousRating", rating);
        completeFreelancerMatch.execute(in);
        deleteMatch(matchID);

    }

    private void deleteMatch(int matchID){
        deleteMatch.withProcedureName("delete_match");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("matchID", matchID);
        deleteMatch.execute(in);
    }

    public void setPreviousEmployerRating(int employerID, int freelancerID, int rating){
        setPreviousEmployer.withProcedureName("update_previous_freelancer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID)
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID)
                .addValue("rating", rating);
        setPreviousEmployer.execute(in);
    }
    public void setPreviousFreelancerRating(int employerID, int freelancerID, int rating){
        setPreviousFreelancer.withProcedureName("update_previous_employer");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue(WorkerDetails.FREELANCER_ID.getValue(), freelancerID)
                .addValue(WorkerDetails.EMPLOYER_ID.getValue(), employerID)
                .addValue("rating", rating);
        setPreviousFreelancer.execute(in);
    }

    public List<Match> getAllMatches() {
        List<Map<String, Object>> out = this.jdbcTemplate.queryForList("SELECT matchID, freelancerID, " +
                "employerID FROM job_match");
        List<Match> matches = new ArrayList<Match>();
        for(int i = 0; i < out.size(); i++){
            Match match = new Match();
            match.setMatchID((Integer) out.get(i).get("matchID"));
            match.setFreelancerID((Integer) out.get(i).get(UserDetails.USER_FREELANCERID.getValue()));
            match.setEmployerID((Integer) out.get(i).get(UserDetails.USER_EMPLOYERID.getValue()));
            matches.add(match);
        }

        return matches;
    }

    private Worker configWorker(Map<String, Object> out){
        Worker worker = new Worker();
        worker.setSalary((Integer) out.get(WorkerDetails.SALARY.getValue()));
        worker.setLocation((Integer) out.get(WorkerDetails.LOCATION.getValue()));
        worker.setJobLength((Integer) out.get(WorkerDetails.JOB_LENGTH.getValue()));
        worker.setRating((Integer) out.get(WorkerDetails.RATING.getValue()));
        worker.setRelaxPreferences((Integer) out.get(WorkerDetails.RELAX_PREFERENCES.getValue()));
        worker.setMinimumMatch((Integer) out.get(WorkerDetails.MINIMUM_MATCH.getValue()));
        worker.setJobTitle((String) out.get(WorkerDetails.JOB_TITLE.getValue()));
        worker.setJobDescription((String) out.get(WorkerDetails.JOB_DESCRIPTION.getValue()));
        if(out.get(WorkerDetails.JOB_MATCH.getValue()) != null){
            worker.setJobMatch((Integer) out.get(WorkerDetails.JOB_MATCH.getValue()));
        }

        return worker;
    }

}
