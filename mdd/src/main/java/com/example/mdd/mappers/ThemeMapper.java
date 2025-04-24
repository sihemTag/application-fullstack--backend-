package com.example.mdd.mappers;

import com.example.mdd.dto.responses.ThemeDTO;
import com.example.mdd.models.ThemeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    ThemeDTO toDTO(ThemeEntity theme);
    ThemeEntity toEntity(ThemeDTO themeDTO);
}
