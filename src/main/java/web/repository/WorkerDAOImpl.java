package web.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public class WorkerDAOImpl implements WorkerDAO {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall insertFreelancer;
    private SimpleJdbcCall insertEmployer;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertFreelancer = new SimpleJdbcCall(jdbcTemplate);
        this.insertEmployer = new SimpleJdbcCall(jdbcTemplate);
    }

    public Map<String, Object> insertFreelancer(String storedProc, Map<String, Object> inParameters){
        insertFreelancer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertFreelancer.execute(in);
        return out;
    }

    public Map<String, Object> insertEmployer(String storedProc, Map<String, Object> inParameters){
        insertEmployer.withProcedureName(storedProc);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(inParameters);
        Map<String, Object> out = insertEmployer.execute(in);
        return out;
    }
}
