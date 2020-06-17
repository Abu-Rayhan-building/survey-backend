package edu.sharif.surveyBackend.model.survey;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import edu.sharif.surveyBackend.model.survey.reply.Reply;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class SurveyResponse extends PanacheEntity {

    @ManyToOne
    Survey survey;

    @ManyToMany
    public List<Reply> replys;

    OffsetDateTime submitTime;

}
