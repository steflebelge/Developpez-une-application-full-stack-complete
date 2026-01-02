package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.AbonnementDto;
import com.openclassrooms.mddapi.entity.AbonnementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AbonnementMapper {

    @Mapping(target = "idUser", source = "id.idUser")
    @Mapping(target = "idTheme", source = "id.idTheme")
    AbonnementDto toDto(AbonnementEntity entity);

    @Mapping(target = "id", expression = "java(new AbonnementId(dto.getIdUser(), dto.getIdTheme()))")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "theme", ignore = true)
    AbonnementEntity toEntity(AbonnementDto dto);
}
