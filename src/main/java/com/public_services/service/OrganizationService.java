package com.public_services.service;

import com.public_services.controller.request.CreateOrganizationRequest;
import com.public_services.controller.response.OrganizationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizationService {

    Long create(CreateOrganizationRequest organizationRequest);

    Page<OrganizationResponse> findAll(Pageable pageable);

    OrganizationResponse findById(Long id);

}
