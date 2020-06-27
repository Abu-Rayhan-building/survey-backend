package edu.sharif.surveyBackend.api.graphql;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import edu.sharif.surveyBackend.mgr.survey.SurveyMgr;
import edu.sharif.surveyBackend.mgr.survey.question.MultiChoiceQuestionMgr;
import edu.sharif.surveyBackend.mgr.university.DepartmentMgr;
import edu.sharif.surveyBackend.mgr.university.UniversityMgr;
import edu.sharif.surveyBackend.model.university.Department;
import edu.sharif.surveyBackend.model.university.University;
import io.quarkus.security.Authenticated;

@GraphQLApi
@Authenticated
public class UniversityResource {

    @Inject
    UniversityMgr universityMgr;
    @Inject
    DepartmentMgr departmentMgr;

    @Inject
    MultiChoiceQuestionMgr multiChoiceQuestionMgr;
    @Inject
    SurveyMgr surveyMgr;

    @Mutation
    public Department createHero(final Department hero) {
	universityMgr.addDepatment(null, hero);
	return hero;
    }

    @Mutation
    public Department deleteHero(final int id) {
	return departmentMgr.deleteDepartment(id);
    }

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<University> getAllFilms() {
	return universityMgr.listAll();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public University getFilm(@Name("filmId") final long id) {
	return universityMgr.findById(id);
    }

    @Query
    public Department getHeroesWithSurname(
	    @DefaultValue("math") final String surname) {
	return departmentMgr.getDepartmentByName(surname);
    }

    public List<Department> heroes(@Source final University uni) {
	return universityMgr.getDepartments(uni);
    }
}