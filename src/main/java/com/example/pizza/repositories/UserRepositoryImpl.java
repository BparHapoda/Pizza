package com.example.pizza.repositories;

import com.example.pizza.models.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UsersRepository{
    @Override
    public void save(User user) {

    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> searchByNameSurname(String name, String surname) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
