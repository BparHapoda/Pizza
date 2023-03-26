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
                Order.builder()
                        .customerId(orderForm.getClientId())
                        .login(orderForm.getLogin())
                        .item(orderForm.getPizza())
                        .receipt(orderForm.getReceipt())
                        .toppings(orderForm.getToppings())
                        .name(orderForm.getName())
                        .surname(orderForm.getSurname())
                        .phone(orderForm.getPhone())
                        .address(orderForm.getAddress())
                        .email(orderForm.getEmail())
                        .build()


        );
    }
}


