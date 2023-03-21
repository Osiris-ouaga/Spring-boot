package com.example.order.controllers;

import com.example.order.domain.OrderItem;
import com.example.order.domain.Orderr;
import com.example.order.repositories.OrderItemRepository;
import com.example.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @PostMapping(value = "/order")
    public ResponseEntity<Orderr> createNewOrder(@RequestBody Orderr orderData)
    {
        Orderr order = orderRepository.save(orderData);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new order");

        return new ResponseEntity<Orderr>(order, HttpStatus.CREATED);
    }

    @GetMapping(value = "/orders")
    public List<Orderr> getOrderList()
    {
        return orderRepository.findAll();
    }

    @GetMapping(value = "/order/{id}")
    public Optional<Orderr> getOrder(@PathVariable Long id)
    {
        Optional<Orderr> order = orderRepository.findById(id);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get order");

        return order;
    }



}
