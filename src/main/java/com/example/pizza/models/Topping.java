package com.example.pizza.models;

public enum Topping {
    CHEESE("Сыр"),
    EGG("Яйцо"),
    HAM("Ветчина"),
    PINE_APPLE("Ананас"),
    FISH("Рыба"),
    CHILI("Острый перец"),
    OLIVES("Оливки");

    private final String title;

    Topping(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }
}