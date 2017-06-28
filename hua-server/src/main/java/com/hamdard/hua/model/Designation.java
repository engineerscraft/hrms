package com.hamdard.hua.model;

public class Designation {

	private long designationId;
	private String designation;

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "DesignationName [designationId=" + designationId
				+ ", designation =" + designation + "]";
	}

}
