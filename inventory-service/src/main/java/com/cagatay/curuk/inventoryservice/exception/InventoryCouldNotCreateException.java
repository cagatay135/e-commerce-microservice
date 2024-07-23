package com.cagatay.curuk.inventoryservice.exception;

public class InventoryCouldNotCreateException extends RuntimeException {
    public InventoryCouldNotCreateException(String message) {
        super(message);
    }

    public InventoryCouldNotCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
