package com.public_services.service;

import com.public_services.controller.request.CreateBookingRequest;
import com.public_services.controller.request.UpdateBookingRequest;
import com.public_services.controller.response.BookingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    Long create(CreateBookingRequest createBookingRequest);

    Page<BookingResponse> findAll(Boolean isProcessed, Pageable pageable);

    BookingResponse findById(Long id);

    Page<BookingResponse> findByUser(Long userId, Pageable pageable);

    void update(Long id, UpdateBookingRequest updateBookingRequest);

    void approveOrder(Long id);

    void rejectOrder(Long id);

    void delete(Long id);
}
