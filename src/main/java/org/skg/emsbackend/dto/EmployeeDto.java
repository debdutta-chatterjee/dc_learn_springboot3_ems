package org.skg.emsbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto
{
    private long id;

    //User firstname should not be null or empty
    @NotEmpty(message = "User firstname must not be empty")
    private String firstName;
    @NotEmpty(message = "User lastname must not be empty")
    private String lastName;
    @NotEmpty(message = "User email must not be empty")
    @Email(message = "User email should be valid")
    private String email;
}
