package edu.sharif.surveyBackend.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

@Data
@Entity
@Cacheable
public class Question extends PanacheEntity {

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

    public void addQuestion(@Valid final Question question) {
	final var q = new Question();
	q.persist();
    }

}
