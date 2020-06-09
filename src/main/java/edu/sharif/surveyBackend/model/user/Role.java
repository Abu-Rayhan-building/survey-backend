package edu.sharif.surveyBackend.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.RolesValue;
import lombok.Data;

@Data
@Entity
public class Role extends PanacheEntity {
    public static Role add(final String string) {
	final var role = new Role(string);
	role.persist();
	return role;
    }

    @ManyToMany(mappedBy = "roles")
    public List<User> users;

    @RolesValue
    public String role;

    public Role() {
    }

    public Role(final String string) {
	this.role = string;
    }
}
