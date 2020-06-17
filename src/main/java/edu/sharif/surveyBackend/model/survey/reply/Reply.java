package edu.sharif.surveyBackend.model.survey.reply;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Cacheable
@JsonIgnoreProperties(value = { "type" })
@JsonSubTypes({ @Type(name = "optionIndex", value = MultiChoiceReply.class),
	@Type(name = "text", value = TextReply.class),
	@Type(name = "value", value = RangedOptionReply.class) })
public abstract class Reply extends PanacheEntity {

    @ManyToOne
    public User responder;
    @ManyToOne
    public Question question;

}
