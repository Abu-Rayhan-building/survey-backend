package edu.sharif.surveyBackend.model.university;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Cacheable
@Entity
@Data
@NoArgsConstructor
public class UserCourseRelation extends PanacheEntity {

    public UserCourseRelation(User u, RoleInCourse roleInCourse) {
	this.user = u;
	this.roleInCourse = roleInCourse;
    }

    @ManyToOne
    RoleInCourse roleInCourse;

    @ManyToOne
    User user;
}
