package edu.sharif.surveyBackend.mgr.user;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import edu.sharif.surveyBackend.model.user.Role;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserMgr implements PanacheRepository<User> {
    public static User add(final String username, final String pass,
	    final List<Role> roles) {
	final User user = new User();
	user.name = username;
	user.pass = BcryptUtil.bcryptHash(pass);
	user.roles.addAll(roles);
	user.persist();
	return user;
    }

    public static User findByUsername(String username) {
	return User.find("name", username).firstResult();
    }

}
