package com.example.userdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;

@Schema(description = "Объект передачи данных пользователя")
public class UserDto {
    @Schema(description = "Уникальный идентификатор", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Имя пользователя", example = "Иван Иванов")
    private String name;

    @Schema(description = "Электронная почта пользователя", example = "ivan@gmail.com")
    private String email;

    @Schema(description = "Возраст пользователя", example = "25")
    private int age;

    @Schema(
            description = "Дата и время создания пользователя",
            example = "2026-01-05T14:30:00.000+00:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Timestamp created_at;

    public UserDto() {}

    public UserDto(int id, String name, String email, int age, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
