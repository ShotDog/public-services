package com.public_services.service;

import com.public_services.entity.EmployeeEntity;
import com.public_services.repository.ScheduleRepository;

import java.util.List;

public interface ScheduleService {

    void createSchedule(EmployeeEntity employee);

    List<ScheduleRepository.BookDates> getScheduleByEmployeeId(Long employeeId);

}
