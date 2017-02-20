package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class JobLength {

    private int jobLengthID;
    private int jobLengthMin;
    private int jobLengthMax;


    public JobLength(int jobLengthID, int jobLengthMin, int jobLengthMax){
        this.jobLengthID = jobLengthID;
        this.jobLengthMin = jobLengthMin;
        this.jobLengthMax = jobLengthMax;
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


}
