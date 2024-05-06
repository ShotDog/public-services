package com.public_services.repository;

import com.public_services.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("""
            SELECT s.workTime as workTime,
            s.isBooked as isBooked
             FROM ScheduleEntity s
            WHERE s.employee.id=:employeeId
            AND (month (s.workTime)=month (current_date)
            OR month (s.workTime)=month (current_date)+1)
            """)
    List<BookDates> findScheduleByEmployeeId(Long employeeId);

    interface BookDates{
        LocalDateTime getWorkTime();
        Boolean getIsBooked();
    }

}
