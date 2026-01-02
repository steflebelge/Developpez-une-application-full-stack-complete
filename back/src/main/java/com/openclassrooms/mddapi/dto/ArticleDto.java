package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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