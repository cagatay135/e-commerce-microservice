package com.cagatay.curuk.inventoryservice.dto.messages;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DebeziumOperationType {

    READ("r"),
    CREATE("c"),
    UPDATE("u"),
    DELETE("d");

    private final String code;

    DebeziumOperationType(String code) {
        this.code = code;
    }

    public static DebeziumOperationType getOperationTypeByCode(String code) {
        return Arrays.stream(values())
                .filter(operation -> operation.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(DebeziumOperationType.class, code));
    }
}