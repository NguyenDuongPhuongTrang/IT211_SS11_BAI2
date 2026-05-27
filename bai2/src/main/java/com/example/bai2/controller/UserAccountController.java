package com.example.bai2.controller;

import com.example.bai2.model.dto.request.UserAccountDTO;
import com.example.bai2.model.dto.request.UserDTO;
import com.example.bai2.model.dto.response.ApiDataResponse;
import com.example.bai2.model.entity.UserAccount;
import com.example.bai2.service.UserAccountService;
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
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<UserAccount>> createUser(@RequestBody UserAccountDTO userAccountDTO) {
        log.info("Creating user account: {}", userAccountDTO);
        return new ResponseEntity<>(new ApiDataResponse<>(true, "User account created successfully", userAccountService.createUserAccount(userAccountDTO), null, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
