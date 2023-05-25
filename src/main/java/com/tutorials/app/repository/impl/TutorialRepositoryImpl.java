package com.tutorials.app.repository.impl;

import com.tutorials.app.model.Tutorial;
import com.tutorials.app.repository.TutorialRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TutorialRepositoryImpl implements TutorialRepository {
    private final JdbcTemplate jdbcTemplate;

    public TutorialRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Tutorial book) {
        return jdbcTemplate.update("INSERT  into tutorial(id,title,description,published) values (?,?,?,?)",new Object[]{book.getId(),book.getTitle(),book.getDescription(),book.isPublished()});
    }

    @Override
    public int update(Tutorial book,Long id) {
        return jdbcTemplate.update("update tutorial set title=?,description=?,published=? where id=?",new Object[]{book.getTitle(),book.getDescription(),book.isPublished(),id});
    }

    @Override
    public Tutorial findById(Long id) {
        Tutorial tutorial=jdbcTemplate.queryForObject("Select * from tutorial where id=?", BeanPropertyRowMapper.newInstance(Tutorial.class),id);
        return tutorial;
    }
    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from tutorial where id=?",id);
    }
    @Override
    public List<Tutorial> findAll() {
        return jdbcTemplate.query("Select * from tutorial",BeanPropertyRowMapper.newInstance(Tutorial.class));
    }
    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return jdbcTemplate.query("Select * from tutorial where published=?",BeanPropertyRowMapper.newInstance(Tutorial.class),published);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("truncate  tutorial");
    }
}
