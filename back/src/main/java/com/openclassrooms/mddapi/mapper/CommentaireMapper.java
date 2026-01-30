package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentaireDto;
import com.openclassrooms.mddapi.entity.CommentaireEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentaireMapper {

    @Mapping(target = "idArticle", source = "article.idArticle")
    CommentaireDto toDto(CommentaireEntity commentaire);

    @Mapping(target = "idCommentaire", ignore = true)
    @Mapping(target = "article", ignore = true)
    CommentaireEntity toEntity(CommentaireDto dto);
}
