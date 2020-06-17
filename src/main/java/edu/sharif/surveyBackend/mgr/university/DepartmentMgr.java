package edu.sharif.surveyBackend.mgr.university;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.sharif.surveyBackend.model.university.Department;
import edu.sharif.surveyBackend.model.university.University;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

public class DepartmentMgr {

    public static Department newDepartment(String name, University uni) {
	var dep = new Department(name, uni);
	dep.persist();
	return dep;
    }

    public static Department deleteDepartment(int id) {
	Department dep = Department.findById(id);
	Department.deleteById(id);
	return dep;
    }

    public static Department getDepartmentByName(String name) {
	String query = "select dep from Department AS dep where dep.name = :name";
	final Map<String, Object> params = new HashMap<>();
	params.put("name", name);
	PanacheQuery<Department> dep = Department.find(query, params);
	return dep.firstResult();
    }
}
