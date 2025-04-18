package com.example.mdd.services;

import com.example.mdd.dto.CommentDTO;
import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.models.ArticleEntity;
import com.example.mdd.models.CommentEntity;
import com.example.mdd.models.UserEntity;
import com.example.mdd.repositories.ArticleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;

    public ArticleService(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
    }


    public List<ArticleEntity> getAll(){
        return articleRepository.findAll();
    }

    public Optional<ArticleEntity> getArticleById(Integer id) throws NotFoundException {
        return articleRepository.findById(id);
    }

    public ArticleEntity createArticle(String title, String description, Authentication authentication){
        ArticleEntity article = new ArticleEntity();
        article.setTitre(title);
        article.setDescription(description);
        article.setCreatedAt(LocalDateTime.now());
        UserEntity owner = userService.currentUserName(authentication)
                .orElseThrow(() -> new IllegalArgumentException("No user authenticated found!"));
        article.setOwner(owner);
        articleRepository.save(article);

        return article;
    }

    public void updateArticle(Integer id, String title, String description) throws NotFoundException{
        ArticleEntity currentArticle = articleRepository.findById(id).orElseThrow(()-> new NotFoundException("Article doesn't exist!"));

        currentArticle.setTitre(title);
        currentArticle.setDescription(description);
        currentArticle.setUpdatedAt(LocalDateTime.now());

        articleRepository.save(currentArticle);
    }

    public void deleteArticle(Integer id){
        articleRepository.deleteById(id);
    }

    /*public List<CommentEntity> getComments(Integer articleId) throws NotFoundException {
        ArticleEntity article = articleRepository.findById(articleId).orElseThrow(()-> new NotFoundException("Article doesn't exist!"));
        return article.getComments();
    }*/

}
