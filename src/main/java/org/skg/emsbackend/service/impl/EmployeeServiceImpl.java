package org.skg.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import org.skg.emsbackend.dto.EmployeeDto;
import org.skg.emsbackend.entity.Employee;
import org.skg.emsbackend.exception.ResourceNotFoundException;
import org.skg.emsbackend.mapper.EmployeeMapper;
import org.skg.emsbackend.repository.EmployeeRepository;
import org.skg.emsbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService
{

    EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto)
    {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee =employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+id+" does not exist!"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((e) -> EmployeeMapper.mapToEmployeeDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+id+" does not exist!"));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail_id(updateEmployee.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id)
    {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+id+" does not exist!"));
        employeeRepository.deleteById(id);

    }
}

