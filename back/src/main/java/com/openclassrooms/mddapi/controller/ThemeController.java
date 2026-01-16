package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.AllThemeDto;
import com.openclassrooms.mddapi.dto.UserThemeDto;
import com.openclassrooms.mddapi.service.ThemeService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/getAll")
    public List<AllThemeDto> getAll(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return themeService.getAllThemes(userId);
    }


}
