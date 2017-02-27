package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;
import org.omg.PortableInterceptor.INACTIVE;

import javax.validation.constraints.Pattern;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Location {

    private int locationID;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]+")
    private String locationName;

    public Location(){
    }

    public int getLocationID(){
        return locationID;
    }

    public void setLocationID(int locationID){
        this.locationID = locationID;
    }

    public String getLocationName(){
        return locationName;
    }

    public void setLocationName(String locationName){
        this.locationName = locationName.trim();
    }


}
