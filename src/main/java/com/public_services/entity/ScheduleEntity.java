package com.public_services.entity;

import com.public_services.entity.metadata.ScheduleEntity_;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = ScheduleEntity_.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = ScheduleEntity_.ID)
    private Long id;

    @Column(name = ScheduleEntity_.WORK_TIME)
    private LocalDateTime workTime;

    @Column(name = ScheduleEntity_.IS_BOOKED)
    private Boolean isBooked;

    @ManyToOne
    @JoinColumn(name = ScheduleEntity_.EMPLOYEE_ID)
    private EmployeeEntity employee;

}
