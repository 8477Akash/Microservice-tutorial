package com.akash.employeeservice.controller;

import com.akash.employeeservice.model.Employee;
import com.akash.employeeservice.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmployeeController {

    final EmployeeService employeeService;

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/id")
    public Employee findById(@RequestParam Long id){
        return employeeService.findById(id);
    }

    @GetMapping("/getAll")
    public List<Employee> getAll(){
        return employeeService.findAll();
    }
    @GetMapping("/department/id")
    public List<Employee> findByDepartmentId(@RequestParam Long departmentId){
        return employeeService.findByDepartmentId(departmentId);
    }
}
