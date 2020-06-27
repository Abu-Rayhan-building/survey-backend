package edu.sharif.surveyBackend.mgr.university;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import edu.sharif.surveyBackend.model.university.Department;
import edu.sharif.surveyBackend.model.university.University;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class DepartmentMgr implements PanacheRepository<Department> {

    public Department deleteDepartment(final long id) {
	final Department dep = findById(id);
	deleteById(id);
	return dep;
    }

    public Department getDepartmentByName(final String name) {
	final String query = "select dep from Department AS dep where dep.name = :name";
	final Map<String, Object> params = new HashMap<>();
	params.put("name", name);
	PanacheQuery<Department> dep = find(query,
		params);
	return dep.firstResult();
    }

    public Department newDepartment(final String name, final University uni) {
	var dep = new Department(name, uni);
	dep.persist();
	return dep;
    }
}
