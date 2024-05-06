package com.public_services.entity;

import com.public_services.entity.metadata.Employees_;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = Employees_.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Employee extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = Employees_.ID)
    private Long id;

    @Column(name = Employees_.FULL_NAME)
    private String fullName;

    @Column(name = Employees_.POSITION)
    private String position;

    @ManyToOne
    @JoinColumn(name = Employees_.LOGIN_ID)
    private LoginEntity login;

    @ManyToOne
    @JoinColumn(name = Employees_.ORGANIZATION_ID)
    private OrganizationEntity organization;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Employee employee = (Employee) o;
        return getId() != null && Objects.equals(getId(), employee.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + id + ", " + "fullName = " + fullName + ", " + "position = " + position + ", " + "login = " + login + ", " + "organization = " + organization + ")";
    }
}
