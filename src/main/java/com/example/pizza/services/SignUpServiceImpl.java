package com.example.pizza.services;

import com.example.pizza.dtos.SignUpForm;
import com.example.pizza.models.User;
import com.example.pizza.repositories.UsersRepository;

public class SignUpServiceImpl implements SignUpService{

private static UsersRepository usersRepository;

public SignUpServiceImpl(UsersRepository usersRepository){
    this.usersRepository=usersRepository;
}
    @Override
    public void signUp(SignUpForm form) {
    User user= User.builder()
            .login(form.getLogin())
            .password(form.getPassword())
            .name(form.getName())
            .surname(form.getSurname())
            .phone(form.getPhone())
            .email(form.getEmail())
            .build();
    usersRepository.save(user);
    }
}
