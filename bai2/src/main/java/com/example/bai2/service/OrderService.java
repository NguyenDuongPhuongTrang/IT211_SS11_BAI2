package com.example.bai2.service;

import com.example.bai2.model.dto.request.OrderDTO;
import com.example.bai2.model.dto.request.PayOrderDTO;
import com.example.bai2.model.entity.Order;
import com.example.bai2.model.entity.User;
import com.example.bai2.model.entity.UserAccount;
import com.example.bai2.repository.OrderRepository;
import com.example.bai2.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {
    private final UserAccountService userAccountService;
    private OrderRepository orderRepository;
    private UserService userService;
    private UserAccountRepository userAccountRepository;

    public Order createOrder(OrderDTO orderDTO) {
        User user = userService.findById(orderDTO.getUserId());
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    public Order findOrderOfUser(Long orderId, Long userId) {
        return orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(() -> {
            log.error("Order not found");
            return new NoSuchElementException("Order not found");
        });
    }

    @Transactional
    public Order payOrder(PayOrderDTO payOrderDTO) {
        UserAccount userAccount = userAccountService.findByUserId(payOrderDTO.getUserId());
        Order order = findOrderOfUser(payOrderDTO.getOrderId(), payOrderDTO.getUserId());
        if (order.getStatus().equals("PAID")) {
            log.error("Order has been paid");
            throw new RuntimeException("Order already paid");
        }
        if (order.getTotalAmount() > payOrderDTO.getAmount()) {
            log.error("Not enough balance");
            throw new RuntimeException("Not enough balance");
        }

        if (payOrderDTO.getAmount().equals(9999.0)) {
            log.error("Invalid amount");
            throw new RuntimeException("Invalid amount");
        }
        order.setStatus("PAID");
        userAccount.setBalance(userAccount.getBalance() - payOrderDTO.getAmount());
        userAccountRepository.save(userAccount);
        return orderRepository.save(order);
    }

}
