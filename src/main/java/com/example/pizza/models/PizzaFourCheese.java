package com.example.pizza.models;


import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PizzaFourCheese extends Pizza{

    public PizzaFourCheese(){
        this.name=PizzaNames.FOUR_CHEESE;
        this.receipt=new ArrayList<>();
        this.receipt.add(Ingridient.DOUGH);
        this.receipt.add(Ingridient.FIRST_CHEESE);
        this.receipt.add(Ingridient.SECOND_CHEESE);
        this.receipt.add(Ingridient.MOZARELLA);
        this.receipt.add(Ingridient.PARMESAN);

   this.toppings=new ArrayList<>();
    }
}
