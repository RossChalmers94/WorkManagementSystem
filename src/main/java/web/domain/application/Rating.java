package web.domain.application;

import javax.validation.constraints.NotNull;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Rating {

    @NotNull(message = "You must select a rating for your match.")
    private Integer rating;

    public Rating(){
    }

    public int getRating(){
        if(rating == null){
            return 0;
        } else {
            return rating;
        }
    }

    public void setRating(int rating){
        this.rating = rating;
    }


}
