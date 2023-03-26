package com.example.pizza.models;

public enum Ingridient {
        FIRST_CHEESE("Чеддер"),
        SECOND_CHEESE("Дор Блю"),
        PARMESAN("Пармезан"),
        MOZARELLA("Мозарелла"),
        SHRIMPS("Креветки"),
        ANANAS("Ананас"),
        ONION("Лук"),
        CHEESE("Сулугуни"),
        TOMATO("Помидоры"),
        SALAMI("Салями"),
        PEPPER("Перец"),
        BASIL("Базилик"),
        OLIVES("Оливки"),
        MUSHROOMS("Грибы"),
        PAPRIKA("Паприка"),
        GARLIC("Чеснок"),
        BACON("Бекон"),
        DOUGH("Тесто");

        public final String title;
         Ingridient (String title){
                this.title=title;
        }

        public String getTitle() {
                return title;
        }
}
