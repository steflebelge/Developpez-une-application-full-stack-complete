package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.CommentaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<CommentaireEntity, Long> {

    // Tu peux ajouter des recherches par date, par article ou utilisateur si nécessaire
}
