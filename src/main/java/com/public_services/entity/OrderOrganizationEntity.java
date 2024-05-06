package com.public_services.entity;

import com.public_services.entity.metadata.OrderOrganizationEntity_;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = OrderOrganizationEntity_.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class OrderOrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = OrderOrganizationEntity_.ID)
    private Long id;

    @Column(name = OrderOrganizationEntity_.NAME)
    private String name;

    @Column(name = OrderOrganizationEntity_.PAN)
    private String pan;

    @Column(name = OrderOrganizationEntity_.EMAIL)
    private String email;

    @Column(name = OrderOrganizationEntity_.ADDRESS)
    private String address;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrderOrganizationEntity that = (OrderOrganizationEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + id + ", " + "name = " + name + ", " + "pan = " + pan + ", " + "email = " + email + ", " + "address = " + address + ")";
    }
}
