package com.pixelpunch.vaultify.core.service;

import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.web.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface IUserService {
    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto createUserRequest);

    UserDto updateUser(UserDto userDTO);

    void deleteUser(Long userId);

    void deleteUnverifiedUsers(Date date);
}

