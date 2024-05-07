package com.public_services.service.impl;

import com.public_services.controller.request.CreateBookingRequest;
import com.public_services.controller.request.UpdateBookingRequest;
import com.public_services.controller.response.BookingResponse;
import com.public_services.entity.BookingEntity;
import com.public_services.entity.EmployeeEntity;
import com.public_services.entity.UserInfoEntity;
import com.public_services.mapper.BookingMapper;
import com.public_services.repository.BookingRepository;
import com.public_services.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Long create(CreateBookingRequest createBookingRequest) {
        BookingEntity bookingEntity = bookingMapper.toEntity(createBookingRequest);
        return bookingRepository.save(bookingEntity).getId();
    }

    @Override
    public Page<BookingResponse> findAll(Boolean isProcessed, Pageable pageable) {
        Page<BookingEntity> bookingEntities;
        if (isProcessed != null) {
            bookingEntities = bookingRepository.findByIsProcessed(isProcessed, pageable);
        } else {
            bookingEntities = bookingRepository.findAll(pageable);
        }
        return bookingEntities.map(bookingMapper::toResponse);
    }

    @Override
    public BookingResponse findById(Long id) {
        BookingEntity bookingEntity = getById(id);
        return bookingMapper.toResponse(bookingEntity);
    }

    @Override
    public void update(Long id, UpdateBookingRequest updateBookingRequest) {
        BookingEntity bookingEntity = getById(id);
        update(bookingEntity, updateBookingRequest);
        bookingRepository.save(bookingEntity);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    private BookingEntity getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void update(BookingEntity bookingEntity, UpdateBookingRequest updateBookingRequest) {
        if (updateBookingRequest.getServiceName() != null) {
            bookingEntity.setServiceName(updateBookingRequest.getServiceName());
        }
        if (updateBookingRequest.getDateTime() != null) {
            bookingEntity.setDateTime(updateBookingRequest.getDateTime());
        }
        if (updateBookingRequest.getEmployeeId() != null) {
            bookingEntity.setEmployee(new EmployeeEntity().setId(updateBookingRequest.getEmployeeId()));
        }
        if (updateBookingRequest.getUserId() != null) {
            bookingEntity.setUser(new UserInfoEntity().setId(updateBookingRequest.getUserId()));
        }
    }
}
