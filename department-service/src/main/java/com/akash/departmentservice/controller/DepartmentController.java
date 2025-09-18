package com.akash.departmentservice.controller;

import com.akash.departmentservice.dtos.DepartmentDto;
import com.akash.departmentservice.model.Department;
import com.akash.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/add")
    public Department add(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @GetMapping("/id")
    public Department get(@RequestParam Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/getAll")
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/with-employees")
    public List<DepartmentDto> getWithEmployees() {
        return departmentService.getDepartmentsWithEmployees();
    }
}
