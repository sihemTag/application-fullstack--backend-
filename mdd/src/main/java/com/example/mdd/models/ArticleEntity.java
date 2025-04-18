package com.example.mdd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("owner_id")
    @ManyToOne
    @JoinColumn(name = "owner_id" , nullable = false)
    private UserEntity owner;

    /*@OneToMany(mappedBy = "article")
    @JsonProperty("comments")
    private List<CommentEntity> comments;*/

    public ArticleEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return title;
    }

    public void setTitre(String titre) {
        this.title = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    /*public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }*/
}
