package edu.sharif.surveyBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

@Data
@Entity
public class Profossor extends PanacheEntity {

    @NotBlank(message = "Name may not be blank")
    String name;
    
}
