package com.hamdard.hua.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hamdard.hua.model.College;

@Component
public class CollegeRepository {
	public List<College> getAllColleges() {
		ArrayList<College> abc = new ArrayList<College>();
		abc.add(new College(1L,"ABC"));
		abc.add(new College(2L,"XYZ"));
		return abc;
	}
}
