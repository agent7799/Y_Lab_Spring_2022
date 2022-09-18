package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;

import java.util.*;
import java.util.stream.Collectors;

public class Storage {
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции


    private static Storage instance;

    private Storage(){}
    public static Storage getInstance(){
        if (instance == null){
            instance =  new Storage();
        }
        return instance;
    }
    private static Map<Long, UserDto> usersList = new HashMap<>();
    private static Map<Long, BookDto> booksList = new HashMap<>();


    public static void addUserToStorage(UserDto userDto){
        if(!usersList.containsValue(userDto)){
            usersList.put(userDto.getId(), userDto);
        }
    }

    public static UserDto getUserFromStorage(Long id){
        return usersList.get(id);
    }

    public static void deleteUserFromStorage(Long id){
        usersList.remove(id);
    }

    public static void replaceUserInStorage(long id, UserDto userDto){
        usersList.replace(id, userDto);
    }


    public static List<Long> getBooksFromStorageByUserID(Long requestedId){
        List<Long> foundBooksList = booksList.values().stream()
                .filter(Objects::nonNull)
                .map(BookDto::getUserId)
                .filter(id -> id.equals(requestedId))
               .toList();
        return foundBooksList;
    }

    public static BookDto getBookFromStorageByBookId(Long bookId){
        return booksList.get(bookId);
    }

    public static void addBookToStorage(BookDto bookDto){
        if(!booksList.containsValue(bookDto)){
            booksList.put(bookDto.getId(), bookDto);
        }

    }


}
