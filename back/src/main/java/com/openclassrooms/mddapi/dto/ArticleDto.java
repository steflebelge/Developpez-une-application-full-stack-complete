package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private long idArticle;

    private long idTheme;

    @NotNull
    @Size(max = 30)
    private String titre;

    @NotBlank
    private String contenu;

    @NotNull
    private LocalDateTime date;
}