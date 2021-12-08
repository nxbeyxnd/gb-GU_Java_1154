package ru.gb.mall.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.dto.AuthRequestDto;
import ru.gb.mall.inventory.entity.User;
import ru.gb.mall.inventory.sevice.AuthService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthUserController {
    private final AuthService authService;

    public AuthUserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public User saveNewUser(@RequestBody AuthRequestDto authRequestDto) {
        return authService.saveNewUser(authRequestDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findUserByLoginAndPass(@RequestBody AuthRequestDto authRequestDto){
        return authService.findUserByLoginAndPassword(authRequestDto);
    }
}
