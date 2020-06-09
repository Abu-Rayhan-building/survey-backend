package edu.sharif.surveyBackend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Course extends PanacheEntity {

    @ManyToMany
    List<Profossor> instructors;

    @ManyToMany
    List<TeachingAssistance> teachingAssistances;
}
