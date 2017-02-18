package web.domain.application;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by RossChalmers on 09/02/2017.
 */
public class Location {

    private Integer locationID;
    private String locationName;

    public Location(Integer locationID, String locationName){
        this.locationName = locationName;
        this.locationID = locationID;
    }

    public int getLocationID(){
        return locationID;
    }

    public void setLocationID(Integer locationID){
        this.locationID = locationID;
    }

    public String getLocationName(){
        return locationName;
    }

    public void setLocationName(String locationName){
        this.locationName = locationName.trim();
    }


}
