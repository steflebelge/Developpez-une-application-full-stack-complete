package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.CreateArticleDto;
import com.openclassrooms.mddapi.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createArticle(
            @RequestBody @Valid CreateArticleDto dto,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        articleService.createArticle(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAll")
    public List<ArticleDto> getAll() {
        return articleService.getAllArticles();
    }
}
