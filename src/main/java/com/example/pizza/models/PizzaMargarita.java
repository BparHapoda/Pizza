package com.example.pizza.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
public class PizzaMargarita extends Pizza{
    public PizzaMargarita(){
        this.receipt=new ArrayList<>();
        this.name=PizzaNames.MARGARITA;
        this.receipt.add(Ingridient.DOUGH);
        this.receipt.add(Ingridient.MOZARELLA);
        this.receipt.add(Ingridient.BACON);

        this.toppings=new ArrayList<>();
    }}


