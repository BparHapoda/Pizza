package com.example.pizza.repositories;

import com.example.pizza.dtos.SignInForm;
import com.example.pizza.models.Order;
import com.example.pizza.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    void save(User user);

    Optional<User> findByLogin(String login);

    Optional <User> searchByNameSurname(String name,String surname);

    List<User> findAll();

    Optional<User> findByLoginAndPassword(String login,String password);

}
