package com.cagatay.curuk.inventoryservice.dto.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemMessage {
    private UUID id;

    @JsonProperty("product_id")
    private UUID productId;

    private Integer quantity;

    @JsonProperty("order_id")
    private UUID orderId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItemMessage{");
        sb.append("id='").append(id).append('\'');
        sb.append(", productId='").append(productId).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", orderId='").append(orderId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}