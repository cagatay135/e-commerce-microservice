package com.cagatay.curuk.orderservice.feign;

import com.cagatay.curuk.orderservice.exception.ErrorBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorBody message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ErrorBody.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        return switch (response.status()) {
            case 400 -> new BadRequestException(message.getErrorDescription());
            case 403 -> new ForbiddenException(message.getErrorDescription());
            case 404 -> new NotFoundException(message.getErrorDescription());
            case 500 -> new InternalServerErrorException(message.getErrorDescription());
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}