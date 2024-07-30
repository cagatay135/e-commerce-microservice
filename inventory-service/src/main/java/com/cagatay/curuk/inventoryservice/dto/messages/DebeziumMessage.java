package com.cagatay.curuk.inventoryservice.dto.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebeziumMessage<T> extends KafkaErrorMessage {

    private T before;
    private T after;

    @JsonProperty("op")
    private String operationCode;

    @JsonProperty("ts_ms")
    private Long timestamp;

    @Override
    public String toString() {
        String sb = "DebeziumMessage{" + "before=" + before +
                ", after=" + after +
                ", operation='" + operationCode + '\'' +
                '}';
        return sb;
    }

    @JsonIgnore
    public DebeziumOperationType getOperationType() {
        return DebeziumOperationType.getOperationTypeByCode(this.operationCode);
    }
}
