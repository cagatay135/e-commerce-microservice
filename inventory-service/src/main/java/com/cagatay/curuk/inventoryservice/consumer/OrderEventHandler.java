package com.cagatay.curuk.inventoryservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
class OrderEventHandler {
    @KafkaListener(topics = "orders.public.orders", groupId = "order-service")
    public void handle(String message) {
        System.out.println("Received message: " + message);
    }
}