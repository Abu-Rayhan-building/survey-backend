package edu.sharif.surveyBackend.model.survey.question;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class MultiChoiceQuestion extends Question {

    @ElementCollection
    public List<String> options;

    public int minimunNumber;

    public int maximumNumber;

}
