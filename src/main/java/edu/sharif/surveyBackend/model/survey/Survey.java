package edu.sharif.surveyBackend.model.survey;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.university.Course;
import edu.sharif.surveyBackend.model.university.RoleInCourse;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Survey extends PanacheEntity {

    String name;

    @ManyToMany
    List<Question> questions;

    OffsetDateTime begging;

    OffsetDateTime endTime;

    @ManyToOne
    Course course;

    @ManyToMany
    List<RoleInCourse> targetCourseRoles;

    public Survey(final String name, final Course course,
	    final OffsetDateTime begining, final OffsetDateTime endDate,
	    final List<Question> questions) {
	this.name = name;
	this.course = course;
	this.begging = begining;
	this.endTime = endDate;
	this.questions = questions;
    }

}
