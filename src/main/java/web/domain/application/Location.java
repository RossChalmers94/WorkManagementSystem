package web.domain.application;

import org.hibernate.validator.constraints.NotEmpty;
import org.omg.PortableInterceptor.INACTIVE;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.List;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Location {

    public interface location{}

    private int locationID;
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Size(min = 1, max = 20, message = "Location must not be empty and must be less than 20 characters.")
    private String locationName;
    private List<Location> locations;
    private List<Integer> locationSet;

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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Integer> getLocationSet() {
        return locationSet;
    }

    public void setLocationSet(List<Integer> locationSet) {
        this.locationSet = locationSet;
    }
}
