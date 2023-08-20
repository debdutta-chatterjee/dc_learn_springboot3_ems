package org.skg.emsbackend.controller;

import lombok.AllArgsConstructor;
import org.skg.emsbackend.dto.DepartmentDto;
import org.skg.emsbackend.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController
{
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto)
    {
        DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<DepartmentDto>(createdDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") long departmentId)
    {
        DepartmentDto department = departmentService.getDepartment(departmentId);
        return new ResponseEntity<DepartmentDto>(department, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments()
    {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable("id") long departmentId)
    {
        DepartmentDto department = departmentService.updateDepartment(departmentDto,departmentId);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("{id}")
    public void deleteDepartment(@PathVariable("id") long departmentId)
    {
        departmentService.deleteDepartment(departmentId);
    }
}
