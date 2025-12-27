package com.example.userdemo.services;

import com.example.userdemo.dto.UserDto;
import com.example.userdemo.models.User;
import com.example.userdemo.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "user-notifications";

    public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public UserDto findUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    public UserDto saveUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        User savedUser = userRepository.save(user);

        kafkaTemplate.send(TOPIC, "CREATE;" + savedUser.getEmail());

        return convertToDto(savedUser);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        String email = user.getEmail();
        userRepository.deleteById(id);

        kafkaTemplate.send(TOPIC, "DELETE;" + email);
    }

    public UserDto updateUser(Integer id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return convertToDto(userRepository.save(user));
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge(), user.getCreated_at());
    }
}
