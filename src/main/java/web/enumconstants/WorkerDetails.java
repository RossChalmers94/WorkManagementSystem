package web.enumconstants;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public enum WorkerDetails {

    EMPLOYER_ID("employerID"),
    FREELANCER_ID("freelancerID"),
    SALARY("salary"),
    LOCATION("location"),
    JOB_LENGTH("jobLength"),
    RELAX_PREFERENCES("relaxPreferences"),
    RATING("rating"),
    JOB_MATCH("job_match"),
    JOB_TITLE("jobTitle"),
    JOB_DESCRIPTION("jobDescription");


    private String value;

    WorkerDetails(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
