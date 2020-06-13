package edu.sharif.surveyBackend.model.university;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import edu.sharif.surveyBackend.model.user.User;
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
