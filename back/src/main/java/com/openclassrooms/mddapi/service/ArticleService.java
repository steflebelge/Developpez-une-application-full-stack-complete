package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.CreateArticleDto;
import com.openclassrooms.mddapi.entity.ArticleEntity;
import com.openclassrooms.mddapi.entity.ThemeEntity;
import com.openclassrooms.mddapi.entity.UserEntity;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    public ArticleService(
            ArticleRepository articleRepository,
            ThemeRepository themeRepository,
            UserRepository userRepository,
            ArticleMapper articleMapper
    ) {
        this.articleRepository = articleRepository;
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
        this.articleMapper = articleMapper;
    }

    public ArticleEntity createArticle(Long userId, CreateArticleDto dto) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

        ThemeEntity theme = themeRepository.findById(dto.getThemeId())
                .orElseThrow(() -> new EntityNotFoundException("Thème introuvable"));

        ArticleEntity article = new ArticleEntity();
        article.setUser(user);
        article.setTheme(theme);
        article.setTitre(dto.getTitle());
        article.setContenu(dto.getContent());

        return articleRepository.save(article);
    }

    public List<ArticleDto> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(articleMapper::toDto)
                .toList();
    }
}
