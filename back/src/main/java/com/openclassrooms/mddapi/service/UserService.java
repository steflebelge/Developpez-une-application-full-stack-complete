package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserUpdateRequestDto;
import com.openclassrooms.mddapi.entity.UserEntity;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmailOrUsername(usernameOrEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), new ArrayList<>());
    }


    public UserDto updateUser(Long userId, UserUpdateRequestDto dto) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        // Vérifications d'unicité (email / name)
        userRepository.findByEmail(dto.getEmail())
                .filter(u -> !u.getIdUser().equals(userId))
                .ifPresent(u -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
                });
        userRepository.findByName(dto.getName())
                .filter(u -> !u.getIdUser().equals(userId))
                .ifPresent(u -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Nom déjà utilisé");
                });

        // Mise à jour
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        String password = dto.getPassword();
        if (password != null && !password.isBlank()) {
            // Validation complexe duy mot de passe
            if (password.length() < 8
                    || !password.matches(".*[a-z].*")
                    || !password.matches(".*[A-Z].*")
                    || !password.matches(".*\\d.*")
                    || !password.matches(".*[^A-Za-z0-9].*")) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Mot de passe invalide : il doit contenir au moins 8 caractères, " +
                                "une majuscule, une minuscule, un chiffre et un caractère spécial"
                );
            }

            // Encodage et mise à jour
            user.setPassword(passwordEncoder.encode(password));
        }

        //enregistrement des nouvelles infos utilisateur
        userRepository.save(user);
        return userMapper.toDto(user);
    }

}