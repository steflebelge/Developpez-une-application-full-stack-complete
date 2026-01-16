package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.AbonnementDto;
import com.openclassrooms.mddapi.dto.UserThemeDto;
import com.openclassrooms.mddapi.entity.AbonnementEntity;
import com.openclassrooms.mddapi.entity.ThemeEntity;
import com.openclassrooms.mddapi.entity.UserEntity;
import com.openclassrooms.mddapi.mapper.AbonnementMapper;
import com.openclassrooms.mddapi.repository.AbonnementRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbonnementService {
    private final AbonnementRepository abonnementRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final AbonnementMapper abonnementMapper;

    public AbonnementService(AbonnementRepository abonnementRepository,
                             UserRepository userRepository,
                             ThemeRepository themeRepository,
                             AbonnementMapper abonnementMapper) {
        this.abonnementRepository = abonnementRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.abonnementMapper = abonnementMapper;
    }

    public UserThemeDto[] getThemesOfCurrentUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

        return abonnementRepository.findByUser(user)
                .stream()
                .map(abonnement -> {
                    ThemeEntity theme = abonnement.getTheme();
                    return new UserThemeDto(
                            theme.getIdTheme(),
                            theme.getName(),
                            theme.getDescription()
                    );
                })
                .toArray(UserThemeDto[]::new);
    }

    public boolean removeThemeFromCurrentUser(Long idTheme, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

        Optional<AbonnementEntity> abonnementOpt = abonnementRepository.findByUser_idUserAndTheme_idTheme(userId, idTheme);
        if (abonnementOpt.isPresent()) {
            abonnementRepository.delete(abonnementOpt.get());
            return true;
        }
        return false;
    }

    public AbonnementDto addThemeFromCurrentUser(Long idTheme, Long userId) {

        if (abonnementRepository.existsByUser_idUserAndTheme_idTheme(userId, idTheme)) {
            throw new IllegalStateException("Utilisateur déjà abonné à ce thème");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

        ThemeEntity theme = themeRepository.findById(idTheme)
                .orElseThrow(() -> new EntityNotFoundException("Theme introuvable"));

        AbonnementEntity abonnement = new AbonnementEntity();
        abonnement.setUser(user);
        abonnement.setTheme(theme);
        AbonnementEntity saved = abonnementRepository.save(abonnement);
        return abonnementMapper.toDto(saved);
    }
}
