package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.ArticleEntity;
import com.openclassrooms.mddapi.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    // Récupérer les articles par thème
    List<ArticleEntity> findByTheme(ThemeEntity theme);

    // Recherche par titre (optionnel)
    List<ArticleEntity> findByTitreContainingIgnoreCase(String titre);
}
