package com.homework.orderapplication.controller;

import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.requestBody.OrderRequestBody;
import com.homework.orderapplication.requestBody.OrderStatusUpdateRequest;
import com.homework.orderapplication.service.OrderService;
import com.homework.orderapplication.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderRequestBody orderRequestBody) {
        orderService.addOrder(orderRequestBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<Map<String, List<OrderDTO>>> getAllRestaurants() {
        Map<String, List<OrderDTO>> orders = orderService.getOrdersByRestaurants();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDTO> getRestaurantById(@PathVariable("id") Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("id") Long id, @Valid @RequestBody OrderStatusUpdateRequest orderStatusUpdateRequest) {
        orderService.updateStatus(id, orderStatusUpdateRequest.getStatus());
        return ResponseEntity.ok().build();
    }
}
