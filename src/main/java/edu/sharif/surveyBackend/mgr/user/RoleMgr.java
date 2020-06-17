package edu.sharif.surveyBackend.mgr.user;

import java.util.List;

import edu.sharif.surveyBackend.model.user.Role;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.elytron.security.common.BcryptUtil;

public class RoleMgr {

    public static Role add(final String string) {
	final var role = new Role(string);
	role.persist();
	return role;
    }

}
