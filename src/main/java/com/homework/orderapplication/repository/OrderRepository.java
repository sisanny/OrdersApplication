package com.homework.orderapplication.repository;

import com.homework.orderapplication.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findById(Long id);
    List<Orders> findAll();
}

