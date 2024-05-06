package com.public_services.service.impl;

import com.public_services.controller.request.CreateOrganizationRequest;
import com.public_services.controller.response.OrganizationResponse;
import com.public_services.entity.LoginEntity;
import com.public_services.entity.OrganizationEntity;
import com.public_services.mapper.OrganizationMapper;
import com.public_services.repository.OrganizationRepository;
import com.public_services.service.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public Long create(CreateOrganizationRequest organizationRequest) {
        OrganizationEntity organizationEntity = organizationMapper.toEntity(organizationRequest);
        if (organizationRequest.getLoginId()!=null){
            organizationEntity.setLoginEntity(new LoginEntity().setId(organizationRequest.getLoginId()));
        }
        return organizationRepository.save(organizationEntity).getId();
    }

    @Override
    public Page<OrganizationResponse> findAll(Pageable pageable) {
        Page<OrganizationEntity> organizationEntities = organizationRepository.findAll(pageable);
        return organizationEntities.map(organizationMapper::toDto);
    }

    @Override
    public OrganizationResponse findById(Long id) {
        OrganizationEntity organizationEntity = getById(id);
        return organizationMapper.toDto(organizationEntity);
    }

    private OrganizationEntity getById(Long id) {
        return organizationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
