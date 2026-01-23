package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentaireDto;
import com.openclassrooms.mddapi.dto.CreateCommentaireDto;
import com.openclassrooms.mddapi.entity.ArticleEntity;
import com.openclassrooms.mddapi.entity.CommentaireEntity;
import com.openclassrooms.mddapi.entity.UserEntity;
import com.openclassrooms.mddapi.mapper.CommentaireMapper;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentaireRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentaireService {
    private final CommentaireRepository commentaireRepository;
    private final ArticleRepository articleRepository;
    private  final UserRepository userRepository;
    private final CommentaireMapper commentaireMapper;

    public CommentaireService(
            CommentaireRepository commentaireRepository, ArticleRepository articleRepository,
            UserRepository userRepository,
            CommentaireMapper commentaireMapper) {
        this.commentaireRepository = commentaireRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentaireMapper = commentaireMapper;
    }

    public CommentaireDto newCommentaire(Long userId, @Valid CreateCommentaireDto dto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        ArticleEntity article = articleRepository.findById(dto.getIdArticle())
                .orElseThrow(() -> new EntityNotFoundException("Article introuvable"));

        CommentaireEntity  commentaireEntity = new CommentaireEntity();
        commentaireEntity.setUser(user);
        commentaireEntity.setArticle(article);
        commentaireEntity.setMessage(dto.getContent());
        commentaireEntity.setDate(LocalDateTime.now());

        return commentaireMapper.toDto(commentaireRepository.save(commentaireEntity));
    }
}
