package edu.sharif.surveyBackend.mgr.survey;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

import edu.sharif.surveyBackend.mgr.survey.question.MultiChoiceQuestionMgr;
import edu.sharif.surveyBackend.mgr.survey.question.QuestionMgr;
import edu.sharif.surveyBackend.mgr.university.DepartmentMgr;
import edu.sharif.surveyBackend.mgr.university.UniversityMgr;
import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.SurveyResponse;
import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.survey.reply.Reply;
import edu.sharif.surveyBackend.model.university.Course;
import edu.sharif.surveyBackend.model.university.UserCourseRelation;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class SurveyMgr implements PanacheRepository<Survey> {
    @Inject
    UniversityMgr universityMgr;
    @Inject
    DepartmentMgr departmentMgr;

    @Inject
    MultiChoiceQuestionMgr multiChoiceQuestionMgr;

    @Inject
    QuestionMgr questionMgr;

    public Survey[] availableSurveies(final User user) {
	// language=HQL
	final String query = "SELECT serv FROM Survey AS serv WHERE serv.begging!=null AND "
		+ "serv.endTime=null AND" + ":user in serv.course.students ";
	final Map<String, Object> params = new HashMap<>();
	params.put("user", user);
	PanacheQuery<Survey> result = find(query, params);
	return result.stream().toArray(Survey[]::new);
    }

    private boolean hasAccess(final User u, final Survey survey) {
	final String query = "SELECT ucr FROM UserCourseRelation AS ucr WHERE serv.course = :current ";
	final Map<String, Object> params = new HashMap<>();
	params.put("current", survey.getCourse());
	final PanacheQuery<UserCourseRelation> result = UserCourseRelation
		.find(query, params);
	final var roles = result.stream().filter(ucr -> {
	    if (ucr.getUser().equals(u)) {
		return true;
	    }
	    return false;
	}).collect(Collectors.toList());
	final boolean[] flag = { false };

	roles.forEach(role -> {
	    if (survey.getTargetCourseRoles()
		    .contains(role.getRoleInCourse())) {
		flag[0] = true;
	    }
	});

	return flag[0];

    }

    public boolean isValidResponse(final Survey survey,
	    @Valid final SurveyResponse surveyResponse) {
	if (survey.getQuestions().size() != surveyResponse.replys.size()) {
	    return false;
	}

	final var itq = survey.getQuestions().iterator();
	final var itr = surveyResponse.getReplys().iterator();

	while (itq.hasNext() && itr.hasNext()) {
	    final Question q = itq.next();
	    final Reply r = itr.next();
	    if (!questionMgr.isReplyValid(q, r)) {
		return false;
	    }

	}
	return true;
    }

    public Survey newSurvey(final String name, final Course course,
	    final OffsetDateTime begining, final OffsetDateTime endDate,
	    final List<Question> questions) {
	final var survey = new Survey(name, course, begining, endDate,
		questions);
	survey.persist();
	return survey;
    }

    public Survey[] oldSurveies(final User user) {
	// language=HQL
	final String query = "SELECT serv FROM Survey AS serv WHERE serv.endTime< :current AND "
		+ ":user in serv.course.students ";
	Map<String, Object> params = new HashMap<>();
	params.put("current", new Date());
	params.put("user", user);
	PanacheQuery<Survey> result = find(query, params);
	return result.stream().toArray(Survey[]::new);
    }

    public void submit(final User u,
	    @Valid final SurveyResponse surveyResponse) {
	final Survey survey = findById(surveyResponse.getSurvey().id);

	if (this.hasAccess(u, survey)
		&& this.isValidResponse(survey, surveyResponse)) {
	    surveyResponse.persist();
	} else {
	    throw new BadRequestException("question type mismatch ");
	}

    }
}
