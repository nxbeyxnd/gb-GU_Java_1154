package ru.gb.mall.inventory.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.dto.AuthRequestDto;
import ru.gb.mall.inventory.entity.User;
import ru.gb.mall.inventory.service.AuthService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthUserController {
    private final AuthService authService;

    public AuthUserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveNewUser(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authService.saveNewUser(authRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> findUserByLoginAndPass(@RequestBody AuthRequestDto authRequestDto){
        return ResponseEntity.ok(authService.findUserByLoginAndPassword(authRequestDto));
    }
}
