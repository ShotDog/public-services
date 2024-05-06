package com.public_services.entity.metadata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleEntity_ {

    public static final String TABLE_NAME = "schedule";

    public static final String ID = "id";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String WORK_TIME = "work_time";

    public static final String IS_BOOKED = "is_booked";
}
