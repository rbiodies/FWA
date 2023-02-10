package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DataRepositoryJdbcTemplateImpl implements DataRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DataRepositoryJdbcTemplateImpl(@Qualifier("hikariBean") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Data entity) {
        jdbcTemplate.update("INSERT INTO data (user_id, date, time, ip) VALUES (?, ?, ?, ?)",
                entity.getUser().getId(), entity.getDate(), entity.getTime(), entity.getIp());
    }

    @Override
    public List<Data> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM data WHERE user_id = ?",
                new Object[]{id}, new DataRepositoryJdbcTemplateImpl.UserMapper());
    }

    @Override
    public List<Data> findAll() {
        return jdbcTemplate.query("SELECT * FROM data", new UserMapper());
    }

    public static class UserMapper implements RowMapper<Data> {

        @Override
        public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
            Data data = new Data();

            data.setId(rs.getLong("id"));
            data.setDate(rs.getString("date"));
            data.setTime(rs.getString("time"));
            data.setIp(rs.getString("ip"));

            return data;
        }
    }
}
