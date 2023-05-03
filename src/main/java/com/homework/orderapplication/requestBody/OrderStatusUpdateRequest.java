package com.homework.orderapplication.requestBody;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public
class OrderStatusUpdateRequest {
    @NotBlank(message = "The status cannot be empty")
    private String status;
}
