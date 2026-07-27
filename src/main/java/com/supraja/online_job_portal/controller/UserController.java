package com.supraja.online_job_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.supraja.online_job_portal.dto.UserDto;
import com.supraja.online_job_portal.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    // Create User
    @PostMapping
    public UserDto createUser(
            @Valid @RequestBody UserDto userDto) {

        return userService.registerUser(userDto);
    }

    // Get All Users
    @GetMapping
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }


    // Get User By Id
    @GetMapping("/{id}")
    public UserDto getUserById(
            @PathVariable("id") Long id) {

        return userService.getUserById(id);
    }


    // Update User
    @PutMapping("/{id}")
    public UserDto updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserDto userDto) {

        return userService.updateUser(id, userDto);
    }


    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable("id") Long id) {

        userService.deleteUser(id);

        return "User deleted successfully.";
    }
}