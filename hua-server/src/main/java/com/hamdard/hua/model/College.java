package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class College {
    private long collegeId;
    private String collegeName;

    public College() {
        super();
        // TODO Auto-generated constructor stub
    }

    public College(long collegeId, String collegeName) {
        super();
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    public long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + "]";
    }

}
