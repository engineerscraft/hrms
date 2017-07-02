package com.hamdard.hua.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jyotirmoy Banerjee
 * /api/#_employee
 */
@XmlRootElement
public class Employee {
    
    private EmployeeAddlDetails           employeeAddlDetails;
    private List<EmployeeAddress>         employeeAddress;
    private EmployeeBasicInfo             employeeBasicInfo;
    private EmployeeHierarchy             employeeHierarchy;
    private List<EmployeeOptionalBenefit> employeeOptionalBenefit;
    private EmployeeProfile               employeeProfile;
    private List<EmployeeSalary>          employeeSalary;
    
    
    
    public static class EmployeeSalary {
        private SalaryComponent salaryComponent;
        private double          salaryValue;
        
        public SalaryComponent getSalaryComponent() {
            return this.salaryComponent;
        }
        public void setSalaryComponent(SalaryComponent salaryComponent) {
            this.salaryComponent = salaryComponent;
        }
        public double getSalaryValue() {
            return this.salaryValue;
        }
        public void setSalaryValue(double salaryValue) {
            this.salaryValue = salaryValue;
        }
        @Override
        public String toString() {
            return "EmployeeSalary [salaryComponent=" + this.salaryComponent + ", salaryValue=" + this.salaryValue
                    + "]";
        }
        
        
    }
    
    public static class EmployeeProfile {
        private String comments;
        private String description;
        private String qualification;
        
        public String getComments() {
            return this.comments;
        }
        public void setComments(String comments) {
            this.comments = comments;
        }
        public String getDescription() {
            return this.description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getQualification() {
            return this.qualification;
        }
        public void setQualification(String qualification) {
            this.qualification = qualification;
        }
        @Override
        public String toString() {
            return "EmployeeProfile [comments=" + this.comments + ", description=" + this.description
                    + ", qualification=" + this.qualification + "]";
        }
        
    }
    
    public static class EmployeeOptionalBenefit {
        private double             benefitValue;
        private int                frequency;
        private int                iterations;
        private Date               nextDueDate;
        private SalaryOptComponent optSalaryComponent;
        private Date               startDate;
        private Date               stopDate;
        private String             remarks;
        
        public double getBenefitValue() {
            return this.benefitValue;
        }
        public void setBenefitValue(double benefitValue) {
            this.benefitValue = benefitValue;
        }
        public int getFrequency() {
            return this.frequency;
        }
        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }
        public int getIterations() {
            return this.iterations;
        }
        public void setIterations(int iterations) {
            this.iterations = iterations;
        }
        public Date getNextDueDate() {
            return this.nextDueDate;
        }
        public void setNextDueDate(Date nextDueDate) {
            this.nextDueDate = nextDueDate;
        }
        public SalaryOptComponent getOptSalaryComponent() {
            return this.optSalaryComponent;
        }
        public void setOptSalaryComponent(SalaryOptComponent optSalaryComponent) {
            this.optSalaryComponent = optSalaryComponent;
        }
        public Date getStartDate() {
            return this.startDate;
        }
        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
        public Date getStopDate() {
            return this.stopDate;
        }
        public void setStopDate(Date stopDate) {
            this.stopDate = stopDate;
        }
        public String getRemarks() {
            return remarks;
        }
        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
        @Override
        public String toString() {
            return "EmployeeOptionalBenefit [benefitValue=" + this.benefitValue + ", frequency=" + this.frequency
                    + ", iterations=" + this.iterations + ", nextDueDate=" + this.nextDueDate + ", optSalaryComponent="
                    + this.optSalaryComponent + ", startDate=" + this.startDate + ", stopDate=" + this.stopDate + "]";
        }
        
    }
    
