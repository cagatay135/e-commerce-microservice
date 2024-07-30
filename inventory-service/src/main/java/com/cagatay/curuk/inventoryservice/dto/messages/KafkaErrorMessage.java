package com.cagatay.curuk.inventoryservice.dto.messages;

import com.fasterxml.jackson.annotation.JsonInclude;

public class KafkaErrorMessage {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String errorMessage;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KafkaErrorMessage{");
        sb.append("errorMessage='").append(errorMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}