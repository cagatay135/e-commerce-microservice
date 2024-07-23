package com.cagatay.curuk.orderservice.exception;

import java.util.UUID;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(UUID productId) {
        super("Stock is not sufficient for product: " + productId);
    }
}

