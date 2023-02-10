package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Images;
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
public class ImagesRepositoryJdbcTemplateImpl implements ImagesRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ImagesRepositoryJdbcTemplateImpl(@Qualifier("hikariBean") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Images> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM images WHERE user_id = ?",
                new Object[]{id}, new ImagesRepositoryJdbcTemplateImpl.UserMapper());
    }

    @Override
    public List<Images> findAll() {
        return jdbcTemplate.query("SELECT * FROM images", new UserMapper());
    }

    @Override
    public void save(Images entity) {
        jdbcTemplate.update("INSERT INTO images (user_id, fileName, size, mime, uniqueName) VALUES (?, ?, ?, ?, ?)",
                entity.getUser().getId(), entity.getFileName(), entity.getSize(), entity.getMime(), entity.getUniqueName());
    }

    public static class UserMapper implements RowMapper<Images> {

        @Override
        public Images mapRow(ResultSet rs, int rowNum) throws SQLException {
            Images images = new Images();

            images.setId(rs.getLong("id"));
            images.setFileName(rs.getString("fileName"));
            images.setSize(rs.getString("size"));
            images.setMime(rs.getString("mime"));
            images.setUniqueName(rs.getString("uniqueName"));

            return images;
        }
    }
}
