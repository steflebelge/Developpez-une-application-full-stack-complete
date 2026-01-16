package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.LoginRequestDto;
import com.openclassrooms.mddapi.dto.LoginResponseDto;
import com.openclassrooms.mddapi.dto.SignupRequestDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.UserEntity;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.service.AuthService;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    public AuthController(AuthService authService,
                          UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody SignupRequestDto request) {

        UserEntity user = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toDto(user));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {

        String token = authService.login(dto);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return authService.getCurrentUser(userId);
    }

    @GetMapping("/checkToken")
    public ResponseEntity<Map<String, Object>>  checkToken() {
        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        return ResponseEntity.ok(response);

    }

}
