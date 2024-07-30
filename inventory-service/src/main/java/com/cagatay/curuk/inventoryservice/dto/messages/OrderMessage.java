package com.cagatay.curuk.inventoryservice.dto.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class OrderMessage {
    private UUID id;

    private String status;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderMessage{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
}

