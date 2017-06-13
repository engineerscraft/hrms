package com.hamdard.hua.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Department;

@Component
public class DepartmentRepository {

	public List<Department> getAllDepartments() {
		ArrayList<Department> abc = new ArrayList<Department>();
		abc.add(new Department(1L, "PQR"));
		abc.add(new Department(2L, "MNP"));
		return abc;
	}
}
