package com.example.mdd.services.interfaces;

import com.example.mdd.dto.responses.ArticleDTO;
import com.example.mdd.exceptions.NotFoundException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ArticleInterface {
    public List<ArticleDTO> getAll();
    public ArticleDTO getArticleById(Integer id) throws NotFoundException;
    public ArticleDTO createArticle(String title, String description, Authentication authentication);
    public void updateArticle(Integer id, String title, String description) throws NotFoundException;
    public void deleteArticle(Integer id);
}
