package edu.sharif.surveyBackend.model.university;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Cacheable
@Entity
@Data
@NoArgsConstructor
public class Course extends PanacheEntity {

    String name;

    @ManyToOne
    Semester semester;

    @ManyToOne
    Department department;

    @OneToMany
    List<UserCourseRelation> users;

    public Course(final String name, Department dep, final Semester semester) {
	this.name = name;
	this.department = dep;
	this.semester = semester;
	this.users = new ArrayList<>();
    }

}
