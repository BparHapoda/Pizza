package com.example.pizza.services;

import com.example.pizza.dtos.OrderForm;
import com.example.pizza.models.Order;
import com.example.pizza.repositories.OrdersRepository;

public class OrderServiceImpl implements OrderService {
    private OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void order(OrderForm orderForm) {
        ordersRepository.save(
                Order.builder().
                        login(orderForm.getLogin()).
                        pizza(orderForm.getPizza()).
                        adress(orderForm.getAddress())
                        .build()
        );
    }
}
