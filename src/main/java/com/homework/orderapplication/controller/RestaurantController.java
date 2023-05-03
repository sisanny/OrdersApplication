package com.homework.orderapplication.controller;

import com.homework.orderapplication.dto.ItemDTO;
import com.homework.orderapplication.dto.RestaurantDTO;
import com.homework.orderapplication.requestBody.OrderRequestBody;
import com.homework.orderapplication.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable("id") Long id) {
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping(path = "/{id}/menu")
    public ResponseEntity<List<ItemDTO>> getMenuOfRestaurantById(@PathVariable("id") Long id) {
        List<ItemDTO> menu = restaurantService.getRestaurantById(id).getMenu();
        return ResponseEntity.ok(menu);
    }
}
