package com.cagatay.curuk.orderservice.controller;

import com.cagatay.curuk.orderservice.dto.OrderItemRequestDto;
import com.cagatay.curuk.orderservice.dto.OrderItemResponseDto;
import com.cagatay.curuk.orderservice.dto.OrderItemUpdateDto;
import com.cagatay.curuk.orderservice.dto.OrderResponseDto;
import com.cagatay.curuk.orderservice.service.OrderService;
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
@RequestMapping(API_PREFIX + API_ORDER_ITEM)
@RequiredArgsConstructor
@Tag(name = "Order Item Controller")
public class OrderItemController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderItemResponseDto> addOrderItem(@RequestBody OrderItemRequestDto orderItemRequestDto
    ) {
        OrderItemResponseDto orderItemResponse = orderService.addOrderItemToOrder(orderItemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemResponse);
    }

    @PutMapping()
    public ResponseEntity<OrderResponseDto> updateOrderItemQuantity(@RequestBody OrderItemUpdateDto orderItemUpdateDto
    ) {
        OrderResponseDto updatedOrder = orderService.updateOrderItemQuantity(orderItemUpdateDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteAllOrderItems(@PathVariable UUID orderId) {
        orderService.deleteAllOrderItemsInOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<OrderResponseDto> removeOrderItem(@PathVariable UUID orderItemId) {
        OrderResponseDto updatedOrder = orderService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok(updatedOrder);
    }
}
