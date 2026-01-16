package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.AbonnementDto;
import com.openclassrooms.mddapi.dto.UserThemeDto;
import com.openclassrooms.mddapi.service.AbonnementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/abonnement")
public class AbonnementController {

    private final AbonnementService abonnementService;

    public AbonnementController(AbonnementService abonnementService) {
        this.abonnementService = abonnementService;
    }

    @GetMapping("/get")
    public UserThemeDto[] get(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return abonnementService.getThemesOfCurrentUser(userId);
    }

    @GetMapping("/removeAbonnement/{idTheme}")
    public ResponseEntity<Map<String, Object>> removeAbonnement(Authentication authentication, @PathVariable Long idTheme) {
        Long userId = (Long) authentication.getPrincipal();

        boolean success = abonnementService.removeThemeFromCurrentUser(idTheme, userId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Thème supprimé avec succès" : "Impossible de supprimer le thème");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/addAbonnement/{idTheme}")
    public ResponseEntity<AbonnementDto> addAbonnement(Authentication authentication, @PathVariable Long idTheme) {
        Long userId = (Long) authentication.getPrincipal();
        AbonnementDto dto = abonnementService.addThemeFromCurrentUser(idTheme, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
