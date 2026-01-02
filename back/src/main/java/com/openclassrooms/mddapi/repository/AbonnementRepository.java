package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.AbonnementEntity;
import com.openclassrooms.mddapi.entity.AbonnementId;
import com.openclassrooms.mddapi.entity.ThemeEntity;
import com.openclassrooms.mddapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<AbonnementEntity, AbonnementId> {

    List<AbonnementEntity> findByUser(UserEntity user);

    List<AbonnementEntity> findByTheme(ThemeEntity theme);

    boolean existsById(AbonnementId id);
}
