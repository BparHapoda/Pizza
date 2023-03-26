package com.example.pizza.services;

import com.example.pizza.dtos.SignInForm;
import com.example.pizza.repositories.UsersRepository;

public class SignInServiceImpl implements SignInService{
    private UsersRepository usersRepository;

    public SignInServiceImpl(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    @Override
    public boolean login(SignInForm form) {
if (usersRepository.findByLoginAndPassword(form.getLogin(), form.getPassword() )
        .map(user -> true).orElse(false)
){
    return true;
        }
        return false;
    }
@Override
    public Integer getUserIdByLogin (String login){
        if( usersRepository.findByLogin(login).isPresent())
        {
         return  usersRepository.findByLogin(login).get()  ;
        }
        else{
            return null;
        }

    }
}
