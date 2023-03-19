package com.example.pizza.repositories;

import com.example.pizza.models.Order;

import java.util.Optional;

public class OrderRepositoryImpl implements OrdersRepository{
    @Override
    public void save(Order order) {

    }

    @Override
    public Optional<Order> findByLogin(String login) {
        return Optional.empty();
    }
}
