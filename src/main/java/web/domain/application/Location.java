package web.domain.application;

import java.util.List;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Location
{
    public interface addLocation{}
    private int locationID;
    @Pattern(groups={addLocation.class}, regexp="^[a-zA-Z\\s]+$", message="Location must only consist of letters.")
    @Size(groups={addLocation.class}, min=1, max=20, message="Location must not be empty and must be less than 20 characters.")
    private String locationName;
    private List<Location> locations;
    private List<Integer> locationSet;

    public Location() {}

    public int getLocationID()
    {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
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