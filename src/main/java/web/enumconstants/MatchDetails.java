package web.enumconstants;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public enum MatchDetails {

    SALARY_ID("salaryID"),
    LOCATION_ID("locationID"),
    JOB_LENGTH_ID("jobLengthID"),
    RATING_ID("ratingID"),
    SKILL_ID("skillID");


    private String value;

    MatchDetails(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
