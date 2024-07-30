package com.cagatay.curuk.inventoryservice.consumer;

import com.cagatay.curuk.inventoryservice.dto.DebeziumOperationType;
import com.cagatay.curuk.inventoryservice.dto.OrderDebeziumMessage;
import com.cagatay.curuk.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class OrderItemsEventHandler {

    private static final Logger logger = Logger.getLogger(OrderItemsEventHandler.class.getName());

    private final InventoryService inventoryService;

    @KafkaListener(topics = "orders.public.order_items", groupId = "inventory-service")
    public void processMessage(@Payload OrderDebeziumMessage message) {
        logger.info("Received message: " + message.getOperationCode());

        // write inventory handling logic here

        if (message.getOperationType().equals(DebeziumOperationType.CREATE)) {
            logger.info("Order created: " + message.getAfter());







            // write inventory handling logic here

        } else if (message.getOperationType().equals(DebeziumOperationType.UPDATE)) {
            logger.info("Order updated: " + message.getAfter());
        } else if (message.getOperationType().equals(DebeziumOperationType.DELETE)) {
            logger.info("Order deleted: " + message.getBefore());
        }


    }
}