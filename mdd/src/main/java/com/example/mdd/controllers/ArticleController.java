package com.example.mdd.controllers;

import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.models.ArticleEntity;
import com.example.mdd.models.CommentEntity;
import com.example.mdd.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleEntity> getAll(){
        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ArticleEntity> getArticleById(@PathVariable Integer id) throws NotFoundException {
        return articleService.getArticleById(id);
    }

    @PostMapping
    public ResponseEntity<ArticleEntity> createArticle(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            Authentication authentication
    ){
        ArticleEntity article = articleService.createArticle(title,description,authentication);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(
            @PathVariable Integer id,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) throws NotFoundException {
        articleService.updateArticle(id,title,description);
        return new ResponseEntity<>("Article updated !", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable Integer id){
        articleService.deleteArticle(id);
    }

    /*@GetMapping("/{id}/comments")
    public List<CommentEntity> getComments(@PathVariable Integer id) throws NotFoundException {
        return articleService.getComments(id);
    }*/
}
