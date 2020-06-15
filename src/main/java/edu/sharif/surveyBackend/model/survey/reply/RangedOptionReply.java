package edu.sharif.surveyBackend.model.survey.reply;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class RangedOptionReply extends Reply {
    @Max(value = 100)
    @Min(value = 0)
    int value;

//    @ManyToOne
//    RangedOptionQuestion question;
}
