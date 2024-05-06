package com.public_services.service.impl;

import com.public_services.entity.EmployeeEntity;
import com.public_services.entity.ScheduleEntity;
import com.public_services.repository.ScheduleRepository;
import com.public_services.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;


    @Override
    public void createSchedule(EmployeeEntity employee) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, LocalDate.now().getMonthValue());
        cal.set(Calendar.DAY_OF_WEEK, LocalDate.now().getDayOfWeek().getValue());
        Integer weekDay = cal.get(Calendar.DAY_OF_WEEK);
        Integer lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Integer currentDay = LocalDate.now().getDayOfMonth();
        LocalDate localDate = LocalDate.now();
        switch (employee.getRate()) {
            case FIVE_AND_TWO -> {
                for (int i = currentDay; i < lastDay; i++) {
                    if (weekDay != 6 && weekDay != 7) {
                        List<ScheduleEntity> scheduleEntities = generateScheduleByFive(i, employee);
                        scheduleRepository.saveAll(scheduleEntities);
                    }
                    weekDay++;
                    if (weekDay == 7) {
                        weekDay = 1;
                        i++;
                    }
                }

            }
            case TWO_AND_TWO -> {
                int dayOfMonth = LocalDate.now().getDayOfMonth();
                for (int i = dayOfMonth; i < lastDay; i += 2) {

                    LocalDateTime dateTimeFirst = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), i, 0, 0);
                    scheduleRepository.saveAll(generateScheduleByDay(dateTimeFirst, employee));
                    i++;
                    LocalDateTime dateTimeSecond = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), i, 0, 0);
                    scheduleRepository.saveAll(generateScheduleByDay(dateTimeSecond, employee));
                }
            }
        }
    }

    @Override
    public List<ScheduleRepository.BookDates> getScheduleByEmployeeId(Long employeeId) {
        return scheduleRepository.findScheduleByEmployeeId(employeeId);
    }

    private List<ScheduleEntity> generateScheduleByFive(Integer currentDay, EmployeeEntity employee) {
        List<ScheduleEntity> scheduleEntities = new ArrayList<>();
        for (int i = 8; i < 17; i++) {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setEmployee(employee);
            LocalDateTime workTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), currentDay, i, 0);
            scheduleEntity.setWorkTime(workTime);
            scheduleEntity.setIsBooked(false);
            scheduleEntities.add(scheduleEntity);
        }
        return scheduleEntities;
    }

    private List<ScheduleEntity> generateScheduleByDay(LocalDateTime dateTime, EmployeeEntity employee) {
        List<ScheduleEntity> scheduleEntities = new ArrayList<>();
        for (int i = 8; i < 20; i++) {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setEmployee(employee);
            LocalDateTime workTime = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), i, 0);
            scheduleEntity.setWorkTime(workTime);
            scheduleEntity.setIsBooked(false);
            scheduleEntities.add(scheduleEntity);
        }
        return scheduleEntities;
    }
}
