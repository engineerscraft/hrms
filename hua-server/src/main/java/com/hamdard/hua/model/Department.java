package com.hamdard.hua.model;

public class Department {
	private long departentId;
	private String departmentName;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(long departentId, String departmentName) {
		super();
		this.departentId = departentId;
		this.departmentName = departmentName;
	}
	public long getDepartentId() {
		return departentId;
	}
	public void setDepartentId(long departentId) {
		this.departentId = departentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "Department [departentId=" + departentId + ", departmentName=" + departmentName + "]";
	}
	
	
}
