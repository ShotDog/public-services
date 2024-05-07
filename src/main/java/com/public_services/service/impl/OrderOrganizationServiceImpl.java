package com.public_services.service.impl;

import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.CreateOrderOrganizationRequest;
import com.public_services.controller.request.CreateOrganizationRequest;
import com.public_services.controller.response.OrderOrganizationResponse;
import com.public_services.entity.OrderOrganizationEntity;
import com.public_services.mapper.OrderOrganizationMapper;
import com.public_services.repository.OrderOrganizationRepository;
import com.public_services.service.LoginService;
import com.public_services.service.MailService;
import com.public_services.service.OrderOrganizationService;
import com.public_services.service.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderOrganizationServiceImpl implements OrderOrganizationService {

    private final OrderOrganizationRepository orderOrganizationRepository;
    private final OrderOrganizationMapper orderOrganizationMapper;

    private final OrganizationService organizationService;
    private final LoginService loginService;

    private final MailService mailService;

    @Override
    public Long create(CreateOrderOrganizationRequest createOrderOrganizationRequest) {
        OrderOrganizationEntity orderOrganizationEntity = orderOrganizationMapper.toEntity(createOrderOrganizationRequest);
        orderOrganizationEntity.setIsProcessed(false);
        return orderOrganizationRepository.save(orderOrganizationEntity).getId();
    }

    @Override
    @Transactional
    public Long approveOrder(Long id) {
        OrderOrganizationEntity orderOrganizationEntity = getById(id);

        CreateLoginRequest createLoginRequest = new CreateLoginRequest(orderOrganizationEntity.getEmail(), null);
        Long loginId = loginService.create(createLoginRequest);

        CreateOrganizationRequest createOrganizationRequest = new CreateOrganizationRequest(orderOrganizationEntity.getName(), orderOrganizationEntity.getPan(), orderOrganizationEntity.getAddress(), loginId);
        Long organizationId = organizationService.create(createOrganizationRequest);

        mailService.sendEmail(orderOrganizationEntity.getEmail(), "Ваш пароль такой : 12345678");

        orderOrganizationEntity.setIsProcessed(true);
        orderOrganizationRepository.save(orderOrganizationEntity);

        return organizationId;
    }

    @Override
    public void rejectOrder(Long id) {
        OrderOrganizationEntity orderOrganizationEntity = getById(id);
        orderOrganizationRepository.deleteById(id);
        mailService.sendEmail(orderOrganizationEntity.getEmail(), "Ваша заявка отклонена");
    }

    @Override
    public Page<OrderOrganizationResponse> findAll(Boolean isProcessed, Pageable pageable) {
        Page<OrderOrganizationEntity> orderOrganizationEntities;
        if (isProcessed != null) {
            orderOrganizationEntities = orderOrganizationRepository.findByIsProcessed(isProcessed, pageable);
        } else {
            orderOrganizationEntities = orderOrganizationRepository.findAll(pageable);
        }
        return orderOrganizationEntities.map(orderOrganizationMapper::toResponse);
    }

    private OrderOrganizationEntity getById(Long id) {
        return orderOrganizationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
