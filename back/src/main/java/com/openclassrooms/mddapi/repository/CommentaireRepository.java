package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.CommentaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<CommentaireEntity, Long> {
    List<CommentaireEntity> findByArticle_IdArticleOrderByDateAsc(Long idArticle);
}
