package com.example.mdd.repositories;

import com.example.mdd.models.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
}
