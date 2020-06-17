package edu.sharif.surveyBackend.mgr.survey;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

import edu.sharif.surveyBackend.mgr.survey.question.QuestionMgr;
import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.SurveyResponse;
import edu.sharif.surveyBackend.model.survey.question.MultiChoiceQuestion;
import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.survey.reply.Reply;
import edu.sharif.surveyBackend.model.university.Course;
import edu.sharif.surveyBackend.model.university.UserCourseRelation;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class SurveyMgr {

    public static Survey[] availableSurveies(final User user) {
	// language=HQL
	final String query = "SELECT serv FROM Survey AS serv WHERE serv.begging!=null AND "
		+ "serv.endTime=null AND" + ":user in serv.course.students ";
	final Map<String, Object> params = new HashMap<>();
	params.put("user", user);
	final PanacheQuery<Survey> result = Survey.find(query, params);
	return result.stream().toArray(Survey[]::new);
    }

    private static boolean hasAccess(final User u, final Survey survey) {
	final String query = "SELECT ucr FROM UserCourseRelation AS ucr WHERE serv.course = :current ";
	final Map<String, Object> params = new HashMap<>();
	params.put("current", survey.getCourse());
	final PanacheQuery<UserCourseRelation> result = UserCourseRelation
		.find(query, params);
	var roles = result.stream().filter(ucr -> {
	    if (((UserCourseRelation) ucr).getUser().equals(u))
		return true;
	    return false;
	}).collect(Collectors.toList());
	boolean[] flag = { false };

	roles.forEach(role -> {
	    if (survey.getTargetCourseRoles().contains(role))
		flag[0] = true;
	});

	return flag[0];

    }

    public static Survey[] oldSurveies(final User user) {
	// language=HQL
	final String query = "SELECT serv FROM Survey AS serv WHERE serv.endTime< :current AND "
		+ ":user in serv.course.students ";
	final Map<String, Object> params = new HashMap<>();
	params.put("current", new Date());
	params.put("user", user);
	final PanacheQuery<Survey> result = Survey.find(query, params);
	return result.stream().toArray(Survey[]::new);
    }

    public static void submit(final User u,
	    @Valid final SurveyResponse surveyResponse) {
	final Survey survey = Survey.findById(surveyResponse.getSurvey().id);

	if (SurveyMgr.hasAccess(u, survey)
		&& isValidResponse(survey, surveyResponse)) {
	    surveyResponse.persist();
	} else {
	    throw new BadRequestException("question type mismatch ");
	}

    }

    public static boolean isValidResponse(Survey survey,
	    @Valid SurveyResponse surveyResponse) {
	if (survey.getQuestions().size() != surveyResponse.replys.size())
	    return false;

	var itq = survey.getQuestions().iterator();
	var itr = surveyResponse.getReplys().iterator();

	while (itq.hasNext() && itr.hasNext()) {
	    Question q = itq.next();
	    Reply r = itr.next();
	    if (!QuestionMgr.isReplyValid(q, r))
		return false;

	}
	return true;
    }

    public static Survey newSurvey(String name, Course course,
	    OffsetDateTime begining, OffsetDateTime endDate,
	    List<Question> questions) {
	var survey = new Survey(name, course, begining, endDate, questions);
	survey.persist();
	return survey;
    }
}
