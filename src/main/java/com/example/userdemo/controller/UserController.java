package com.example.userdemo.controller;

import com.example.userdemo.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import com.example.userdemo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Integer id) {
        return userService.findUser(id);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return userService.saveUser(dto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Integer id, @RequestBody UserDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }
}
