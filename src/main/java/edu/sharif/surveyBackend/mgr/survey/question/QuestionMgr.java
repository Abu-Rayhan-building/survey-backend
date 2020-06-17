package edu.sharif.surveyBackend.mgr.survey.question;

import java.util.List;

import edu.sharif.surveyBackend.model.survey.question.MultiChoiceQuestion;
import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.survey.question.RangedOptionQuestion;
import edu.sharif.surveyBackend.model.survey.question.TextQuestion;
import edu.sharif.surveyBackend.model.survey.reply.MultiChoiceReply;
import edu.sharif.surveyBackend.model.survey.reply.RangedOptionReply;
import edu.sharif.surveyBackend.model.survey.reply.Reply;
import edu.sharif.surveyBackend.model.survey.reply.TextReply;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionMgr {
    public static Question findByName(final String name) {
	return Question.find("name", name).firstResult();
    }



    // TODO not all cases are considered
    public static boolean isReplyValid(final Question q, final Reply r) {
	if (!q.id.equals(r.question.id)) {
	    return false;
	}

	if (r instanceof MultiChoiceReply) {
	    if (!(q instanceof MultiChoiceQuestion)) {
		return false;
	    }
	    final var rr = (MultiChoiceReply) r;
	    final var qq = (MultiChoiceQuestion) q;
	    if (qq.minimunNumber > rr.getOptionIndex().size()
		    || qq.maximumNumber < rr.getOptionIndex().size()) {
		return false;
	    }
	} else if (r instanceof RangedOptionReply) {
	    if (!(q instanceof RangedOptionQuestion)) {
		return false;
	    }
	    final var rr = (RangedOptionReply) r;
	    final var qq = (RangedOptionQuestion) q;
	    if (rr.getValue() < qq.min || rr.getValue() > qq.max) {
		return false;
	    }
	} else {
	    if (!(q instanceof TextQuestion)) {
		return false;
	    }
	    final var rr = (TextReply) r;
	    final var qq = (TextQuestion) q;
	    if (rr.getText().length() < qq.minLength
		    || rr.getText().length() > qq.maxLength) {
		return false;
	    }
	}
	return true;

    }
}
