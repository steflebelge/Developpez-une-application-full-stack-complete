package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.AbonnementDto;
import com.openclassrooms.mddapi.entity.AbonnementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AbonnementMapper {

    // Entity → DTO
    @Mapping(target = "idUser", source = "user.idUser")
    @Mapping(target = "idTheme", source = "theme.idTheme")
    AbonnementDto toDto(AbonnementEntity entity);

    // DTO → Entity
    // user et theme injectés dans le service
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "theme", ignore = true)
    AbonnementEntity toEntity(AbonnementDto dto);
}
