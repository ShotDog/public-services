package com.public_services.controller.rest;

import com.public_services.controller.request.CreateBookingRequest;
import com.public_services.controller.request.UpdateBookingRequest;
import com.public_services.controller.response.BookingResponse;
import com.public_services.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateBookingRequest request) {
        Long id = bookingService.create(request);
        return ResponseEntity.status(201).body(id);
    }

    @GetMapping
    public ResponseEntity<Page<BookingResponse>> findAll(@RequestParam(required = false) Boolean isProcessed, Pageable pageable) {
        Page<BookingResponse> bookingResponses = bookingService.findAll(isProcessed, pageable);
        return ResponseEntity.status(200).body(bookingResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> findById(@PathVariable Long id) {
        BookingResponse bookingResponse = bookingService.findById(id);
        return ResponseEntity.status(200).body(bookingResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody UpdateBookingRequest request) {
        bookingService.update(id, request);
        return ResponseEntity.status(200).body(HttpStatus.OK);
    }

    @PatchMapping("/{id}/approving")
    public ResponseEntity<HttpStatus> approving(@PathVariable Long id) {
        bookingService.approveOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/rejecting")
    public ResponseEntity<HttpStatus> approve(@PathVariable Long id) {
        bookingService.rejectOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
