package com.homework.orderapplication.dto;

import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long customer;
    private Long restaurant;
    private Status status;
    private List<OrderItemDTO> items;
}
