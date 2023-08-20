package org.skg.emsbackend.mapper;

import org.skg.emsbackend.dto.DepartmentDto;
import org.skg.emsbackend.entity.Department;

public class DepartmentMapper
{
    public static DepartmentDto mapToDepartmentDto(Department department)
    {
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto)
    {
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }
}
