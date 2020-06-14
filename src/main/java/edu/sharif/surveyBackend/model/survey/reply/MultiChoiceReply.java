package edu.sharif.surveyBackend.model.survey.reply;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class MultiChoiceReply extends Reply {

    @ElementCollection
    List<Integer> optionIndex;

}
