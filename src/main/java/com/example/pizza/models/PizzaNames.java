package com.example.pizza.models;

public enum PizzaNames {
    
    MARGARITA ("Маргарита"),
    FOUR_CHEESE ("Четыре сыра"),
    CAPRICHOSA ("Капричоза"),
    HAWAJI("Гавайская"),
    MY_PIZZA ("Пицца по собственному рецепту");

    private final String title;

    PizzaNames(String title){
      this.title=title;
    }

    public String getTitle() {
        return title;
    }
}