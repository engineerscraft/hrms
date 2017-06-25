package com.hamdard.hua.model;

import java.util.Date;

/**
 * @author Jyotirmoy Banerjee
 * /api/#_employee
 */

public class Employee {
    
    private EmployeeAddlDetails     employeeAddlDetails;
    private EmployeeAddress         employeeAddress;
    private EmployeeBasicInfo       employeeBasicDetails;
    private EmployeeHierarchy       employeeHierarchy;
    private EmployeeOptionalBenefit employeeOptionalBenefit;
    private EmployeeProfile         employeeProfile;
    private EmployeeSalary          employeeSalary;
    
    
    
    public class EmployeeSalary {
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
        
        
    }
    
    public class EmployeeProfile {
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
        
    }
    
    public class EmployeeOptionalBenefit {
        private double             benefitValue;
        private int                frequency;
        private int                iterations;
        private Date               nextDueDate;
        private SalaryOptComponent optSalaryComponent;
        private Date               startDate;
        private Date               stopDate;
        
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
    }
    
    public class EmployeeAddress {
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
    }
    
    public class EmployeeAddlDetails {
        private int    dependentNo;
        private String emergencyContactName;
        private String emergencyContactNo;
        private String medicalReportComment;
        private String nomineeName1;
        private String nomineeName2;
        private String nomineeName3;
        private float  nomineeShare1;
        private float  nomineeShare2;
        private float  nomineeShare3;
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
        public float getNomineeShare1() {
            return this.nomineeShare1;
        }
        public void setNomineeShare1(float nomineeShare1) {
            this.nomineeShare1 = nomineeShare1;
        }
        public float getNomineeShare2() {
            return this.nomineeShare2;
        }
        public void setNomineeShare2(float nomineeShare2) {
            this.nomineeShare2 = nomineeShare2;
        }
        public float getNomineeShare3() {
            return this.nomineeShare3;
        }
        public void setNomineeShare3(float nomineeShare3) {
            this.nomineeShare3 = nomineeShare3;
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
    }
    
    public class EmployeeBasicInfo {
        private String     contactNo;
        private Department department;
        private String     dob;
        private String     doj;
        private String     emailId;
        private String     empFirstName;
        private String     empId;
        private String     empLastName;
        private String     empMiddleName;
        private String     empType;
        private String     entryBy;
        private String     entryDate;
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
        public String getDob() {
            return this.dob;
        }
        public void setDob(String dob) {
            this.dob = dob;
        }
        public String getDoj() {
            return this.doj;
        }
        public void setDoj(String doj) {
            this.doj = doj;
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
        public String getEntryDate() {
            return this.entryDate;
        }
        public void setEntryDate(String entryDate) {
            this.entryDate = entryDate;
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
        
        
    }

    
    public EmployeeAddlDetails getEmployeeAddlDetails() {
        return this.employeeAddlDetails;
    }

    public void setEmployeeAddlDetails(EmployeeAddlDetails employeeAddlDetails) {
        this.employeeAddlDetails = employeeAddlDetails;
    }

    public EmployeeAddress getEmployeeAddress() {
        return this.employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public EmployeeBasicInfo getEmployeeBasicDetails() {
        return this.employeeBasicDetails;
    }

    public void setEmployeeBasicDetails(EmployeeBasicInfo employeeBasicDetails) {
        this.employeeBasicDetails = employeeBasicDetails;
    }

    public EmployeeHierarchy getEmployeeHierarchy() {
        return this.employeeHierarchy;
    }

    public void setEmployeeHierarchy(EmployeeHierarchy employeeHierarchy) {
        this.employeeHierarchy = employeeHierarchy;
    }

    public EmployeeOptionalBenefit getEmployeeOptionalBenefit() {
        return this.employeeOptionalBenefit;
    }

    public void setEmployeeOptionalBenefit(EmployeeOptionalBenefit employeeOptionalBenefit) {
        this.employeeOptionalBenefit = employeeOptionalBenefit;
    }

    public EmployeeProfile getEmployeeProfile() {
        return this.employeeProfile;
    }

    public void setEmployeeProfile(EmployeeProfile employeeProfile) {
        this.employeeProfile = employeeProfile;
    }

    public EmployeeSalary getEmployeeSalary() {
        return this.employeeSalary;
    }

    public void setEmployeeSalary(EmployeeSalary employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}

