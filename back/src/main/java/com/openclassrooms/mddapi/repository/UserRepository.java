package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByName(String name);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :login OR u.name = :login")
    Optional<UserEntity> findByEmailOrUsername(@Param("login") String login);
}
