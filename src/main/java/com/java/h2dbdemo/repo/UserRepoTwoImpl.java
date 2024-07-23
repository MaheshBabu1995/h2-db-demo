package com.java.h2dbdemo.repo;

import java.util.Objects;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.h2dbdemo.model.User;

@Repository
public class UserRepoTwoImpl implements UserRepoTwo {
    private final JdbcTemplate jdbcTemplate;

    public UserRepoTwoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
        public int saveAndFlush(User user) {
            System.out.println("user: " + user);
            return jdbcTemplate.update("INSERT INTO user_data ( sno ,name,city) VALUES(?,?,?)",
                    new Object[] { user.getSno(), user.getName(), user.getCity() });
        }

    @Override
    public User findByUserByName(String name) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM user_data WHERE name=?",
                    BeanPropertyRowMapper.newInstance(User.class), name);

            return user;

        } catch (IncorrectResultSizeDataAccessException e) {
            throw new RuntimeException("Could not find user");
        }
    }
}
