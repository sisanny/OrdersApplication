package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.exception.CustomException;
import com.homework.orderapplication.model.OrderItem;
import com.homework.orderapplication.model.Status;
import com.homework.orderapplication.repository.OrderItemRepository;
import com.homework.orderapplication.requestBody.OrderRequestBody;
import com.homework.orderapplication.model.Orders;
import com.homework.orderapplication.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class OrderService {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void addOrder(OrderRequestBody orderRequestBody) {
        Orders orders = mapOrders(orderRequestBody);
        Orders savedOrders = orderRepository.save(orders);

        List<OrderItemDTO> orderItemDTOS = orderRequestBody.getOrderItems();
        for (OrderItemDTO orderItemDTO : orderItemDTOS) {
            OrderItem orderItem = OrderItem.builder()
                    .orderId(savedOrders)
                    .itemId(orderItemDTO.getItemId())
                    .quantity(orderItemDTO.getQuantity())
                    .specialInstructions(orderItemDTO.getSpecialInstructions())
                    .build();
            savedOrders.getItems().add(orderItem);
            orderItemRepository.save(orderItem);
        }
        orderRepository.save(savedOrders);
    }

    private Orders mapOrders(OrderRequestBody orderRequestBody) {
        return Orders.builder()
                .customer(orderRequestBody.getCustomer())
                .restaurant(orderRequestBody.getRestaurant())
                .status(Status.RECEIVED)
                .items(new ArrayList<>())
                .build();
    }


    public OrderDTO getOrderById(Long id) {
        Orders order = getOrders(id);
        return modelMapper.map(order, OrderDTO.class);
    }

    public Map<String, List<OrderDTO>> getOrdersByRestaurants() {
        List<OrderDTO> ordersList = getAllOrders();
        Map<String, List<OrderDTO>> ordersByRestaurant = new HashMap<>();
        for (OrderDTO orderDTO : ordersList) {
            String restaurantName = getRestaurantName(orderDTO.getRestaurant());
            List<OrderDTO> restaurantOrders = ordersByRestaurant.getOrDefault(restaurantName, new ArrayList<>());
            restaurantOrders.add(orderDTO);
            ordersByRestaurant.put(restaurantName, restaurantOrders);
        }
        return ordersByRestaurant;
    }

    private String getRestaurantName(Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId).getName();
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public void updateStatus(Long id, String status) {
        Orders order = getOrders(id);
        order.setStatus(Status.valueOf(status));
        orderRepository.save(order);
    }

    private Orders getOrders(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new CustomException("Restaurant with id " + id + " not found", HttpStatus.NOT_FOUND));
    }

}
