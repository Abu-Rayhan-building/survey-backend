package edu.sharif.surveyBackend.model.user;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.RolesValue;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Cacheable
public class Role extends PanacheEntity {


    @RolesValue
    public String role;

    public Role(final String string) {
        this.role = string;
    }
}
