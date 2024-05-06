package com.public_services.entity;

import com.public_services.entity.metadata.LoginEntity_;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = LoginEntity_.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Accessors(chain = true)
public class LoginEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = LoginEntity_.ID)
    private Long id;

    @Column(name = LoginEntity_.EMAIL)
    private String email;

    @Column(name = LoginEntity_.PASSWORD)
    private String password;

    @ManyToOne
    @JoinColumn(name = LoginEntity_.ROLE_ID)
    private RoleEntity roleEntity;
}
