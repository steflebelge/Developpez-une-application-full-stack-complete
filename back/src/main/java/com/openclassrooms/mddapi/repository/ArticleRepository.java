package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findByTheme_IdThemeInOrderByDateDesc(List<Long> themeIds);
    Optional<ArticleEntity> findById(Long id);
}
