package org.skg.emsbackend.controller;

import lombok.AllArgsConstructor;
import org.skg.emsbackend.dto.LoginDto;
import org.skg.emsbackend.dto.RegisterDto;
import org.skg.emsbackend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController
{
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
    {
        return new ResponseEntity<String>(authService.register(registerDto),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
    {
        return new ResponseEntity<String>(authService.login(loginDto),
                HttpStatus.OK);
    }
}
