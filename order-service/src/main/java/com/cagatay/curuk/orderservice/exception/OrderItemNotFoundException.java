package com.cagatay.curuk.orderservice.exception;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(String message) {
        super(message);
    }

    public OrderItemNotFoundException() {
        super("Order item not found.");
    }
}