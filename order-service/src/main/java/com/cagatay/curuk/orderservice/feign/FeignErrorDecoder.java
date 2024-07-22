package com.cagatay.curuk.orderservice.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        switch (status) {
            case NOT_FOUND:
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
            case BAD_REQUEST:
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
            case INTERNAL_SERVER_ERROR:
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            default:
                return new Exception("Generic error");
        }
    }
}