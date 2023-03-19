package com.example.pizza.repositories;

import com.example.pizza.models.Order;
import com.example.pizza.models.User;

import java.util.Optional;

public interface OrdersRepository {
    void save (Order order);
    Optional<Order> findByLogin(String login);

}
