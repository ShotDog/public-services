package com.public_services.repository;

import com.public_services.entity.OrderOrganizationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOrganizationRepository extends JpaRepository<OrderOrganizationEntity, Long> {
    Page<OrderOrganizationEntity> findByIsProcessed(Boolean isProcessed, Pageable pageable);
}
