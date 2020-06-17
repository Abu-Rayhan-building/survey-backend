package edu.sharif.surveyBackend.api.graphql;

import java.util.List;

import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import edu.sharif.surveyBackend.mgr.university.DepartmentMgr;
import edu.sharif.surveyBackend.mgr.university.UniversityMgr;
import edu.sharif.surveyBackend.model.university.Department;
import edu.sharif.surveyBackend.model.university.University;

@GraphQLApi
public class UniversityResource {

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<University> getAllFilms() {
	return University.listAll();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public University getFilm(@Name("filmId") final int id) {
	return University.findById(id);
    }

    public List<Department> heroes(@Source final University uni) {
	return UniversityMgr.getDepartments(uni);
    }

    @Mutation
    public Department createHero(Department hero) {
	UniversityMgr.addDepatment(null, hero);
	return hero;
    }

    @Mutation
    public Department deleteHero(int id) {
	return DepartmentMgr.deleteDepartment(id);
    }

    @Query
    public Department getHeroesWithSurname(
	    @DefaultValue("math") String surname) {
	return DepartmentMgr.getDepartmentByName(surname);
    }
}