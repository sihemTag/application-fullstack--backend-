package com.example.mdd.repositories;

import com.example.mdd.models.AbonnementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbonnementRepository extends JpaRepository<AbonnementEntity, Integer> {
    Optional<AbonnementEntity> findByTheme_IdAndUser_Id(Integer themeId, Integer userId);
}
