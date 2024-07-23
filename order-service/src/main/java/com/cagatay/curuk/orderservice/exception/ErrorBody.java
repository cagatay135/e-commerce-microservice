package com.cagatay.curuk.orderservice.exception;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorBody {
    private Integer errorCode;
    private String errorDescription;
}