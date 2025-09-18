package com.akash.departmentservice.client;

import com.akash.departmentservice.dtos.EmployeeDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {
    @GetExchange("/employee/department/id")
    public List<EmployeeDto> findByDepartmentId(@RequestParam Long departmentId);
}
