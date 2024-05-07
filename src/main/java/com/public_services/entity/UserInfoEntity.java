package com.public_services.entity;

import com.public_services.entity.metadata.LoginEntity_;
import com.public_services.entity.metadata.UserInfoEntity_;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = UserInfoEntity_.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Accessors(chain = true)
public class UserInfoEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = UserInfoEntity_.ID)
    private Long id;

    @Column(name = UserInfoEntity_.FULL_NAME)
    private String fullName;

    @Column(name = UserInfoEntity_.PASSPORT_NUMBER)
    private String passportNumber;

    @Column(name = UserInfoEntity_.PASSPORT_SERIES)
    private String passportSeries;

    @Column(name = UserInfoEntity_.LOCATION)
    private String location;

    @ManyToOne
    @JoinColumn(name = UserInfoEntity_.LOGIN_ID)
    private LoginEntity loginEntity;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserInfoEntity that = (UserInfoEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + getId() + ", " + "fullName = " + getFullName() + ", " + "passportNumber = " + getPassportNumber() + ", " + "passportSeries = " + getPassportSeries() + ", " + "location = " + getLocation() + ", " + "loginEntity = " + getLoginEntity() + ", " + "createdDate = " + getCreatedDate() + ", " + "updatedDate = " + getUpdatedDate() + ")";
    }
}
