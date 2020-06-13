package edu.sharif.surveyBackend.model.survey.reply;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.survey.question.RangedOptionQuestion;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Cacheable
public abstract class Reply extends PanacheEntity {

    @ManyToOne
    User responder;
    @ManyToOne
    Question question;

}
