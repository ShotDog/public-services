package com.public_services.service;


import com.public_services.controller.request.CreateEmployeeRequest;
import com.public_services.controller.request.UpdateEmployeeRequest;
import com.public_services.controller.response.EmployeeResponse;
import com.public_services.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Long create(CreateEmployeeRequest createEmployeeRequest);

    Page<EmployeeResponse> findAll(Pageable pageable);

    EmployeeEntity findById(Long employeeId);

    void update(Long id, UpdateEmployeeRequest updateEmployeeRequest);

    void delete(Long id);
}
