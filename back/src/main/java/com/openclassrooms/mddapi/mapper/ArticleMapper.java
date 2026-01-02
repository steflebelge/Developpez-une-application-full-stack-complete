package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "idTheme", source = "theme.idTheme")
    ArticleDto toDto(ArticleEntity entity);

    @Mapping(target = "idArticle", ignore = true)
    @Mapping(target = "theme", ignore = true)
    ArticleEntity toEntity(ArticleDto dto);
}
