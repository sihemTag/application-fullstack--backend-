package com.example.mdd.services.interfaces;

import com.example.mdd.dto.responses.CommentDTO;
import com.example.mdd.exceptions.NotFoundException;

import java.util.List;

public interface CommentInterface {
    public CommentDTO addComment(String message, Integer articleId, Integer userId) throws NotFoundException;
    public void deleteById(Integer id);
    public List<CommentDTO> getCommentsByArticle(Integer articleId);
}
