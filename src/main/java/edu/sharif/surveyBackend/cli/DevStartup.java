package edu.sharif.surveyBackend.cli;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;
import edu.sharif.surveyBackend.mgr.survey.SurveyMgr;
import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.question.MultiChoiceQuestion;
import edu.sharif.surveyBackend.model.university.Course;
import edu.sharif.surveyBackend.model.university.RoleInCourse;
import edu.sharif.surveyBackend.model.university.Semester;
import edu.sharif.surveyBackend.model.university.UserCourseRelation;
import edu.sharif.surveyBackend.model.user.Role;
import edu.sharif.surveyBackend.model.user.User;

@Singleton
public class DevStartup {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
	// reset and load all test users
	Role.deleteAll();
	var roleAdmin = Role.add("admin");
	var roleuser = Role.add("user");

	User.deleteAll();
	User.add("admin", "admin", roleAdmin);
	User u = User.add("user", "user", roleuser);

	var sem = new Semester("98-2");
	Semester.persist(sem);

	var course = new Course("algorithm", sem);
	Course.persist(course);

	List<String> options = List.of("گزینه ی یک", "گزینه ی دو");
	var mcquestion = MultiChoiceQuestion.addMultiChoiceQuestion(options, 1,
		1);

	mcquestion.persist();

	OffsetDateTime begining = OffsetDateTime.now();
	var endDate = begining.plusHours(5);

	var survey = SurveyMgr.newSurvey("نظر خواهی", course, begining, endDate,
		List.of(mcquestion));
	RoleInCourse roleInCourse = new RoleInCourse("استاد", course);
	roleInCourse.persist();
	UserCourseRelation ucr = new UserCourseRelation(u, roleInCourse);
	UserCourseRelation.persist(ucr);

    }
}
