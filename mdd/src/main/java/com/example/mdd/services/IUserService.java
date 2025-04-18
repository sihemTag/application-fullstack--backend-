package com.example.mdd.services;


import com.example.mdd.dto.RegisterDTO;
import com.example.mdd.exceptions.UserAlreadyExistException;
import com.example.mdd.models.UserEntity;

public interface IUserService {
    UserEntity save(RegisterDTO registerDto) throws UserAlreadyExistException;
}
