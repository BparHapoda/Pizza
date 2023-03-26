package com.example.pizza.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PizzaCaprichosa extends Pizza{

    public PizzaCaprichosa(){
this.receipt=new ArrayList<>();
        this.name=PizzaNames.CAPRICHOSA;
        this.receipt.add(Ingridient.DOUGH);
        this.receipt.add(Ingridient.TOMATO);
        this.receipt.add(Ingridient.MUSHROOMS);
        this.receipt.add(Ingridient.MOZARELLA);
   this.toppings=new ArrayList<>();

    }
}
