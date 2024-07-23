package com.cagatay.curuk.orderservice.exception;

public class OrderCouldNotCreateException extends RuntimeException{
    public OrderCouldNotCreateException(String message) {
        super(message);
    }

    public OrderCouldNotCreateException() {
        super("Order could not be created.");
    }
}