package com.public_services.controller.rest;

import com.public_services.controller.request.UpdateOrganizationRequest;
import com.public_services.controller.response.OrganizationResponse;
import com.public_services.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<Page<OrganizationResponse>> findAll(final Pageable pageable) {
        Page<OrganizationResponse> organizations = organizationService.findAll(pageable);
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> findById(@PathVariable final Long id) {
        OrganizationResponse organizationResponse = organizationService.findById(id);
        return ResponseEntity.ok(organizationResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable final Long id, UpdateOrganizationRequest updateOrganizationRequest) {
        organizationService.update(id, updateOrganizationRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Long id) {
        organizationService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
