package edu.sharif.surveyBackend.model.survey.question;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import edu.sharif.surveyBackend.model.survey.reply.MultiChoiceReply;
import edu.sharif.surveyBackend.model.survey.reply.RangedOptionReply;
import edu.sharif.surveyBackend.model.survey.reply.TextReply;
import edu.sharif.surveyBackend.model.university.Course;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Cacheable
@Getter
@Setter
@JsonIgnoreProperties(value = { "type" })
@JsonSubTypes({ @Type(name = "options", value = MultiChoiceQuestion.class),
	@Type(name = "maxLength", value = TextQuestion.class),
	@Type(name = "min", value = RangedOptionQuestion.class) })
public abstract class Question extends PanacheEntity {

    public static Question findByName(final String name) {
	return PanacheEntityBase.find("name", name).firstResult();
    }

    @ManyToOne
    Course course;

    String text;
}
