package com.example.mdd.controllers;

import com.example.mdd.dto.responses.ArticleDTO;
import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.services.implementations.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAll() {
        List<ArticleDTO> articles = articleService.getAll();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Integer id) throws NotFoundException {
        ArticleDTO article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            Authentication authentication
    ) {
        ArticleDTO article = articleService.createArticle(title, description, authentication);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(
            @PathVariable Integer id,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) throws NotFoundException {
        articleService.updateArticle(id, title, description);
        return ResponseEntity.ok("Article updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticleById(@PathVariable Integer id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("Article deleted!");
    }

}
