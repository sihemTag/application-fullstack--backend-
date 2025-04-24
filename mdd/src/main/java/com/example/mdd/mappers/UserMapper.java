package com.example.mdd.mappers;

import com.example.mdd.dto.responses.UserDTO;
import com.example.mdd.models.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);
    UserEntity toEntity(UserDTO userDTO);
}
