package com.example.pizza.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private String login;
    private Pizza pizza;
    private ArrayList<Toppings> toppings;

    private String adress;


}
