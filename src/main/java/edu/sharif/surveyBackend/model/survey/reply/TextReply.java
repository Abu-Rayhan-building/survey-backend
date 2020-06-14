package edu.sharif.surveyBackend.model.survey.reply;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class TextReply extends Reply {
    String text;
}
