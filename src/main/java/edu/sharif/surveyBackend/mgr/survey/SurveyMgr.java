package edu.sharif.surveyBackend.mgr.survey;

import javax.validation.Valid;

import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.SurveyResponse;
import edu.sharif.surveyBackend.model.user.User;

public class SurveyMgr {

    public static Survey[] availableSurveies(User user) {
	return null;
	// Survey.find(query, params)
    }

    public static Survey[] oldSurveies(User u) {
	// TODO Auto-generated method stub
	return null;
    }

    public static void oldSurveies(User u,
	    @Valid SurveyResponse surveyResponse) {
	surveyResponse.persist();
    }
}
