package com.example.bai2.controller;

import com.example.bai2.model.dto.request.UserDTO;
import com.example.bai2.model.dto.response.ApiDataResponse;
import com.example.bai2.model.entity.User;
import com.example.bai2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiDataResponse<User>> createUser(@RequestBody UserDTO userDTO) {
        log.info("Creating user: {}", userDTO);
        return new ResponseEntity<>(new ApiDataResponse<>(true, "User created successfully", userService.createUser(userDTO), null, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
