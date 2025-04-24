package com.example.mdd.mappers;

import com.example.mdd.dto.responses.AbonnementDTO;
import com.example.mdd.models.AbonnementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ThemeMapper.class})
public interface AbonnementMapper {

    AbonnementDTO toDTO(AbonnementEntity abonnement);
    AbonnementEntity toEntity(AbonnementDTO abonnementDTO);
}
