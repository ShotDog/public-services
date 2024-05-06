package com.public_services.service.impl;

import com.public_services.controller.request.CreateEmployeeRequest;
import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.UpdateEmployeeRequest;
import com.public_services.controller.response.EmployeeResponse;
import com.public_services.entity.EmployeeEntity;
import com.public_services.entity.LoginEntity;
import com.public_services.enums.Rate;
import com.public_services.mapper.EmployeesMapper;
import com.public_services.repository.EmployeeRepository;
import com.public_services.service.EmployeeService;
import com.public_services.service.LoginService;
import com.public_services.service.ScheduleService;
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

    private final ScheduleService scheduleService;

    @Override
    public Long create(CreateEmployeeRequest createEmployeeRequest) {
        EmployeeEntity employeeEntity = employeesMapper.toEntity(createEmployeeRequest);
        employeeEntity.setRate(createEmployeeRequest.getRate() == null ? Rate.FIVE_AND_TWO : Rate.parseText(createEmployeeRequest.getRate()));

        CreateLoginRequest createLoginRequest = new CreateLoginRequest(createEmployeeRequest.getEmail(), null);
        Long loginId = loginService.create(createLoginRequest);
        employeeEntity.setLogin(new LoginEntity().setId(loginId));

        Long id = employeeRepository.save(employeeEntity).getId();
        scheduleService.createSchedule(employeeEntity);
        return id;
    }

    @Override
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        Page<EmployeeEntity> employees = employeeRepository.findAll(pageable);
        return employees.map(employeesMapper::toResponse);
    }

    @Override
    public EmployeeEntity findById(Long employeeId) {
        return getById(employeeId);
    }

    @Override
    public void update(Long id, UpdateEmployeeRequest updateEmployeeRequest) {
        EmployeeEntity employeeEntity = getById(id);
        update(employeeEntity, updateEmployeeRequest);
        employeeRepository.save(employeeEntity);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeEntity getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void update(EmployeeEntity employeeEntity, UpdateEmployeeRequest updateEmployeeRequest) {
        if (updateEmployeeRequest.getFullName() != null) {
            employeeEntity.setFullName(updateEmployeeRequest.getFullName());
        }
        if (updateEmployeeRequest.getPosition() != null) {
            employeeEntity.setPosition(updateEmployeeRequest.getPosition());
        }
    }
}
