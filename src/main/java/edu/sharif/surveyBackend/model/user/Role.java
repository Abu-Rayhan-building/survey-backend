package edu.sharif.surveyBackend.model.user;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.RolesValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Cacheable
public class Role extends PanacheEntity {
    public static Role add(final String string) {
        final var role = new Role(string);
        role.persist();
        return role;
    }

    @RolesValue
    public String role;

    public Role(final String string) {
        this.role = string;
    }
}
