package edu.sharif.surveyBackend.cli;

import java.time.OffsetDateTime;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import edu.sharif.surveyBackend.mgr.survey.SurveyMgr;
import edu.sharif.surveyBackend.mgr.survey.question.MultiChoiceQuestionMgr;
import edu.sharif.surveyBackend.mgr.university.DepartmentMgr;
import edu.sharif.surveyBackend.mgr.university.UniversityMgr;
import edu.sharif.surveyBackend.mgr.user.RoleMgr;
import edu.sharif.surveyBackend.mgr.user.UserMgr;
import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.question.MultiChoiceQuestion;
import edu.sharif.surveyBackend.model.university.Course;
import edu.sharif.surveyBackend.model.university.Department;
import edu.sharif.surveyBackend.model.university.RoleInCourse;
import edu.sharif.surveyBackend.model.university.Semester;
import edu.sharif.surveyBackend.model.university.University;
import edu.sharif.surveyBackend.model.university.UserCourseRelation;
import edu.sharif.surveyBackend.model.user.Role;
import edu.sharif.surveyBackend.model.user.User;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class DevStartup {

    @Inject
    UniversityMgr universityMgr;
    @Inject
    DepartmentMgr departmentMgr;

    @Inject
    MultiChoiceQuestionMgr multiChoiceQuestionMgr;
    @Inject
    SurveyMgr surveyMgr;

    @Inject
    RoleMgr roleMgr;

    @Transactional
    public void loadUsers(@Observes final StartupEvent evt) {
	// reset and load all test users
	roleMgr.deleteAll();
	Role roleAdmin = RoleMgr.add("admin");
	Role roleuser = RoleMgr.add("user");

	User.deleteAll();
	UserMgr.add("admin", "admin", List.of(roleAdmin));
	User u = UserMgr.add("user", "user", List.of(roleuser));

	University uni = this.universityMgr.newUniversity("sharif");
	Department dep = this.departmentMgr.newDepartment("math", uni);

	Semester sem = new Semester("98-2");
	Semester.persist(sem);

	Course course = new Course("algorithm", dep, sem);
	Course.persist(course);

	List<String> options = List.of("گزینه ی یک", "گزینه ی دو");
	MultiChoiceQuestion mcquestion = this.multiChoiceQuestionMgr
		.addMultiChoiceQuestion(options, 1, 1);

	mcquestion.persist();

	OffsetDateTime begining = OffsetDateTime.now();
	OffsetDateTime endDate = begining.plusHours(5);

	Survey survey = this.surveyMgr.newSurvey("نظر خواهی", course, begining,
		endDate, List.of(mcquestion));
	RoleInCourse roleInCourse = new RoleInCourse("استاد", course);
	roleInCourse.persist();
	UserCourseRelation ucr = new UserCourseRelation(u, roleInCourse);
	UserCourseRelation.persist(ucr);

    }
}
