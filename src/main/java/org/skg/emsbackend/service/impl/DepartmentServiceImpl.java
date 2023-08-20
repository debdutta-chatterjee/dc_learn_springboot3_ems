package org.skg.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import org.skg.emsbackend.dto.DepartmentDto;
import org.skg.emsbackend.entity.Department;
import org.skg.emsbackend.exception.ResourceNotFoundException;
import org.skg.emsbackend.mapper.DepartmentMapper;
import org.skg.emsbackend.repository.DepartmentRepository;
import org.skg.emsbackend.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService
{
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto)
    {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment =departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, long departmentId)
    {
        Department department =departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department id ["+departmentId+"] not found!"));

        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(long departmentId)
    {
        Department department =departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department id ["+departmentId+"] not found!"));
        departmentRepository.deleteById(department.getId());
    }

    @Override
    public DepartmentDto getDepartment(long departmentId)
    {
        Department department =departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department id ["+departmentId+"] not found!"));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments()
    {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDto =departments.stream()
                .map(d -> DepartmentMapper.mapToDepartmentDto(d))
                .collect(Collectors.toList());
        return departmentDto;
    }
}
