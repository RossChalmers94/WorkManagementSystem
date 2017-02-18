package web.domain.repository;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import sun.java2d.pipe.SpanShapeRenderer;
import web.domain.User;
import web.enumconstants.UserDetails;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by RossChalmers on 11/02/2017.
 */

public class PreferencesDAOImpl implements PreferencesDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall callAdminDetails;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.callAdminDetails = new SimpleJdbcCall(jdbcTemplate);
    }

    public List<Map<String, Object>> get(String storedProc) {
        List<Map<String, Object>> get = this.jdbcTemplate.queryForList(storedProc);
        return get;
    }

    public Map getRatings(String storedProc) {
        List<Map<String, Object>> ratings = this.jdbcTemplate.queryForList("SELECT ratingID, ratingName FROM rating");
        Map<Integer, String> toReturn = new HashMap<Integer,String>();
        for(int i = 0; i < ratings.size(); i++){
            toReturn.put((Integer)ratings.get(i).get("ratingId"), (String)ratings.get(i).get("ratingName"));
        }

        return toReturn;
    }

    public Map getLocations(String storedProc){
        List<Map<String, Object>> ratings = this.jdbcTemplate.queryForList("SELECT locationID, locationName FROM location");
        Map<Integer, String> toReturn = new HashMap<Integer,String>();
        for(int i = 0; i < ratings.size(); i++){
            toReturn.put((Integer)ratings.get(i).get("locationId"), (String)ratings.get(i).get("locationName"));
        }

        return toReturn;
    }

}
