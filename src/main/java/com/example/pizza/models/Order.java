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
    private int id;
    private Integer customerId;
    private String login;
    private String item;
    private String receipt;

    private String toppings;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String email;




}
