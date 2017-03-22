package web.domain.application;

import javax.validation.constraints.NotNull;

public class Rating
{
    @NotNull(message="You must select a rating for your match.")
    private Integer rating;

    public Rating() {}

    public int getRating()
    {
        if (rating == null) {
            return 0;
        }
        return rating.intValue();
    }

    public void setRating(int rating)
    {
        this.rating = Integer.valueOf(rating);
    }
}