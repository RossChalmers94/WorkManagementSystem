package web.domain.application;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Rating {

    private String ratingValue;

    public Rating(){
    }

    public String getRatingValue(){
        return ratingValue;
    }

    public void setRatingValue(String ratingValue){
        this.ratingValue = ratingValue.trim();
    }


}
