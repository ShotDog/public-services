package com.public_services.service;

import com.public_services.controller.request.CreateOrderOrganizationRequest;
import com.public_services.controller.response.OrderOrganizationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderOrganizationService {

    Long create(CreateOrderOrganizationRequest createOrderOrganizationRequest);

    Long approveOrder(Long id);

    void rejectOrder(Long id);

    Page<OrderOrganizationResponse> findAll(Pageable pageable);

}
