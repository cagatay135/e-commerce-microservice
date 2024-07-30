package com.cagatay.curuk.inventoryservice.consumer;

import com.cagatay.curuk.inventoryservice.dto.messages.DebeziumOperationType;
import com.cagatay.curuk.inventoryservice.dto.messages.OrderItemDebeziumMessage;
import com.cagatay.curuk.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class OrderItemEventHandler {

    private static final Logger logger = Logger.getLogger(OrderItemEventHandler.class.getName());

    private final InventoryService inventoryService;

    @KafkaListener(topics = "orders.public.order_items", groupId = "inventory-service", containerFactory = "OrderItemKafkaListenerContainerFactory")
    public void processMessage(@Payload OrderItemDebeziumMessage message) {
        logger.info("Received message: " + message.getOperationCode());

        if (message.getOperationType().equals(DebeziumOperationType.CREATE)) {
            logger.info("Order created: " + message.getAfter());
        } else if (message.getOperationType().equals(DebeziumOperationType.UPDATE)) {
            logger.info("Order updated: " + message.getAfter());
        } else if (message.getOperationType().equals(DebeziumOperationType.DELETE)) {
            logger.info("Order deleted: " + message.getBefore());
        }
    }
}