package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.entity.ThemeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeDto toDto(ThemeEntity entity);
    ThemeEntity toEntity(ThemeDto dto);
}

