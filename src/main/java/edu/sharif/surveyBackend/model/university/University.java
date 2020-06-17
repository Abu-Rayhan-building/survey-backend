package edu.sharif.surveyBackend.model.university;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Cacheable
@Entity
@Data
@NoArgsConstructor
public class University extends PanacheEntity {

    String name;

    @OneToMany
    List<Department> departments;

    public University(final String name) {
	this.name = name;
    }
}
