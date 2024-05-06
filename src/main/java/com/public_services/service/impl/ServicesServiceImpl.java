package com.public_services.service.impl;

import com.public_services.controller.request.CreateServiceRequest;
import com.public_services.controller.response.ServiceResponse;
import com.public_services.entity.ServiceEntity;
import com.public_services.mapper.ServiceMapper;
import com.public_services.repository.ServiceRepository;
import com.public_services.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public Long create(CreateServiceRequest createServiceRequest) {
        ServiceEntity serviceEntity = serviceMapper.toEntity(createServiceRequest);
        return serviceRepository.save(serviceEntity).getId();
    }

    @Override
    public Page<ServiceResponse> findAll(Pageable pageable) {
        Page<ServiceEntity> serviceEntities = serviceRepository.findAll(pageable);
        return serviceEntities.map(serviceMapper::toResponse);
    }
}
