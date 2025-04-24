package com.example.mdd.mappers;

import com.example.mdd.dto.responses.CommentDTO;
import com.example.mdd.models.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticleMapper.class, UserMapper.class})
public interface CommentMapper {
    @Mapping(source = "article", target = "article")
    @Mapping(source = "user", target = "user")
    CommentDTO toDTO(CommentEntity comment);

    @Mapping(source = "article", target = "article")
    @Mapping(source = "user", target = "user")
    CommentEntity toEntity(CommentDTO commentDTO);
}
