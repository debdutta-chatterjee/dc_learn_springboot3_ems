package org.skg.emsbackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentDto
{
    private long id;
    private String departmentName;
    private String departmentDescription;
}
