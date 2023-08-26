package org.skg.emsbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.skg.emsbackend.dto.JwtAuthResponse;
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
@Tag(
        name = "REST APIs for Authentication",
        description = "CRUD REST APIs - Register User, Login User"
)
public class AuthController
{
    private AuthService authService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
    {
        return new ResponseEntity<String>(authService.register(registerDto),
                HttpStatus.CREATED);
    }

    @Operation(
            summary = "Login User REST API",
            description = "Login User REST API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Ok"
    )
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto)
    {
        //String token = authService.login(loginDto);
        //JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        //jwtAuthResponse.setAccessToken(token);

        //return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);

        //return new ResponseEntity<String>(authService.login(loginDto),
              //  HttpStatus.OK);
    }
}
