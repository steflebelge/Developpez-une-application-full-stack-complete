package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
