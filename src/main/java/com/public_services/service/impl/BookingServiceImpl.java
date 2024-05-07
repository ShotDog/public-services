package com.public_services.service.impl;

import com.public_services.controller.request.CreateBookingRequest;
import com.public_services.controller.request.UpdateBookingRequest;
import com.public_services.controller.response.BookingResponse;
import com.public_services.entity.BookingEntity;
import com.public_services.entity.EmployeeEntity;
import com.public_services.entity.ServiceEntity;
import com.public_services.entity.UserInfoEntity;
import com.public_services.mapper.BookingMapper;
import com.public_services.repository.BookingRepository;
import com.public_services.service.BookingService;
import com.public_services.service.MailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    private final MailService mailService;

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
    public void approveOrder(Long id) {
        BookingEntity bookingEntity = getById(id);
        bookingEntity.setIsProcessed(true);
        bookingRepository.save(bookingEntity);

        mailService.sendEmail(bookingEntity.getUser().getLoginEntity().getEmail(), generateApproveMessage(bookingEntity));
    }

    @Override
    public void rejectOrder(Long id) {
        BookingEntity bookingEntity = getById(id);
        bookingEntity.setIsProcessed(false);
        bookingRepository.save(bookingEntity);

        mailService.sendEmail(bookingEntity.getUser().getLoginEntity().getEmail(), generateRejectMessage(bookingEntity));

    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    private BookingEntity getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void update(BookingEntity bookingEntity, UpdateBookingRequest updateBookingRequest) {
        if (updateBookingRequest.getServiceId() != null) {
            bookingEntity.setService(new ServiceEntity().setId(updateBookingRequest.getServiceId()));
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

    private String generateApproveMessage(BookingEntity bookingEntity) {
        String date = bookingEntity.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return bookingEntity.getUser().getFullName() + ", здравствуйте!\n Ваша бронь на услугу " + bookingEntity.getService().getName() + " " + date + " по адресу" + bookingEntity.getService().getOrganizationEntity().getAddress() + "подтверждена.\nИсполнитель " + bookingEntity.getEmployee().getFullName() + "\nЖдем вас!";
    }

    private String generateRejectMessage(BookingEntity bookingEntity) {
        String date = bookingEntity.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return bookingEntity.getUser().getFullName() + ", здравствуйте!\n Ваша бронь на услугу " + bookingEntity.getService().getName() + " " + date + " отменена.\n С уважением!";
    }
}
