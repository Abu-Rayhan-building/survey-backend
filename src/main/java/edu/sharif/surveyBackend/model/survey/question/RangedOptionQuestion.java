package edu.sharif.surveyBackend.model.survey.question;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class RangedOptionQuestion extends Question {

  int min;
  int max;
}
