package com.example.pizza.dtos;

import com.example.pizza.models.Toppings;

import java.util.List;

public class OrderForm {
    private String login;
    private  String name;
    private String surname;
    private String pizza;
    private List<Toppings> toppingsList;
    private String address;
}
