//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.service.BookService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImplTemplate implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImplTemplate.class);
    private final JdbcTemplate jdbcTemplate;

    public BookServiceImplTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookDto createBook(BookDto bookDto) {
        String INSERT_SQL = "INSERT INTO BOOK(TITLE, AUTHOR, PAGE_COUNT, USER_ID) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO BOOK(TITLE, AUTHOR, PAGE_COUNT, USER_ID) VALUES (?,?,?,?)", new String[]{"id"});
                ps.setString(1, bookDto.getTitle());
                ps.setString(2, bookDto.getAuthor());
                ps.setLong(3, bookDto.getPageCount());
                ps.setLong(4, bookDto.getUserId());
                return ps;
            }
        }, keyHolder);
        bookDto.setId(((Number)Objects.requireNonNull(keyHolder.getKey())).longValue());
        return bookDto;
    }

    public BookDto updateBook(BookDto bookDto) {
        return null;
    }

    public BookDto getBookById(Long id) {
        return null;
    }

    public void deleteBookById(Long id) {
    }

    public List<Long> getBooksListById(Long id) {
        return null;
    }
}

