package edu.sharif.surveyBackend.model.survey.question;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import edu.sharif.surveyBackend.model.survey.reply.MultiChoiceReply;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=true)
public class MultiChoiceQuestion extends Question {

    @ElementCollection
    public List<String> options;

    public int minimunNumber;

    public int maximumNumber;

}
