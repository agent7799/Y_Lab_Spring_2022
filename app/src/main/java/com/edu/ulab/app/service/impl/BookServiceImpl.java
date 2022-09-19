package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.BookStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private static long bookId;
    final
    BookStorage bookStorage;

    public BookServiceImpl(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {       //bookDto with userId, without id
        if (bookDto.getId() == null) {
            bookId++;
            bookStorage.addBookToStorage(bookId, bookDto);
            bookDto.setId(bookId);
        }
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {     // userId is present in bookDto
        bookDto.getUserId();


        bookId++;
        bookStorage.updateBookInStorage(bookId, bookDto);

        bookDto.setId(bookId);
        return bookDto;
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookStorage.getBookFromStorageByBookId(id);
    }

    @Override
    public void deleteBookById(Long id) {
        bookStorage.deleteBookFromStorage(id);
    }


}
