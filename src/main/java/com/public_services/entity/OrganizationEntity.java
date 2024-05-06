package com.public_services.entity;

import com.public_services.entity.metadata.OrganizationEntity_;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = OrganizationEntity_.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class OrganizationEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = OrganizationEntity_.ID)
    private Long id;

    @Column(name = OrganizationEntity_.NAME)
    private String name;

    @Column(name = OrganizationEntity_.PAN)
    private String pan;

    @Column(name = OrganizationEntity_.LOCATION)
    private String location;

    @ManyToOne
    @JoinColumn(name = OrganizationEntity_.LOGIN_ID)
    private LoginEntity loginEntity;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrganizationEntity that = (OrganizationEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + getId() + ", " + "name = " + getName() + ", " + "pan = " + getPan() + ", " + "location = " + getLocation() + ", " + "loginEntity = " + getLoginEntity() + ", " + "createdDate = " + getCreatedDate() + ", " + "updatedDate = " + getUpdatedDate() + ")";
    }
}
