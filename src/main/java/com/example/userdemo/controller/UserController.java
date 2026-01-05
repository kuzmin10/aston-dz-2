package com.example.userdemo.controller;

import com.example.userdemo.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.example.userdemo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Пользователи", description = "Управление учетными записями пользователей")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить список всех пользователей")
    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAllUsers();
    }

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Integer id) {
        return userService.findUser(id);
    }

    @Operation(
            summary = "Создать нового пользователя",
            description = "Сохраняет пользователя в базу и отправляет уведомление в Kafka"
    )
    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return userService.saveUser(dto);
    }

    @Operation(summary = "Обновить данные пользователя по ID")
    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Integer id, @RequestBody UserDto dto) {
        return userService.updateUser(id, dto);
    }

    @Operation(
            summary = "Удалить пользователя по ID",
            description = "Удаляет пользователя из базы и отправляет уведомление в Kafka"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }
}
