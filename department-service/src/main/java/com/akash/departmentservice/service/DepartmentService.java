package com.akash.departmentservice.service;

import com.akash.departmentservice.client.EmployeeClient;
import com.akash.departmentservice.dtos.DepartmentDto;
import com.akash.departmentservice.dtos.EmployeeDto;
import com.akash.departmentservice.model.Department;
import com.akash.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    public Department addDepartment(Department department) {
        log.info("Adding Department {}", department);

        if (department.getId() != null && departmentRepository.existsById(department.getId())) {
            Department existing = departmentRepository.findById(department.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));

            existing.setName(department.getName());
            return departmentRepository.save(existing);
        } else {
            return departmentRepository.save(department);
        }
    }

    public Department getDepartmentById(Long id) {
        log.info("Getting Department by ID {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department with id " + id + " not found"));
    }

    public List<Department> getAllDepartments() {
        log.info("Getting all Departments");
        return departmentRepository.findAll();
    }

    public List<DepartmentDto> getDepartmentsWithEmployees() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(dept -> DepartmentDto.builder()
                        .id(dept.getId())
                        .name(dept.getName())
                        .employees(employeeClient.findByDepartmentId(dept.getId()))
                        .build())
                .toList();
    }
}
