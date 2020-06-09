package edu.sharif.surveyBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Student extends PanacheEntity {
}
