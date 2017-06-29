/**
 * 
 */
package com.hamdard.hua.model;

/**
 * @author Biswajit
 *
 */
public class EmployeeAdditionalDetails {
    private int dependentNo;
    private String emergencyContactName;
    private String emergencyContactNo;
    private String medicalReportComment;
    private String nomineeName1;
    private String nomineeName2;
    private String nomineeName3;
    private double nomineeShare1;
    private double nomineeShare2;
    private double nomineeShare3;
    private String preMedicalCheckUpDate;
    private int siblingNo;

    /**
     * @return the dependentNo
     */
    public int getDependentNo() {
        return dependentNo;
    }

    /**
     * @param dependentNo the dependentNo to set
     */
    public void setDependentNo(int dependentNo) {
        this.dependentNo = dependentNo;
    }

    /**
     * @return the emergencyContactName
     */
    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    /**
     * @param emergencyContactName the emergencyContactName to set
     */
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    /**
     * @return the emergencyContactNo
     */
    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    /**
     * @param emergencyContactNo the emergencyContactNo to set
     */
    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    /**
     * @return the medicalReportComment
     */
    public String getMedicalReportComment() {
        return medicalReportComment;
    }

    /**
     * @param medicalReportComment the medicalReportComment to set
     */
    public void setMedicalReportComment(String medicalReportComment) {
        this.medicalReportComment = medicalReportComment;
    }

    /**
     * @return the nomineeName1
     */
    public String getNomineeName1() {
        return nomineeName1;
    }

    /**
     * @param nomineeName1 the nomineeName1 to set
     */
    public void setNomineeName1(String nomineeName1) {
        this.nomineeName1 = nomineeName1;
    }

    /**
     * @return the nomineeName2
     */
    public String getNomineeName2() {
        return nomineeName2;
    }

    /**
     * @param nomineeName2 the nomineeName2 to set
     */
    public void setNomineeName2(String nomineeName2) {
        this.nomineeName2 = nomineeName2;
    }

    /**
     * @return the nomineeName3
     */
    public String getNomineeName3() {
        return nomineeName3;
    }

    /**
     * @param nomineeName3 the nomineeName3 to set
     */
    public void setNomineeName3(String nomineeName3) {
        this.nomineeName3 = nomineeName3;
    }

    /**
     * @return the nomineeShare1
     */
    public double getNomineeShare1() {
        return nomineeShare1;
    }

    /**
     * @param nomineeShare1 the nomineeShare1 to set
     */
    public void setNomineeShare1(double nomineeShare1) {
        this.nomineeShare1 = nomineeShare1;
    }

    /**
     * @return the nomineeShare2
     */
    public double getNomineeShare2() {
        return nomineeShare2;
    }

    /**
     * @param nomineeShare2 the nomineeShare2 to set
     */
    public void setNomineeShare2(double nomineeShare2) {
        this.nomineeShare2 = nomineeShare2;
    }

    /**
     * @return the nomineeShare3
     */
    public double getNomineeShare3() {
        return nomineeShare3;
    }

    /**
     * @param nomineeShare3 the nomineeShare3 to set
     */
    public void setNomineeShare3(double nomineeShare3) {
        this.nomineeShare3 = nomineeShare3;
    }

    /**
     * @return the preMedicalCheckUpDate
     */
    public String getPreMedicalCheckUpDate() {
        return preMedicalCheckUpDate;
    }

    /**
     * @param preMedicalCheckUpDate the preMedicalCheckUpDate to set
     */
    public void setPreMedicalCheckUpDate(String preMedicalCheckUpDate) {
        this.preMedicalCheckUpDate = preMedicalCheckUpDate;
    }

    /**
     * @return the siblingNo
     */
    public int getSiblingNo() {
        return siblingNo;
    }

    /**
     * @param siblingNo the siblingNo to set
     */
    public void setSiblingNo(int siblingNo) {
        this.siblingNo = siblingNo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmployeeAdditionalDetails [dependentNo=" + dependentNo + ", emergencyContactName=" + emergencyContactName + ", emergencyContactNo=" + emergencyContactNo + ", medicalReportComment=" + medicalReportComment + ", nomineeName1=" + nomineeName1
                + ", nomineeName2=" + nomineeName2 + ", nomineeName3=" + nomineeName3 + ", nomineeShare1=" + nomineeShare1 + ", nomineeShare2=" + nomineeShare2 + ", nomineeShare3=" + nomineeShare3 + ", preMedicalCheckUpDate=" + preMedicalCheckUpDate
                + ", siblingNo=" + siblingNo + "]";
    }

}
