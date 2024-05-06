package com.public_services.service;

import com.public_services.controller.request.CreateServiceRequest;
import com.public_services.controller.response.ServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServicesService {

    Long create(CreateServiceRequest createServiceRequest);

    Page<ServiceResponse> findAll(Pageable pageable);

}
