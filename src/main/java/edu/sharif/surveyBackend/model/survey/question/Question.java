package edu.sharif.surveyBackend.model.survey.question;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import edu.sharif.surveyBackend.model.university.Course;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Cacheable
@Getter
@Setter
public abstract class Question extends PanacheEntity {

    public static void deleteStefs() {
	PanacheEntityBase.delete("name", "Stef");
    }

    public static List<Question> findAlive() {
	return PanacheEntityBase.list("status");
    }

    public static Question findByName(final String name) {
	return PanacheEntityBase.find("name", name).firstResult();
    }

    @ManyToOne
    Course course;
}
