package com.hamdard.hua.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Designation;

@Component
public class DesignationRepository {

	public List<Designation> getAllDesignations() {
		ArrayList<Designation> abc = new ArrayList<Designation>();
		abc.add(new Designation(1L,"XYZ"));
		abc.add(new Designation(2L,"WXY"));
		return abc;

	}
}
