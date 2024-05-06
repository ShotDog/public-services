package com.public_services.controller.rest;

import com.public_services.controller.request.CreateOrderOrganizationRequest;
import com.public_services.controller.response.OrderOrganizationResponse;
import com.public_services.service.OrderOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderOrganizationController {

    private final OrderOrganizationService orderOrganizationService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateOrderOrganizationRequest request) {
        Long orderId = orderOrganizationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping
    public ResponseEntity<Page<OrderOrganizationResponse>> findAll(Pageable pageable) {
        Page<OrderOrganizationResponse> orderOrganizationResponses = orderOrganizationService.findAll(pageable);
        return ResponseEntity.ok(orderOrganizationResponses);
    }

    @PatchMapping("/{id}/approving")
    public ResponseEntity<Long> approving(@PathVariable Long id) {
        Long organizationId = orderOrganizationService.approveOrder(id);
        return ResponseEntity.ok(organizationId);
    }

    @PatchMapping("/{id}/rejecting")
    public ResponseEntity<HttpStatus> approve(@PathVariable Long id) {
        orderOrganizationService.rejectOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
