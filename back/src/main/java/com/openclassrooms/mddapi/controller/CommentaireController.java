package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentaireDto;
import com.openclassrooms.mddapi.dto.CreateCommentaireDto;
import com.openclassrooms.mddapi.entity.CommentaireEntity;
import com.openclassrooms.mddapi.service.CommentaireService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommentaireDto> addCommentaire(
            Authentication authentication,
            @Valid @RequestBody CreateCommentaireDto dto
    ) {
        Long userId = (Long) authentication.getPrincipal();
        CommentaireDto newComment = commentaireService.newCommentaire(userId, dto);
        return ResponseEntity.ok(newComment);
    }
}
