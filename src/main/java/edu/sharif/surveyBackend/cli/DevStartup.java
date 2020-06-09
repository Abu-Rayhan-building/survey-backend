package edu.sharif.surveyBackend.cli;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;
import edu.sharif.surveyBackend.model.user.Role;
import edu.sharif.surveyBackend.model.user.User;

@Singleton
public class DevStartup {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
	// reset and load all test users

	Role.deleteAll();
	var roleAdmin = Role.add("admin");
	var roleuser = Role.add("user");

	User.deleteAll();
	User.add("admin", "admin", roleAdmin);
	User.add("user", "user", roleuser);
    }
}
