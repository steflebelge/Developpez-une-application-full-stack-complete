package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.CommentaireDto;
import com.openclassrooms.mddapi.dto.CreateArticleDto;
import com.openclassrooms.mddapi.dto.UserThemeDto;
import com.openclassrooms.mddapi.entity.*;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final AbonnementRepository  abonnementRepository;
    private final CommentaireRepository  commentaireRepository;
    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    public ArticleService(
            ArticleRepository articleRepository,
            AbonnementRepository abonnementRepository,
            CommentaireRepository commentaireRepository,
            ThemeRepository themeRepository,
            UserRepository userRepository,
            ArticleMapper articleMapper
    ) {
        this.articleRepository = articleRepository;
        this.abonnementRepository = abonnementRepository;
        this.commentaireRepository = commentaireRepository;
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

    public List<ArticleDto> getAllArticles(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

        // Récupère les abonnements
        List<Long> themeIds = abonnementRepository.findByUser(user).stream()
                .map(abonnement -> {
                    ThemeEntity theme = abonnement.getTheme();
                    return theme.getIdTheme();
                })
                .toList();

        if (themeIds.isEmpty()) {
            return List.of();
        }

        return articleRepository
                .findByTheme_IdThemeInOrderByDateDesc(themeIds)
                .stream()
                .map(articleMapper::toDto)
                .toList();
    }

    public ArticleDto getArticleById(Long id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article introuvable"));

        // récupère les commentaires liés à l'article, triés par date
        List<CommentaireEntity> commentaires = commentaireRepository
                .findByArticle_IdArticleOrderByDateAsc(id);

        // mappe l'article
        ArticleDto dto = articleMapper.toDto(article);

        // mappe les commentaires vers le DTO
        dto.setCommentaires(commentaires.stream().map(c -> {
            CommentaireDto cdto = new CommentaireDto();
            cdto.setIdCommentaire(c.getIdCommentaire());
            cdto.setMessage(c.getMessage());
            cdto.setDate(c.getDate());
            cdto.setUsername(c.getUser().getName());
            return cdto;
        }).toList());

        return dto;
    }
}
