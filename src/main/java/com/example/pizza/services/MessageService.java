package com.example.pizza.services;

import com.example.pizza.dtos.OrderForm;
import jakarta.mail.MessagingException;

public interface MessageService {
    void send (OrderForm orderForm) throws MessagingException;
}
