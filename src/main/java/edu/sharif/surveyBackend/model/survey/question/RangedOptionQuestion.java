package edu.sharif.surveyBackend.model.survey.question;

import javax.persistence.Entity;

@Entity
public class RangedOptionQuestion extends Question {

  public int min;
  public int max;
}
