package edu.sharif.surveyBackend.model.survey.reply;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.survey.question.RangedOptionQuestion;

@Entity
public class RangedOptionReply extends Reply {
    @Max(value = 100)
    @Min(value = 0)
    int value;

//    @ManyToOne
//    RangedOptionQuestion question;
}
