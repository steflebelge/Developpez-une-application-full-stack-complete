package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserThemeDto {
    private long idTheme;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 200)
    private String description;
}