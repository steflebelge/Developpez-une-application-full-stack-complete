package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "userId", source = "user.idUser")
    @Mapping(target = "username", source = "user.name")
    @Mapping(target = "themeId", source = "theme.idTheme")
    @Mapping(target = "themeName", source = "theme.name")
    ArticleDto toDto(ArticleEntity entity);
}

