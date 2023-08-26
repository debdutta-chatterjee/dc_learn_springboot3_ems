package org.skg.emsbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "UserDto Model Information"
)

public class LoginDto
{
    @Schema(
            description = "User name or email"
    )
    private String usernameOrEmail;
    @Schema(
            description = "User password"
    )
    private String password;
}
