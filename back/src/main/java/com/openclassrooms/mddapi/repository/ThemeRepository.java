package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {

    boolean existsByName(String name);

    List<ThemeEntity> findAll();
}
