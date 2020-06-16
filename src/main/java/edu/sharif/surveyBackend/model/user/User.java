package edu.sharif.surveyBackend.model.user;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@UserDefinition
@Table(name = "usertable")
public class User extends PanacheEntity {

    public static User add(final String username, final String pass,
	    final Role role) {
	final User user = new User();
	user.name = username;
	user.pass = BcryptUtil.bcryptHash(pass);
	user.roles.add(role);
	user.persist();
	return user;
    }

    @Username
    public String name;

    @Email
    public String email;

    OffsetDateTime signupDate;

    @Password
    public String pass;

    @ManyToMany
    @Roles
    public List<Role> roles = new ArrayList<>();

    public static User findByUsername(String username) {
	return find("name", username).firstResult();
    }

}
