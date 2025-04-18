package com.example.mdd.dto;

import com.example.mdd.models.ArticleEntity;
import com.example.mdd.models.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CommentDTO {
    private Integer id;
    @JsonProperty("article_id")
    private ArticleEntity article;
    @JsonProperty("user_id")
    private UserEntity user;
    private String comment;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public CommentDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

