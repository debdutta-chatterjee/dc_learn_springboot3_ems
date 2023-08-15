package org.skg.emsbackend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto
{
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
