package com.public_services.controller.rest;

import com.public_services.controller.request.CreateServiceRequest;
import com.public_services.controller.response.ServiceResponse;
import com.public_services.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesService servicesService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateServiceRequest request) {
        Long id = servicesService.create(request);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<Page<ServiceResponse>> getAll(Pageable pageable) {
        Page<ServiceResponse> response = servicesService.findAll(pageable);
        return ResponseEntity.ok(response);
    }
}
