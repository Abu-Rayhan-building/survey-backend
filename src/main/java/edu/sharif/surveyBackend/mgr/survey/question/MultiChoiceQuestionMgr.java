package edu.sharif.surveyBackend.mgr.survey.question;

import java.util.List;

import edu.sharif.surveyBackend.model.survey.question.MultiChoiceQuestion;

public class MultiChoiceQuestionMgr {
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
