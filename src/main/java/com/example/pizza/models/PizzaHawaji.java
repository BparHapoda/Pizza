package com.example.pizza.models;


import lombok.Data;


import java.util.ArrayList;

@Data
public class PizzaHawaji extends Pizza{

    public PizzaHawaji(){
        this.receipt=new ArrayList<>();
     this.name=PizzaNames.HAWAJI;
     this.toppings=new ArrayList<>();
        this.receipt.add(Ingridient.DOUGH);
    this.receipt.add(Ingridient.SHRIMPS);
    this.receipt.add(Ingridient.ANANAS);
}}
