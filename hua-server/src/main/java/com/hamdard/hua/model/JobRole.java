package com.hamdard.hua.model;

public class JobRole {
	private int jobRoleId;
	private int orgId;
	private int gradeId;
	private int probationNoticePeriod;
	private int Noticeperiod;
	
	public JobRole(int jobRoleId, int orgId, int gradeId, int probationNoticePeriod, int noticeperiod) {
		super();
		this.jobRoleId = jobRoleId;
		this.orgId = orgId;
		this.gradeId = gradeId;
		this.probationNoticePeriod = probationNoticePeriod;
		Noticeperiod = noticeperiod;
	}
	
	
	public JobRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getJobRoleId() {
		return jobRoleId;
	}
	public void setJobRoleId(int jobRoleId) {
		this.jobRoleId = jobRoleId;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public int getProbationNoticePeriod() {
		return probationNoticePeriod;
	}
	public void setProbationNoticePeriod(int probationNoticePeriod) {
		this.probationNoticePeriod = probationNoticePeriod;
	}
	public int getNoticeperiod() {
		return Noticeperiod;
	}
	public void setNoticeperiod(int noticeperiod) {
		Noticeperiod = noticeperiod;
	}
	@Override
	public String toString() {
		return "JobRole [jobRoleId=" + jobRoleId + ", orgId=" + orgId + ", gradeId=" + gradeId
				+ ", probationNoticePeriod=" + probationNoticePeriod + ", Noticeperiod=" + Noticeperiod
				+ ", getJobRoleId()=" + getJobRoleId() + ", getOrgId()=" + getOrgId() + ", getGradeId()=" + getGradeId()
				+ ", getProbationNoticePeriod()=" + getProbationNoticePeriod() + ", getNoticeperiod()="
				+ getNoticeperiod() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