    public class EmployeeHierarchy {
        private int    cl;                    // the number of CLs of the employee
        private String hrId;
        private int    maternityLeave;
        private Date   noticePeriodEndDate;
        private int    paternityLeave;
        private int    pl;                    // paid leaves for the employee
        private Date   probationPeriodEndDate;
        private int    specialLeave;
        private String status;
        private String supervisorId;
        private int    sickLeave;
        public int getCl() {
            return this.cl;
        }
        public void setCl(int cl) {
            this.cl = cl;
        }
        public String getHrId() {
            return this.hrId;
        }
        public void setHrId(String hrId) {
            this.hrId = hrId;
        }
        public int getMaternityLeave() {
            return this.maternityLeave;
        }
        public void setMaternityLeave(int maternityLeave) {
            this.maternityLeave = maternityLeave;
        }
        public Date getNoticePeriodEndDate() {
            return this.noticePeriodEndDate;
        }
        public void setNoticePeriodEndDate(Date noticePeriodEndDate) {
            this.noticePeriodEndDate = noticePeriodEndDate;
        }
        public int getPaternityLeave() {
            return this.paternityLeave;
        }
        public void setPaternityLeave(int paternityLeave) {
            this.paternityLeave = paternityLeave;
        }
        public int getPl() {
            return this.pl;
        }
        public void setPl(int pl) {
            this.pl = pl;
        }
        public Date getProbationPeriodEndDate() {
            return this.probationPeriodEndDate;
        }
        public void setProbationPeriodEndDate(Date probationPeriodEndDate) {
            this.probationPeriodEndDate = probationPeriodEndDate;
        }
        public int getSpecialLeave() {
            return this.specialLeave;
        }
        public void setSpecialLeave(int specialLeave) {
            this.specialLeave = specialLeave;
        }
        public String getStatus() {
            return this.status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public String getSupervisorId() {
            return this.supervisorId;
        }
        public void setSupervisorId(String supervisorId) {
            this.supervisorId = supervisorId;
        }
        @Override
        public String toString() {
            return "EmployeeHierarchy [cl=" + this.cl + ", hrId=" + this.hrId + ", maternityLeave="
                    + this.maternityLeave + ", noticePeriodEndDate=" + this.noticePeriodEndDate + ", paternityLeave="
                    + this.paternityLeave + ", pl=" + this.pl + ", probationPeriodEndDate="
                    + this.probationPeriodEndDate + ", specialLeave=" + this.specialLeave + ", status=" + this.status
                    + ", supervisorId=" + this.supervisorId + "]";
        }
        public int getSickLeave() {
            return this.sickLeave;
        }
        public void setSickLeave(int sickLeave) {
            this.sickLeave = sickLeave;
        }
    }
    
    public static class EmployeeAddress {
        private String area;
        private int    countryId;
        private String countryName;
        private String description;
        private int    districtId;
        private String districtName;
        private String houseNo;
        private String pinno;
        private String region;
        private int    stateId;
        private String stateName;
        private String streetName;
        private String addressType;
        
