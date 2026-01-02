package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Recherche par email (utile pour login / vérification)
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
