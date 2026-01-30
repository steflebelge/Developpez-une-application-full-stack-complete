package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.AllThemeDto;
import com.openclassrooms.mddapi.repository.AbonnementRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final AbonnementRepository abonnementRepository;

    public ThemeService(ThemeRepository themeRepository,
                        AbonnementRepository abonnementRepository) {
        this.themeRepository = themeRepository;
        this.abonnementRepository = abonnementRepository;
    }

    //recuperation de la liste des themes avec infos de si l'utilisateur est abonné ou non
    public List<AllThemeDto> getAllThemes(Long userId) {
        return themeRepository.findAll().stream()
                .map(theme -> {
                    AllThemeDto dto = new AllThemeDto();
                    dto.setIdTheme(theme.getIdTheme());
                    dto.setName(theme.getName());
                    dto.setDescription(theme.getDescription());

                    boolean isAbonner =
                            abonnementRepository.existsByUser_idUserAndTheme_idTheme(
                                    userId,
                                    theme.getIdTheme()
                            );

                    dto.setIsUserAbonner(isAbonner);
                    return dto;
                })
                .toList();
    }
}
