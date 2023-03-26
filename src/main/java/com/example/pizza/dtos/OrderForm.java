package com.example.pizza.dtos;



import com.example.pizza.models.Pizza;
import com.example.pizza.models.Topping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderForm {
    private int clientId;
   private String pizza;

   private String receipt;
private String login;
private String name;
private String surname;
private String phone;
    private String toppings;
    private String address;
private String email;
}
