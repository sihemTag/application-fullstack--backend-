package com.example.mdd.services.interfaces;


import com.example.mdd.dto.responses.RegisterDTO;
import com.example.mdd.exceptions.UserAlreadyExistException;
import com.example.mdd.models.UserEntity;

public interface UserServiceInterface {
    UserEntity save(RegisterDTO registerDto) throws UserAlreadyExistException;
}
