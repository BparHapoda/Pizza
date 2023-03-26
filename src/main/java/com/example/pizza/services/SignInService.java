package com.example.pizza.services;

import com.example.pizza.dtos.SignInForm;

public interface SignInService {
    boolean login(SignInForm form);

    Integer getUserIdByLogin(String login);
}
