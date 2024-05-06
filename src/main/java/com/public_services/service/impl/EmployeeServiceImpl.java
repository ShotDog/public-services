package com.public_services.service.impl;

import com.public_services.controller.request.CreateEmployeeRequest;
import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.UpdateEmployeeRequest;
import com.public_services.controller.response.EmployeeResponse;
import com.public_services.entity.Employee;
import com.public_services.entity.LoginEntity;
import com.public_services.mapper.EmployeesMapper;
import com.public_services.repository.EmployeeRepository;
import com.public_services.service.EmployeeService;
import com.public_services.service.LoginService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeesMapper employeesMapper;

    private final LoginService loginService;

    @Override
    public Long create(CreateEmployeeRequest createEmployeeRequest) {
        Employee employee = employeesMapper.toEntity(createEmployeeRequest);

        CreateLoginRequest createLoginRequest = new CreateLoginRequest(createEmployeeRequest.getEmail(), null);
        Long loginId = loginService.create(createLoginRequest);
        employee.setLogin(new LoginEntity().setId(loginId));
        return employeeRepository.save(employee).getId();
    }

    @Override
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employeesMapper::toResponse);
    }

    @Override
    public void update(Long id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = getById(id);
        update(employee, updateEmployeeRequest);
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    private Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void update(Employee employee, UpdateEmployeeRequest updateEmployeeRequest) {
        if (updateEmployeeRequest.getFullName()!=null){
            employee.setFullName(updateEmployeeRequest.getFullName());
        }
        if (updateEmployeeRequest.getPosition()!=null){
            employee.setPosition(updateEmployeeRequest.getPosition());
        }
    }
}
