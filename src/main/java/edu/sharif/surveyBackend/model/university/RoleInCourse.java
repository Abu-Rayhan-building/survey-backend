package edu.sharif.surveyBackend.model.university;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Cacheable
@Entity
@Data
@NoArgsConstructor
public class RoleInCourse extends PanacheEntity {

    String name;

    @ManyToOne
    Course course;

    public RoleInCourse(String string, Course course) {
	this.name = string;
	this.course = course;
    }

}
