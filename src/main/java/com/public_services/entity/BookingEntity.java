package com.public_services.entity;

import com.public_services.entity.metadata.BookingEntity_;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = BookingEntity_.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = BookingEntity_.ID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = BookingEntity_.SERVICE_ID)
    private ServiceEntity service;

    @Column(name = BookingEntity_.DATE_TIME)
    private LocalDateTime dateTime;

    @Column(name = BookingEntity_.IS_PROCESSED)
    private Boolean isProcessed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = BookingEntity_.EMPLOYEE_ID)
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = BookingEntity_.USER_ID)
    private UserInfoEntity user;

    @Lob
    @Column(name = BookingEntity_.FILE_DATA)
    private byte[] file;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        BookingEntity that = (BookingEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + id + ", " + "service = " + service + ", " + "dateTime = " + dateTime + ", " + "isProcessed = " + isProcessed + ")";
    }
}
