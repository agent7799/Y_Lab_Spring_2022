package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.edu.ulab.app.storage.Storage.getBookFromStorageByBookId;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private static long bookId;

    @Override
    public BookDto createBook(BookDto bookDto) {
        if(bookDto.getId() == null){
            bookDto.setId(bookId++);
        }
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {

        return bookDto;
    }

    @Override
    public BookDto getBookById(Long id) {
        return getBookFromStorageByBookId(id);
    }

    @Override
    public void deleteBookById(Long id) {

    }
}
