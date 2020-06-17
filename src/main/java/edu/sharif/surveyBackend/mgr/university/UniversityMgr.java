package edu.sharif.surveyBackend.mgr.university;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import edu.sharif.surveyBackend.model.university.Department;
import edu.sharif.surveyBackend.model.university.University;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UniversityMgr implements PanacheRepository<University> {

    // TODO
    public List<Department> getDepartments(final University uni) {
	final String query = "SELECT dep FROM Department AS dep WHERE dep.university= :user ";
	final Map<String, Object> params = new HashMap<>();
	params.put("user", uni);

	final PanacheQuery<Department> result = Department.find(query,
		params);
	final var deps = result.stream().collect(Collectors.toList());

	return deps;
    }

    @Transactional
    public University newUniversity(final String name) {
	final var uni = new University(name);
	uni.persist();
	return uni;
    }

    @Transactional
    public void addDepatment(University uni, Department dep) {
	uni.getDepartments().add(dep);
	// TODO bug
	uni.flush();

    }

}
