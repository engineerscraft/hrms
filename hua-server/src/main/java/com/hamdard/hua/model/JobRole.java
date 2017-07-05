package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobRole {
    private int jobRoleId;
    private int orgId;
    private Grade grade;
    private int probationNoticePeriod;
    private int Noticeperiod;

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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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
        return "JobRole [jobRoleId=" + jobRoleId + ", orgId=" + orgId + ", grade=" + grade + ", probationNoticePeriod=" + probationNoticePeriod + ", Noticeperiod=" + Noticeperiod + "]";
    }

}
