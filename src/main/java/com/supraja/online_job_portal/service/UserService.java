package com.supraja.online_job_portal.service;

import java.util.List;

import com.supraja.online_job_portal.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);
}