package com.example.bai2.controller;

import com.example.bai2.model.dto.request.OrderDTO;
import com.example.bai2.model.dto.request.PayOrderDTO;
import com.example.bai2.model.dto.response.ApiDataResponse;
import com.example.bai2.model.entity.Order;
import com.example.bai2.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<ApiDataResponse<Order>> createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("Creating order: {}", orderDTO);
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Order created successfully", orderService.createOrder(orderDTO), null, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping("/payment/pay")
    public ResponseEntity<ApiDataResponse<Order>> payOrder(@RequestBody PayOrderDTO payOrderDTO) {
        log.info("Paying order: {}", payOrderDTO);
        Order order = orderService.payOrder(payOrderDTO);
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Order paid successfully", order, null, HttpStatus.OK), HttpStatus.OK);
    }
}
