//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import java.sql.PreparedStatement;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplTemplate implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImplTemplate.class);
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserServiceImplTemplate(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    public UserDto createUser(UserDto userDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PERSON(FULL_NAME, TITLE, AGE) VALUES (?,?,?)", new String[]{"id"});
            ps.setString(1, userDto.getFullName());
            ps.setString(2, userDto.getTitle());
            ps.setLong(3, (long)userDto.getAge());
            return ps;
        }, keyHolder);
        userDto.setId(((Number)Objects.requireNonNull(keyHolder.getKey())).longValue());
        log.info("UserServiceImplTemplate: {}", userDto);
        return userDto;
    }

    public UserDto updateUser(UserDto userDto, Long userId) {
        this.jdbcTemplate.update("UPDATE PERSON SET FULL_NAME=?, TITLE=?, AGE=? WHERE id=?", new Object[]{userDto.getFullName(), userDto.getTitle(), userDto.getAge(), userId});
        return this.getUserById(userId);
    }

    public UserDto getUserById(Long id) {
        return this.userMapper.userEntityToUserDto((UserEntity)this.jdbcTemplate.queryForObject("SELECT * FROM PERSON WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper()));
    }

    public void deleteUserById(Long id) {
        this.jdbcTemplate.update("DELETE FROM PERSON WHERE id=?", new Object[]{id});
    }
}
