package edu.sharif.surveyBackend.model.survey;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.university.Course;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Survey extends PanacheEntity {

    String name;

    @ManyToMany
    List<Question> questions;

    Date begging;

    Date endTime;

    @ManyToOne
    Course course;
}
