package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.edu.ulab.app.storage.UserStorage.deleteUserFromStorage;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static long userId;
    final UserStorage userStorage;

    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }


    // todo сгенерировать идентификатор
    //  создать пользователя
    //  вернуть сохраненного пользователя со всеми необходимыми полями id

    @Override
    public UserDto createUser(UserDto userDto) {    //userDto without userId
        if (userDto.getId() == null) {
            //if (!storage.isUserPresent(userDto)) {
            userId++;
            userStorage.addUserToStorage(userDto, userId);
            userDto.setId(userId);
        }

        return userDto;     //userDto with userId
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        if (userStorage.isUserPresent(userId)) {
            userStorage.updateUserInStorage(userId, userDto);
        }
        throw new NotFoundException("Can not update user, user not found");
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userStorage.getUserFromStorage(userId);
    }

    @Override
    public void deleteUserById(Long id) {
        userStorage.deleteUserFromStorage(id);
    }
}
