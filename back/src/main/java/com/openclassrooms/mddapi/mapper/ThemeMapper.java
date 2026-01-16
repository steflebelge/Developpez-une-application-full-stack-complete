package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserThemeDto;
import com.openclassrooms.mddapi.entity.ThemeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    UserThemeDto toDto(ThemeEntity entity);
    ThemeEntity toEntity(UserThemeDto dto);
}

