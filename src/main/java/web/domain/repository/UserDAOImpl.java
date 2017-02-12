package web.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.domain.User;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * Created by RossChalmers on 11/02/2017.
 */


public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(User user) {
        this.jdbcTemplate.update(
                "insert into users (username, userpassword) values (?, ?)",
                user.getUsername(), user.getPassword());
    }

    public int getUserID(User user) {
        int rowCount = this.jdbcTemplate.queryForObject("select userID from users where username = ?",
                new Object[] { user.getUsername() }, Integer.class);

        return rowCount;
    }

    public void update(User user) {


    }

    public void delete(User user) {

    }

    public void getUsername(User user){
        String username = this.jdbcTemplate.queryForObject("select username from users where username = ?",
                new Object[] { user.getUsername() }, String.class);
    }
}
