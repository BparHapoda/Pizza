package com.example.pizza.services;

import com.example.pizza.dtos.UserDto;

public interface UserService {
    UserDto getUserFromRepository(int id);
}
