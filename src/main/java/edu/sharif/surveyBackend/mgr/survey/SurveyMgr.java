package edu.sharif.surveyBackend.mgr.survey;

import javax.validation.Valid;

import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.SurveyResponse;
import edu.sharif.surveyBackend.model.user.User;

public class SurveyMgr {

    // TODO should return survies that user have access to and also has been started
    // and not finished
    public static Survey[] availableSurveies(User user) {
	return null;
	// Survey.find(query, params)
    }

    
    // TODO should return survies that user has access to and has been finished
    public static Survey[] oldSurveies(User u) {
	// TODO Auto-generated method stub
	return null;
    }

    // TODO I'll do this
    public static void submit(User u,
	    @Valid SurveyResponse surveyResponse) {
	surveyResponse.persist();
    }
}
