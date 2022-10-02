//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.edu.ulab.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImplTemplate implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImplTemplate.class);
    private final JdbcTemplate jdbcTemplate;
    UserServiceImplTemplate userServiceImplTemplate;
    BookMapper bookMapper;

    public BookServiceImplTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookDto createBook(BookDto bookDto) {
        String INSERT_SQL = "INSERT INTO BOOK(TITLE, AUTHOR, PAGE_COUNT, USER_ID) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                ps.setString(1, bookDto.getTitle());
                ps.setString(2, bookDto.getAuthor());
                ps.setLong(3, bookDto.getPageCount());
                ps.setLong(4, bookDto.getUserId());
                return ps;
            }
        }, keyHolder);
        bookDto.setId((Objects.requireNonNull(keyHolder.getKey())).longValue());
        return bookDto;
    }

    public BookDto updateBook(BookDto bookDto) {

        final String UPDATE_SQL = "UPDATE BOOK SET USER_ID=?, TITLE=?, AUTHOR=?, PAGE_COUNT=?  WHERE USER_ID = ?";
        jdbcTemplate.update(UPDATE_SQL, bookDto.getUserId(), bookDto.getTitle(),
                bookDto.getAuthor(), bookDto.getPageCount(), bookDto.getUserId());

        return getBookById(bookDto.getUserId());
    }

    public BookDto getBookById(Long userId) {
        final String GET_SQL = "SELECT * FROM BOOK WHERE USER_ID = ?";
        return bookMapper.bookEntityToBookDto(jdbcTemplate.queryForObject(GET_SQL,
                new Object[]{userId}, new BeanPropertyRowMapper<>(BookEntity.class)));
    }

    public void deleteBookById(Long id) {
        String DELETE_SQL = "DELETE FROM BOOK WHERE USER_ID=?";
        jdbcTemplate.update(DELETE_SQL, new Object[]{id});
    }

    public List<Long> getBooksListById(Long id) {

        final String GET_BOOKS_SQL = "SELECT ALL ID FROM BOOK WHERE USER_ID=?";

        List<Long> ids =  jdbcTemplate.queryForList(GET_BOOKS_SQL, Long.class, id);

        log.info("Books ID's list for user {} : {}", id, ids);

        return ids;
    }
}

