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

        switch (message.getOperationType()) {
            case DebeziumOperationType.CREATE:
                inventoryService.updateStock(message.getAfter().getProductId(), -message.getAfter().getQuantity());
                break;
            case DebeziumOperationType.UPDATE:
                inventoryService.updateStock(message.getAfter().getProductId(), -(message.getAfter().getQuantity() - message.getBefore().getQuantity()));
                break;
            case DebeziumOperationType.DELETE:
                inventoryService.updateStock(message.getBefore().getProductId(), message.getBefore().getQuantity());
                break;
            default:
                logger.warning("Unknown operation code: " + message.getOperationCode());
        }
    }
}