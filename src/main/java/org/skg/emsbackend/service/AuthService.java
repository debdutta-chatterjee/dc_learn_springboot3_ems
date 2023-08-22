package org.skg.emsbackend.service;

import org.skg.emsbackend.dto.LoginDto;
import org.skg.emsbackend.dto.RegisterDto;

public interface AuthService
{
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
