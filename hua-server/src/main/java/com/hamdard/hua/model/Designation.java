package com.hamdard.hua.model;

public class Designation {

	private long designationId;
	private String designationName;
	public Designation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Designation(long designationId, String designationName) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
	}
	public long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	@Override
	public String toString() {
		return "DesignationName [designationId=" + designationId + ", designationName=" + designationName + "]";
	}
	
	
}
