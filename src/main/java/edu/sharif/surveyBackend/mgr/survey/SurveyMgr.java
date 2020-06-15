package edu.sharif.surveyBackend.mgr.survey;

import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.SurveyResponse;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SurveyMgr {

    // TODO should return survies that user have access to and also has been started
    // and not finished
    public static Survey[] availableSurveies(User user) {
        //language=HQL
        final String query = "SELECT serv FROM Survey AS serv WHERE serv.begging!=null AND " +
                "serv.endTime=null AND" +
                ":user in serv.course.students ";
        final Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        PanacheQuery<PanacheEntityBase> result = Survey.find(query, params);
        return (Survey[]) result.stream().toArray();
    }


    // TODO should return survies that user has access to and has been finished
    public static Survey[] oldSurveies(User user) {
        // TODO Auto-generated method stub
        //language=HQL
        final String query = "SELECT serv FROM Survey AS serv WHERE serv.endTime< :current AND " +
                ":user in serv.course.students ";
        final Map<String, Object> params = new HashMap<>();
        params.put("current", new Date());
        params.put("user", user);
        PanacheQuery<PanacheEntityBase> result = Survey.find(query, params);
        return (Survey[]) result.stream().toArray();
    }

    // TODO I'll do this
    public static void submit(User u,
                              @Valid SurveyResponse surveyResponse) {
        surveyResponse.persist();
    }
}
