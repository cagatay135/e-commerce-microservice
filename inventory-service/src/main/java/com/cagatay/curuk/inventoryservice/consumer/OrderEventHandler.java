package com.cagatay.curuk.inventoryservice.consumer;

import com.cagatay.curuk.inventoryservice.dto.messages.OrderDebeziumMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
class OrderEventHandler {
    @KafkaListener(topics = "orders.public.orders", groupId = "order-service", containerFactory = "OrderKafkaListenerContainerFactory")
    public void handle(@Payload OrderDebeziumMessage message) {
        System.out.println("Received message: " + message);
    }
}