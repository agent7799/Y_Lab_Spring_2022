package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        BookEntity bookEntity = bookMapper.bookDtoToBookEntity(bookDto);
        log.info("Mapped bookEntity: {}", bookEntity);
        BookEntity savedBook = bookRepository.save(bookEntity);
        log.info("Saved bookEntity: {}", savedBook);
        return bookMapper.bookEntityToBookDto(savedBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        BookEntity bookEntity = bookMapper.bookDtoToBookEntity(bookDto);
        bookRepository.save(bookEntity);
        log.info("Book {} with id: {} updated in storage.", bookDto);
        return bookMapper.bookEntityToBookDto(bookEntity);

    }

    @Override
    public BookDto getBookById(Long userId) {
        Optional<BookEntity> bookEntity = bookRepository.findById(userId);
        return bookMapper.bookEntityToBookDto(bookEntity.orElseThrow());
    }

    @Override
    public void deleteBookById(Long userId) {
        log.info("Books with userId: {} to be deleted", bookRepository.findById(userId));
        for (BookEntity b : bookRepository.findAll()) {
            if (b.getUserId().equals(userId)){
                bookRepository.delete(b);
                log.info("Book {} of user {} deleted", b, b.getUserId());
            }
        }
    }

    @Override
    public  List<Long> getBooksListById(Long userId) {
        List<Long> ids = new ArrayList<>();
        for (BookEntity book : bookRepository.findAll()) {
            if(book.getUserId().equals(userId)){
                ids.add(book.getId());
            }
        }
        log.info("Books ID's list for user {} : {}", userId, ids);
        return ids;
    }


}
