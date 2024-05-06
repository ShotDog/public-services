package com.public_services.controller.rest;

import com.public_services.repository.ScheduleRepository;
import com.public_services.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ScheduleRepository.BookDates>> getSchedules(@PathVariable("id") Long id) {
        List<ScheduleRepository.BookDates> schedules = scheduleService.getScheduleByEmployeeId(id);
        return ResponseEntity.ok(schedules);
    }

}
