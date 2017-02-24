package web.enumconstants;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public enum PreferenceDetails {

    SKILL_ID("skillID"),
    SKILL_NAME("skillName"),
    LOCATION_ID("locationID"),
    LOCATION_NAME("locationName"),
    SALARY_ID("salaryID"),
    SALARY_MIN("salaryMinValue"),
    SALARY_MAX("salaryMaxValue"),
    JOB_LENGTH_ID("jobLengthID"),
    JOB_LENGTH_MIN("jobLengthMin"),
    JOB_LENGTH_MAX("jobLengthMax");


    private String value;

    PreferenceDetails(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
