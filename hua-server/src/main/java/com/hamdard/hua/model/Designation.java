package com.hamdard.hua.model;

public class Designation {

    private long designationId;
    private String designationName;

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
        return "Designation [designationId=" + designationId + ", designationName=" + designationName + "]";
    }

}
