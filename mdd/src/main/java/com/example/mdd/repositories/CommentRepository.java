package com.example.mdd.repositories;

import com.example.mdd.models.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
