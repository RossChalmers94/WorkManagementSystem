package web.domain.application;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

public class JobLength
{
    private int jobLengthID;
    @NotBlank
    @Pattern(regexp="[0-9]+", message="Job Length must only consist of numbers")
    private int jobLengthMin;
    @NotBlank
    @Pattern(regexp="[0-9]+", message="Job Length must only consist of numbers")
    private int jobLengthMax;
    private List<JobLength> jobLengths;

    public JobLength() {}

    public int getJobLengthID()
    {
        return jobLengthID;
    }

    public void setJobLengthID(int jobLengthID) {
        this.jobLengthID = jobLengthID;
    }

    public int getJobLengthMin() {
        return jobLengthMin;
    }

    public void setJobLengthMin(int jobLengthMin) {
        this.jobLengthMin = jobLengthMin;
    }

    public int getJobLengthMax() {
        return jobLengthMax;
    }

    public void setJobLengthMax(int jobLengthMax) {
        this.jobLengthMax = jobLengthMax;
    }

    public List<JobLength> getJobLengths() {
        return jobLengths;
    }

    public void setJobLengths(List<JobLength> jobLengths) {
        this.jobLengths = jobLengths;
    }
}