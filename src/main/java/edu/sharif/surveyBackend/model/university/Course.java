package edu.sharif.surveyBackend.model.university;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
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

    public Course(String name, Semester semester) {
	this.name = name;
	this.semester = semester;
	this.users = new ArrayList<UserCourseRelation>();
    }

    @OneToMany
    List<UserCourseRelation> users;

}
