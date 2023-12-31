package org.skg.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.skg.emsbackend.dto.JwtAuthResponse;
import org.skg.emsbackend.dto.LoginDto;
import org.skg.emsbackend.dto.RegisterDto;
import org.skg.emsbackend.entity.Role;
import org.skg.emsbackend.entity.User;
import org.skg.emsbackend.exception.TodoAPIException;
import org.skg.emsbackend.repository.RoleRepository;
import org.skg.emsbackend.repository.UserRepository;
import org.skg.emsbackend.security.JwtTokenProvider;
import org.skg.emsbackend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService
{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername()))
        {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Usernmae already exists in database.");
        }

        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Email already exists in database.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        //return "User logged in successfully!";

        Optional<User> user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
                loginDto.getUsernameOrEmail());

        String role = null;
        if(user.isPresent())
        {
            User loggedInUser = user.get();
            Optional<Role> optionalRole = loggedInUser.getRoles()
                    .stream().findFirst();

            if(optionalRole.isPresent())
            {
                Role userRole = optionalRole.get();
                role = userRole.getName();
            }

        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }
}
