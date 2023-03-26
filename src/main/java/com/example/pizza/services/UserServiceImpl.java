package com.example.pizza.services;

import com.example.pizza.dtos.UserDto;
import com.example.pizza.repositories.UsersRepository;


public class UserServiceImpl implements UserService{

    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }
    @Override
    public UserDto getUserFromRepository(int id) {

        return usersRepository.findById(id);

    }
}
