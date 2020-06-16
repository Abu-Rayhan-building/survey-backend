package edu.sharif.surveyBackend.model.survey.question;

import javax.persistence.Entity;

@Entity
public class TextQuestion extends Question {

    public int maxLength;
    public int minLength;
}
