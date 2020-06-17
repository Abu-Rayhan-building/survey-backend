package edu.sharif.surveyBackend.model.survey.reply;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class TextReply extends Reply {
    String text;
}
