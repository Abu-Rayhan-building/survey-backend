package edu.sharif.surveyBackend.model.survey;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

import edu.sharif.surveyBackend.model.survey.question.MultiChoiceQuestion;
import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.survey.question.RangedOptionQuestion;
import edu.sharif.surveyBackend.model.survey.question.TextQuestion;
import edu.sharif.surveyBackend.model.survey.reply.MultiChoiceReply;
import edu.sharif.surveyBackend.model.survey.reply.RangedOptionReply;
import edu.sharif.surveyBackend.model.survey.reply.Reply;
import edu.sharif.surveyBackend.model.survey.reply.TextReply;
import edu.sharif.surveyBackend.model.university.Course;
import edu.sharif.surveyBackend.model.university.RoleInCourse;
import edu.sharif.surveyBackend.model.university.UserCourseRelation;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Survey extends PanacheEntity {

    public Survey(String name, Course course, OffsetDateTime begining,
	    OffsetDateTime endDate, List<Question> questions) {
	this.name = name;
	this.course = course;
	this.begging = begining;
	this.endTime = endDate;
	this.questions = questions;
    }

    String name;

    @ManyToMany
    List<Question> questions;

    OffsetDateTime begging;

    OffsetDateTime endTime;

    @ManyToOne
    Course course;

    @ManyToMany
    List<RoleInCourse> targetCourseRoles;

}
