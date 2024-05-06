package com.public_services.controller.rest;

import com.public_services.controller.request.CreateEmployeeRequest;
import com.public_services.controller.request.UpdateEmployeeRequest;
import com.public_services.controller.response.EmployeeResponse;
import com.public_services.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateEmployeeRequest request) {
        Long id = employeeService.create(request);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAll(Pageable pageable) {
        Page<EmployeeResponse> employees = employeeService.findAll(pageable);
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody UpdateEmployeeRequest request) {
        employeeService.update(id, request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
