package edu.sharif.surveyBackend.model.university;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Cacheable
@Entity
@Data
public class Semester extends PanacheEntity {

    String name;

}
