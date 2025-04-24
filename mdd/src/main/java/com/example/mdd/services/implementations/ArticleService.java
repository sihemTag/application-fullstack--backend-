package com.example.mdd.services.implementations;

import com.example.mdd.dto.responses.ArticleDTO;
import com.example.mdd.dto.responses.UserDTO;
import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.mappers.ArticleMapper;
import com.example.mdd.mappers.UserMapper;
import com.example.mdd.models.ArticleEntity;
import com.example.mdd.repositories.ArticleRepository;
import com.example.mdd.services.interfaces.ArticleInterface;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService implements ArticleInterface {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ArticleMapper articleMapper;

    public ArticleService(ArticleRepository articleRepository, UserService userService, UserMapper userMapper, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleDTO> getAll() {
        return articleRepository.findAll()
                .stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getArticleById(Integer id) throws NotFoundException {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article doesn't exist!"));
        return articleMapper.toDTO(article);
    }

    @Override
    public ArticleDTO createArticle(String title, String description, Authentication authentication) {
        ArticleEntity article = new ArticleEntity();
        article.setTitle(title);
        article.setDescription(description);
        article.setCreatedAt(LocalDateTime.now());

        UserDTO ownerDTO = userService.currentUserName(authentication)
                .orElseThrow(() -> new IllegalArgumentException("No authenticated user found!"));
        article.setOwner(userMapper.toEntity(ownerDTO));

        ArticleEntity saved = articleRepository.save(article);
        return articleMapper.toDTO(saved);
    }

    @Override
    public void updateArticle(Integer id, String title, String description) throws NotFoundException {
        ArticleEntity currentArticle = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article doesn't exist!"));

        currentArticle.setTitle(title);
        currentArticle.setDescription(description);
        currentArticle.setUpdatedAt(LocalDateTime.now());

        articleRepository.save(currentArticle);
    }

    @Override
    public void deleteArticle(Integer id){
        articleRepository.deleteById(id);
    }

}
