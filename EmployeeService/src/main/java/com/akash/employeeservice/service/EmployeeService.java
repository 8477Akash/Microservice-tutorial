package com.akash.employeeservice.service;

import com.akash.employeeservice.model.Employee;
import com.akash.employeeservice.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    final EmployeeRepository employeeRepository;

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List <Employee> findAll(){
        return employeeRepository.findAll();
    }

    public List<Employee> findByDepartmentId(Long departmentId){
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
