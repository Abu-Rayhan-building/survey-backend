package edu.sharif.surveyBackend.model.survey.question;

import javax.persistence.Entity;

import edu.sharif.surveyBackend.model.survey.reply.MultiChoiceReply;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=true)
public class TextQuestion extends Question {

    public int maxLength;
    public int minLength;
}
