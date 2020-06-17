package edu.sharif.surveyBackend.model.survey.reply;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RangedOptionReply extends Reply {
    int value;

//    @ManyToOne
//    RangedOptionQuestion question;
}
