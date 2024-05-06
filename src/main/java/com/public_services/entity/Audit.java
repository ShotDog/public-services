package com.public_services.entity;

import com.public_services.entity.metadata.Audit_;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Audit {

    @CreatedDate
    @Column(name = Audit_.CREATED_DATE)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = Audit_.UPDATED_DATE)
    private LocalDateTime updatedDate;

}
