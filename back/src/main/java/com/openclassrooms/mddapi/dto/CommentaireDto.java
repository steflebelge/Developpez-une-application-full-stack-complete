package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireDto {
    private long idCommentaire;
    private long idArticle;   // nouvelle propriété
    @NotBlank
    private String message;
    @NotNull
    private LocalDateTime date;
}
