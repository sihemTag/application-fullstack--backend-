package com.example.mdd.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CommentDTO {

    private Integer id;

    @JsonProperty("article_id")
    private ArticleDTO article;

    @JsonProperty("user_id")
    private UserDTO user;

    private String commentaire;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO articleId) {
        this.article = articleId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO userId) {
        this.user = userId;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}