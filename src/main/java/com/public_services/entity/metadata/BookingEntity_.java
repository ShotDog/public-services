package com.public_services.entity.metadata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingEntity_ {

    public static final String TABLE_NAME = "booking";
    public static final String ID = "id";
    public static final String SERVICE_ID = "service_id";
    public static final String USER_ID = "user_id";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String DATE_TIME = "date_time";
    public static final String IS_PROCESSED = "is_processed";

    public static final String FILE_DATA = "file_data";

}
