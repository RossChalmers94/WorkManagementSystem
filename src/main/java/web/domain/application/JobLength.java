package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class JobLength {

    private int jobLengthID;
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$\n", message = "Job Length Minimum Value must be a number")
    private int jobLengthMin;
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$\n", message = "Job Length Maximum Value must be a number")
    private int jobLengthMax;

    private List<JobLength> jobLengths;


    public JobLength(){
    }

    public int getJobLengthID(){
        return jobLengthID;
    }

    public void setJobLengthID(int jobLengthID){
        this.jobLengthID = jobLengthID;
    }

    public int getJobLengthMin(){
        return jobLengthMin;
    }

    public void setJobLengthMin(int jobLengthMin){
        this.jobLengthMin = jobLengthMin;
    }

    public int getJobLengthMax(){
        return jobLengthMax;
    }

    public void setJobLengthMax(int jobLengthMax){
        this.jobLengthMax = jobLengthMax;
    }

    public List<JobLength> getJobLengths() {
        return jobLengths;
    }

    public void setJobLengths(List<JobLength> jobLengths) {
        this.jobLengths = jobLengths;
    }
}
