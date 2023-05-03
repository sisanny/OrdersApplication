package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.RestaurantDTO;
import com.homework.orderapplication.exception.CustomException;
import com.homework.orderapplication.model.Restaurant;
import com.homework.orderapplication.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;

    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new CustomException("Restaurant with id " + id + " not found", HttpStatus.NOT_FOUND));
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
    }
}
