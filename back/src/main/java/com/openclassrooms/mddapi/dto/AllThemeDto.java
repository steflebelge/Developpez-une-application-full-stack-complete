package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllThemeDto {
    private long idTheme;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private Boolean isUserAbonner = false;
}