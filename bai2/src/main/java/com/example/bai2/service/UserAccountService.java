package com.example.bai2.service;

import com.example.bai2.model.dto.request.UserAccountDTO;
import com.example.bai2.model.entity.User;
import com.example.bai2.model.entity.UserAccount;
import com.example.bai2.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class UserAccountService {
    private UserService userService;
    private UserAccountRepository userAccountRepository;

    public UserAccount createUserAccount(UserAccountDTO userAccountDTO) {
        User user = userService.findById(userAccountDTO.getUserId());
        UserAccount userAccount = UserAccount.builder()
                .user(user)
                .balance(userAccountDTO.getBalance())
                .build();
        return userAccountRepository.save(userAccount);
    }

    public UserAccount findByUserId(Long userId) {
        return userAccountRepository.findByUserId(userId).orElseThrow(() -> {
            log.error("User account not found");
            return new NoSuchElementException("User account not found");
        });
    }
}
