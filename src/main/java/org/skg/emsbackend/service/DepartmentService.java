package org.skg.emsbackend.service;

import org.skg.emsbackend.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService
{
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(DepartmentDto departmentDto, long departmentId);

    void deleteDepartment(long departmentId);

    DepartmentDto getDepartment(long departmentId);

    List<DepartmentDto> getAllDepartments();
}
