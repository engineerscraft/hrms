package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Grade {

	private int gradeId;
	private String gradeName;
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", gradeName=" + gradeName + "]";
	}
}
