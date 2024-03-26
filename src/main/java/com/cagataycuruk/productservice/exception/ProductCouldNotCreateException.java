package com.cagataycuruk.productservice.exception;

public class ProductCouldNotCreateException extends RuntimeException{
    public ProductCouldNotCreateException(String message) {
        super(message);
    }
}