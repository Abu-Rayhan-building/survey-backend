package edu.sharif.surveyBackend.model.university;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Cacheable
@Entity
@Data
@NoArgsConstructor
public class Department extends PanacheEntity {

    public Department(String name, University uni) {
	this.name = name;

	this.university = uni;
    }

    String name;

    @ManyToOne
    University university;
}
