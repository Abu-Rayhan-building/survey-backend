package edu.sharif.surveyBackend.model.survey.question;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
public class MultiChoiceQuestion extends Question {

    @ElementCollection
    List<String> options;

    int forcedNumber;

    int availableNumber;

    public MultiChoiceQuestion addMultiChoiceQuestion(
	    @Valid final MultiChoiceQuestion question) {
	final var q = new MultiChoiceQuestion();
	q.persist();
	return q;
    }

}
