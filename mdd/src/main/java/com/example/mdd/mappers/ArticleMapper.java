package com.example.mdd.mappers;

import com.example.mdd.dto.responses.ArticleDTO;
import com.example.mdd.models.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    ArticleDTO toDTO(ArticleEntity articleEntity);

    ArticleEntity toEntity(ArticleDTO dto);
}
