package com.example.pizza.models;

import java.util.ArrayList;

public class PizzaMy extends Pizza{

    public PizzaMy() {
        this.receipt=new ArrayList<>();
        this.name = PizzaNames.MY_PIZZA;

        this.toppings = new ArrayList<>();
    }
}