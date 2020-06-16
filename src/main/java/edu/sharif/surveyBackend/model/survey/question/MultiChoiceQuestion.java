package edu.sharif.surveyBackend.model.survey.question;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
public class MultiChoiceQuestion extends Question {

    @ElementCollection
    List<String> options;

    public int minimunNumber;

    public int maximumNumber;

    public static MultiChoiceQuestion addMultiChoiceQuestion(
	    List<String> options, int min, int max) {
	final var q = new MultiChoiceQuestion();
	// TODO is this true? or should we copy the content?
	q.options = options;
	q.minimunNumber = min;
	q.maximumNumber = max;
	q.persist();
	return q;
    }

}
