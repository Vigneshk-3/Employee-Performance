package com.controller;


import com.dto.DeviationResponse;
import com.dto.SuggestionResponse;
import com.model.Employee;
import com.model.Rating;
import com.service.AppraisalService;
import com.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final AppraisalService appraisalService;

    public EmployeeController(EmployeeService employeeService, AppraisalService appraisalService) {
        this.employeeService = employeeService;
        this.appraisalService = appraisalService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/deviations")
    public DeviationResponse getDeviations() {
        List<Employee> employees = employeeService.getAllEmployees();
        return appraisalService.calculateDeviation(employees);
    }

    @GetMapping("/suggestions")
    public List<SuggestionResponse> getSuggestions() {
        List<Employee> employees = employeeService.getAllEmployees();
        DeviationResponse deviations = appraisalService.calculateDeviation(employees);
        return appraisalService.suggestRevisions(employees, deviations);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PostMapping("/ratings")
    public Rating addOrUpdateRating(@RequestBody Rating rating) {
        return appraisalService.addOrUpdateRating(rating);
    }

    @PutMapping("/employees/{id}/rating")
    public Employee updateEmployeeRating(@PathVariable Long id, @RequestParam String rating) {
        return employeeService.updateEmployeeRating(id, rating);
    }
}