package com.cagatay.curuk.orderservice.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorBody> catchBasketNotFoundExceptions(OrderNotFoundException orderNotFoundException) {
        return ResponseEntity.ok(ErrorBody.builder().errorCode(404).errorDescription(orderNotFoundException.getMessage()).build());
    }

    @ApiResponse(responseCode = "400", description = "Bad Request Error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorBody.class)))
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBody catchBadRequestException(BadRequestException exception) {
        log.error("Bad Request Exception {}", exception.toString());
        return ErrorBody.builder().errorCode(400).errorDescription(exception.getMessage()).build();
    }

    @ApiResponse(responseCode = "403", description = "Forbidden Error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorBody.class)))
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorBody catchForbiddenException(ForbiddenException exception) {
        log.error("Forbidden Exception {}", exception.toString());
        return ErrorBody.builder().errorCode(403).errorDescription(exception.getMessage()).build();
    }

    @ApiResponse(responseCode = "404", description = "Not Found Error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorBody.class)))
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorBody catchNotFoundException(NotFoundException exception) {
        log.error("Not Found Exception {}", exception.toString());
        return ErrorBody.builder().errorCode(404).errorDescription(exception.getMessage()).build();
    }

    @ApiResponse(responseCode = "500", description = "Internal Error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorBody.class)))
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorBody catchInternalServerException(InternalServerErrorException exception) {
        log.error("Internal Server Exception {}", exception.toString());
        return ErrorBody.builder().errorCode(500).errorDescription(exception.getMessage()).build();
    }


}