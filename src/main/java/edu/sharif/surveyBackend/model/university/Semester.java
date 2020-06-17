package edu.sharif.surveyBackend.model.university;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Cacheable
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Semester extends PanacheEntity {

    String name;

}
