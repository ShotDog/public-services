package com.public_services.service.impl;

import com.public_services.entity.BookingEntity;
import com.public_services.repository.BookingRepository;
import com.public_services.service.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final BookingRepository bookingRepository;

    @Override
    public void uploadFile(Long id, byte[] file) {
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        bookingEntity.setFile(file);
        bookingEntity.setIsProcessed(true);
        bookingRepository.save(bookingEntity);
    }

    @Override
    public byte[] downloadFile(Long id) {
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return bookingEntity.getFile();
    }
}
