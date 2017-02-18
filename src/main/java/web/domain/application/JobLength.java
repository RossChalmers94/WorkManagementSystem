package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class JobLength {

    private String jobMinValue;
    private String jobMaxValue;


    public JobLength(String jobMinValue, String jobMaxValue){
        this.jobMinValue = jobMinValue;
        this.jobMaxValue = jobMaxValue;
    }

    public String getJobMinValue(){
        return jobMinValue;
    }

    public void setJobMinValue(String jobMinValue){
        this.jobMinValue = jobMinValue.trim();
    }

    public String getJobMaxValue(){
        return jobMaxValue;
    }

    public void setJobMaxValue(String jobMaxValue){
        this.jobMaxValue = jobMaxValue.trim();
    }


}
