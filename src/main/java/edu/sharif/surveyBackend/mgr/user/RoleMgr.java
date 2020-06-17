package edu.sharif.surveyBackend.mgr.user;

import edu.sharif.surveyBackend.model.user.Role;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class RoleMgr implements PanacheRepository<Role> {

    public static Role add(final String string) {
	final var role = new Role(string);
	role.persist();
	return role;
    }

}
