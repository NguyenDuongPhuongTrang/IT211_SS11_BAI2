package com.example.bai2.service;

import com.example.bai2.model.dto.request.UserDTO;
import com.example.bai2.model.entity.User;
import com.example.bai2.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .build();
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("User not found");
            return new NoSuchElementException("User not found");
        });
    }
}