        public String getArea() {
            return this.area;
        }
        public void setArea(String area) {
            this.area = area;
        }
        public int getCountryId() {
            return this.countryId;
        }
        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }
        public String getCountryName() {
            return this.countryName;
        }
        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
        public String getDescription() {
            return this.description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public int getDistrictId() {
            return this.districtId;
        }
        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }
        public String getDistrictName() {
            return this.districtName;
        }
        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }
        public String getHouseNo() {
            return this.houseNo;
        }
        public void setHouseNo(String houseNo) {
            this.houseNo = houseNo;
        }
        public String getPinno() {
            return this.pinno;
        }
        public void setPinno(String pinno) {
            this.pinno = pinno;
        }
        public String getRegion() {
            return this.region;
        }
        public void setRegion(String region) {
            this.region = region;
        }
        public int getStateId() {
            return this.stateId;
        }
        public void setStateId(int stateId) {
            this.stateId = stateId;
        }
        public String getStateName() {
            return this.stateName;
        }
        public void setStateName(String stateName) {
            this.stateName = stateName;
        }
        public String getStreetName() {
            return this.streetName;
        }
        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }
        @Override
        public String toString() {
            return "EmployeeAddress [area=" + this.area + ", countryId=" + this.countryId + ", countryName="
                    + this.countryName + ", description=" + this.description + ", districtId=" + this.districtId
                    + ", districtName=" + this.districtName + ", houseNo=" + this.houseNo + ", pinno=" + this.pinno
                    + ", region=" + this.region + ", stateId=" + this.stateId + ", stateName=" + this.stateName
                    + ", streetName=" + this.streetName + "]";
        }
        public String getAddressType() {
            return this.addressType;
        }
        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }
    }
    
    public static class EmployeeAddlDetails {
        private int    dependentNo;
        private String emergencyContactName;
        private String emergencyContactNo;
        private String medicalReportComment;
        private String nomineeName1;
        private String nomineeName2;
        private String nomineeName3;
        private double nomineeShare1;
        private double nomineeShare2;
        private double nomineeShare3;
        private Date   preMedicalCheckUpDate;
        private int    siblingNo;
        public int getDependentNo() {
            return this.dependentNo;
        }
        public void setDependentNo(int dependentNo) {
            this.dependentNo = dependentNo;
        }
        public String getEmergencyContactName() {
            return this.emergencyContactName;
        }
        public void setEmergencyContactName(String emergencyContactName) {
            this.emergencyContactName = emergencyContactName;
        }
        public String getEmergencyContactNo() {
            return this.emergencyContactNo;
        }
        public void setEmergencyContactNo(String emergencyContactNo) {
            this.emergencyContactNo = emergencyContactNo;
        }
        public String getMedicalReportComment() {
            return this.medicalReportComment;
        }
        public void setMedicalReportComment(String medicalReportComment) {
            this.medicalReportComment = medicalReportComment;
        }
        public String getNomineeName1() {
            return this.nomineeName1;
        }
        public void setNomineeName1(String nomineeName1) {
            this.nomineeName1 = nomineeName1;
        }
        public String getNomineeName2() {
            return this.nomineeName2;
        }
        public void setNomineeName2(String nomineeName2) {
            this.nomineeName2 = nomineeName2;
        }
        public String getNomineeName3() {
            return this.nomineeName3;
        }
        public void setNomineeName3(String nomineeName3) {
            this.nomineeName3 = nomineeName3;
        }
        public Date getPreMedicalCheckUpDate() {
            return this.preMedicalCheckUpDate;
        }
        public void setPreMedicalCheckUpDate(Date preMedicalCheckUpDate) {
            this.preMedicalCheckUpDate = preMedicalCheckUpDate;
        }
        public int getSiblingNo() {
            return this.siblingNo;
        }
        public void setSiblingNo(int siblingNo) {
            this.siblingNo = siblingNo;
        }
        public double getNomineeShare1() {
            return this.nomineeShare1;
        }
        public void setNomineeShare1(double nomineeShare1) {
            this.nomineeShare1 = nomineeShare1;
        }
        public double getNomineeShare2() {
            return this.nomineeShare2;
        }
        public void setNomineeShare2(double nomineeShare2) {
            this.nomineeShare2 = nomineeShare2;
        }
        public double getNomineeShare3() {
            return this.nomineeShare3;
        }
        public void setNomineeShare3(double nomineeShare3) {
            this.nomineeShare3 = nomineeShare3;
        }
        @Override
        public String toString() {
            return "EmployeeAddlDetails [dependentNo=" + this.dependentNo + ", emergencyContactName="
                    + this.emergencyContactName + ", emergencyContactNo=" + this.emergencyContactNo
                    + ", medicalReportComment=" + this.medicalReportComment + ", nomineeName1=" + this.nomineeName1
                    + ", nomineeName2=" + this.nomineeName2 + ", nomineeName3=" + this.nomineeName3 + ", nomineeShare1="
                    + this.nomineeShare1 + ", nomineeShare2=" + this.nomineeShare2 + ", nomineeShare3="
                    + this.nomineeShare3 + ", preMedicalCheckUpDate=" + this.preMedicalCheckUpDate + ", siblingNo="
                    + this.siblingNo + "]";
        }
    }
    
    public class EmployeeBasicInfo {
        private String     contactNo;
        private Department department;
        private Date       dob;
        private Date       doj;
        private String     emailId;
        private String     empFirstName;
        private String     empId;
        private String     empLastName;
        private String     empMiddleName;
        private String     empType;
        private String     entryBy;
        private Date       entryDate;
        private String     fatherName;
        private boolean    hrFlag;
        private DocType    identityDocType;
        private String     identityNumber;
        private String     maritalStatus;
        private String     nationality;
        private String     organization;
        private String     sex;
        private boolean    supervisorFlag;
        private String     title;
        private Unit       unit;
        
        public String getContactNo() {
            return this.contactNo;
        }
        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }
        public Department getDepartment() {
            return this.department;
        }
        public void setDepartment(Department department) {
            this.department = department;
        }
        public String getEmailId() {
            return this.emailId;
        }
        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }
        public String getEmpFirstName() {
            return this.empFirstName;
        }
        public void setEmpFirstName(String empFirstName) {
            this.empFirstName = empFirstName;
        }
        public String getEmpId() {
            return this.empId;
        }
        public void setEmpId(String empId) {
            this.empId = empId;
        }
        public String getEmpLastName() {
            return this.empLastName;
        }
        public void setEmpLastName(String empLastName) {
            this.empLastName = empLastName;
        }
        public String getEmpMiddleName() {
            return this.empMiddleName;
        }
        public void setEmpMiddleName(String empMiddleName) {
            this.empMiddleName = empMiddleName;
        }
        public String getEmpType() {
            return this.empType;
        }
        public void setEmpType(String empType) {
            this.empType = empType;
        }
        public String getEntryBy() {
            return this.entryBy;
        }
        public void setEntryBy(String entryBy) {
            this.entryBy = entryBy;
        }
        public String getFatherName() {
            return this.fatherName;
        }
        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }
        public boolean isHrFlag() {
            return this.hrFlag;
        }
        public void setHrFlag(boolean hrFlag) {
            this.hrFlag = hrFlag;
        }
        public DocType getIdentityDocType() {
            return this.identityDocType;
        }
        public void setIdentityDocType(DocType identityDocType) {
            this.identityDocType = identityDocType;
        }
        public String getIdentityNumber() {
            return this.identityNumber;
        }
        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }
        public String getMaritalStatus() {
            return this.maritalStatus;
        }
        public void setMaritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
        }
        public String getNationality() {
            return this.nationality;
        }
        public void setNationality(String nationality) {
            this.nationality = nationality;
        }
        public String getOrganization() {
            return this.organization;
        }
        public void setOrganization(String organization) {
            this.organization = organization;
        }
        public String getSex() {
            return this.sex;
        }
        public void setSex(String sex) {
            this.sex = sex;
        }
        public boolean isSupervisorFlag() {
            return this.supervisorFlag;
        }
        public void setSupervisorFlag(boolean supervisorFlag) {
            this.supervisorFlag = supervisorFlag;
        }
        public String getTitle() {
            return this.title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public Unit getUnit() {
            return this.unit;
        }
        public void setUnit(Unit unit) {
            this.unit = unit;
        }
        public Date getDob() {
            return this.dob;
        }
        public void setDob(Date dob) {
            this.dob = dob;
        }
        public Date getDoj() {
            return this.doj;
        }
        public void setDoj(Date doj) {
            this.doj = doj;
        }
        public Date getEntryDate() {
            return this.entryDate;
        }
        public void setEntryDate(Date entryDate) {
            this.entryDate = entryDate;
        }
        
        
    }
    
    public static class EmployeeSearchResult {
        
        private String empId;
        private String name;
        private String emailId; 
        private String departmentName;
        private String designation;
        private String contactNo;
        
        public String getEmpId() {
            return empId;
        }
        public void setEmpId(String empId) {
            this.empId = empId;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmailId() {
            return emailId;
        }
        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }
        public String getDepartmentName() {
            return departmentName;
        }
        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }
        public String getDesignation() {
            return designation;
        }
        public void setDesignation(String designation) {
            this.designation = designation;
        }
        public String getContactNo() {
            return contactNo;
        }
        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }
        @Override
        public String toString() {
            return "EmployeeSearchResult [empId=" + empId + ", name=" + name + ", emailId=" + emailId + ", departmentName=" + departmentName + ", designation=" + designation + ", contactNo=" + contactNo + "]";
        }
    }

    public EmployeeAddlDetails getEmployeeAddlDetails() {
        return this.employeeAddlDetails;
    }

    public void setEmployeeAddlDetails(EmployeeAddlDetails employeeAddlDetails) {
        this.employeeAddlDetails = employeeAddlDetails;
    }

    public EmployeeBasicInfo getEmployeeBasicInfo() {
        return this.employeeBasicInfo;
    }

    public void setEmployeeBasicInfo(EmployeeBasicInfo employeeBasicInfo) {
        this.employeeBasicInfo = employeeBasicInfo;
    }

    public EmployeeHierarchy getEmployeeHierarchy() {
        return this.employeeHierarchy;
    }

    public void setEmployeeHierarchy(EmployeeHierarchy employeeHierarchy) {
        this.employeeHierarchy = employeeHierarchy;
    }

    public EmployeeProfile getEmployeeProfile() {
        return this.employeeProfile;
    }

    public void setEmployeeProfile(EmployeeProfile employeeProfile) {
        this.employeeProfile = employeeProfile;
    }
    
    public List<EmployeeAddress> getEmployeeAddress() {
        return this.employeeAddress;
    }

    public void setEmployeeAddress(List<EmployeeAddress> employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
    
    public List<EmployeeOptionalBenefit> getEmployeeOptionalBenefit() {
        return this.employeeOptionalBenefit;
    }

    public void setEmployeeOptionalBenefit(List<EmployeeOptionalBenefit> employeeOptionalBenefit) {
        this.employeeOptionalBenefit = employeeOptionalBenefit;
    }

    public List<EmployeeSalary> getEmployeeSalary() {
        return this.employeeSalary;
    }

    public void setEmployeeSalary(List<EmployeeSalary> employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @Override
    public String toString() {
        return "Employee [employeeAddlDetails=" + this.employeeAddlDetails + ", employeeAddress=" + this.employeeAddress
                + ", employeeBasicDetails=" + this.employeeBasicInfo + ", employeeHierarchy="
                + this.employeeHierarchy + ", employeeOptionalBenefit=" + this.employeeOptionalBenefit
                + ", employeeProfile=" + this.employeeProfile + ", employeeSalary=" + this.employeeSalary + "]";
    }

}

