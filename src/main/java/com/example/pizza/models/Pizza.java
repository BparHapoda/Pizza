package com.example.pizza.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
public abstract class Pizza {
    public PizzaNames name;
    public ArrayList<Ingridient> receipt;
    public ArrayList<Topping> toppings;
}
