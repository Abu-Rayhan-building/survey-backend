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
public class Course extends PanacheEntity {

    String name;
    
    @ManyToOne
    Semester semester;

    @ManyToMany
    List<User> instructors;

    @ManyToMany
    List<User> teachingAssistances;

    @ManyToMany
    List<User> students;
}
