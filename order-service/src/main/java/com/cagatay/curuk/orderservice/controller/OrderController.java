package com.cagatay.curuk.orderservice.controller;

import com.cagatay.curuk.orderservice.dto.OrderResponseDto;
import com.cagatay.curuk.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.cagatay.curuk.orderservice.constants.OrderConstants.*;

@Slf4j
@RestController
@RequestMapping(API_PREFIX + API_ORDER)
@RequiredArgsConstructor
@Tag(name = "Order Controller")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID orderId) {
        log.info("Fetching order with ID: {}", orderId);
        OrderResponseDto orderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponse);
    }

    @PostMapping()
    public ResponseEntity<OrderResponseDto> createOrder() {
        log.info("Creating a new order");
        OrderResponseDto createdOrder = orderService.createOrder();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId) {
        log.info("Cancelling order with ID: {}", orderId);
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
