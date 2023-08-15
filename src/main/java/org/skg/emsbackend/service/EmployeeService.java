package org.skg.emsbackend.service;

import org.skg.emsbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService
{
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(long id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(long id, EmployeeDto updateEmployee);

    void deleteEmployee(long id);
}
