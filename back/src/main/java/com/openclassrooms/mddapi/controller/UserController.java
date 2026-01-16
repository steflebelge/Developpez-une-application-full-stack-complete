package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserUpdateRequestDto;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/edit")
    public UserDto updateUser(@Valid @RequestBody UserUpdateRequestDto dto, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return userService.updateUser(userId, dto);
    }
}
