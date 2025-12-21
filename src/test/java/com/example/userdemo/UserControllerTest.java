package com.example.userdemo;

import com.example.userdemo.dto.UserDto;
import com.example.userdemo.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crudTest() throws Exception {
        User user = new User("Test User", 1, "test@example.com");
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge(), user.getCreated_at());

        String response = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andExpect(jsonPath("$.created_at").exists())
                .andReturn().getResponse().getContentAsString();

        UserDto createdUser = objectMapper.readValue(response, UserDto.class);
        Integer userId = createdUser.getId();

        mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andExpect(jsonPath("$.created_at").exists());


        User updateUser = new User("Updated Name", 2, "updated@mail.com");
        UserDto updatedDto = new UserDto(updateUser.getId(), updateUser.getName(), updateUser.getEmail(), updateUser.getAge(), updateUser.getCreated_at());

        mockMvc.perform(put("/api/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updateUser.getName()))
                .andExpect(jsonPath("$.email").value(updateUser.getEmail()))
                .andExpect(jsonPath("$.age").value(updateUser.getAge()))
                .andExpect(jsonPath("$.created_at").exists());

        mockMvc.perform(delete("/api/users/" + userId))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
