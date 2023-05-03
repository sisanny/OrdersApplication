package com.homework.orderapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class OrderItemDTO {
    private Long orderId;
    private Long itemId;
    private int quantity;
    private String specialInstructions;
}