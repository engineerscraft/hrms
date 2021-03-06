package com.hamdard.hua.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hamdard.hua.utils.DateAdapter;

/**
 * @author Jyotirmoy Banerjee
 * /api/#_employee
 */
@XmlRootElement
public class Employee {

    private EmployeeAddlDetails employeeAddlDetails;
    private List<EmployeeAddress> employeeAddress;
    private EmployeeBasicInfo employeeBasicInfo;
    private EmployeeHierarchy employeeHierarchy;
    private List<EmployeeOptionalBenefit> employeeOptionalBenefit;
    private EmployeeProfile employeeProfile;
    private List<EmployeeSalary> employeeSalary;
    private AppraisalRating appraisalRating;
    private List<EmployeeDocument> documentList;
    private List<EmployeeTax> employeeTaxList;
    private Leave leave;

    public static class EmployeeDocument {
        private int docId;
        private String remarks;
        private Date uploadDate;
        private String uploadedBy;
        private String document;
        private int docTypeId;
        private String docTypeName;
        
        public int getDocTypeId() {
            return docTypeId;
        }
        public void setDocTypeId(int docTypeId) {
            this.docTypeId = docTypeId;
        }
        public String getDocTypeName() {
            return docTypeName;
        }
        public void setDocTypeName(String docTypeName) {
            this.docTypeName = docTypeName;
        }
        public int getDocId() {
            return docId;
        }
        public void setDocId(int docId) {
            this.docId = docId;
        }
        public String getRemarks() {
            return remarks;
        }
        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
        public Date getUploadDate() {
            return uploadDate;
        }
        public void setUploadDate(Date uploadDate) {
            this.uploadDate = uploadDate;
        }
        public String getUploadedBy() {
            return uploadedBy;
        }
        public void setUploadedBy(String uploadedBy) {
            this.uploadedBy = uploadedBy;
        }
        public String getDocument() {
            return document;
        }
        public void setDocument(String document) {
            this.document = document;
        }
        
        @Override
        public String toString() {
            return "EmployeeDocument [docId=" + docId + ", remarks=" + remarks + ", uploadDate=" + uploadDate + ", uploadedBy=" + uploadedBy + ", document=" + document + ", docTypeId=" + docTypeId + ", docTypeName=" + docTypeName + "]";
        }        
    }
    
    public static class AppraisalRating {
        private int gradeId;
        private String grade;
        private int designationId;
        private String designation;
        private int performanceIndicator;
        private String performanceDescripton;
        public int getGradeId() {
            return gradeId;
        }
        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }
        public String getGrade() {
            return grade;
        }
        public void setGrade(String grade) {
            this.grade = grade;
        }
        public int getDesignationId() {
            return designationId;
        }
        public void setDesignationId(int designationId) {
            this.designationId = designationId;
        }
        public String getDesignation() {
            return designation;
        }
        public void setDesignation(String designation) {
            this.designation = designation;
        }
        public int getPerformanceIndicator() {
            return performanceIndicator;
        }
        public void setPerformanceIndicator(int performanceIndicator) {
            this.performanceIndicator = performanceIndicator;
        }
        public String getPerformanceDescripton() {
            return performanceDescripton;
        }
        public void setPerformanceDescripton(String performanceDescripton) {
            this.performanceDescripton = performanceDescripton;
        }
        @Override
        public String toString() {
            return "AppraisalRating [gradeId=" + gradeId + ", grade=" + grade + ", designationId=" + designationId + ", designation=" + designation + ", performanceIndicator=" + performanceIndicator + ", performanceDescripton=" + performanceDescripton + "]";
        }
    }
    
    public static class EmployeeSalary {
        private SalaryComponent salaryComponent;
        private double salaryValue;

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
            return "EmployeeSalary [salaryComponent=" + this.salaryComponent + ", salaryValue=" + this.salaryValue + "]";
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
            return "EmployeeProfile [comments=" + this.comments + ", description=" + this.description + ", qualification=" + this.qualification + "]";
        }

    }

    public static class EmployeeOptionalBenefit {
        private double benefitValue;
        private int frequency;
        private int iterations;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date nextDueDate;
        private SalaryOptComponent optSalaryComponent;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date startDate;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date stopDate;
        private String remarks;
        private double totalAmount;
        private int salOptFlag;
        private int benefitId;
        private boolean needToUpdate;

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

        public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public int getSalOptFlag() {
			return salOptFlag;
		}

		public void setSalOptFlag(int salOptFlag) {
			this.salOptFlag = salOptFlag;
		}

		public int getBenefitId() {
			return benefitId;
		}

		public void setBenefitId(int benefitId) {
			this.benefitId = benefitId;
		}

		public boolean isNeedToUpdate() {
			return needToUpdate;
		}

		public void setNeedToUpdate(boolean needToUpdate) {
			this.needToUpdate = needToUpdate;
		}

		@Override
		public String toString() {
			return "EmployeeOptionalBenefit [benefitValue=" + benefitValue + ", frequency=" + frequency
					+ ", iterations=" + iterations + ", nextDueDate=" + nextDueDate + ", optSalaryComponent="
					+ optSalaryComponent + ", startDate=" + startDate + ", stopDate=" + stopDate + ", remarks="
					+ remarks + ", totalAmount=" + totalAmount + ", salOptFlag=" + salOptFlag + ", benefitId="
					+ benefitId + ", needToUpdate=" + needToUpdate + "]";
		}

    }

    public static class EmployeeHierarchy {
        private int cl; // the number of CLs of the employee
        private String hrId;
        private String hrEmailId;
        private int maternityLeave;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date noticePeriodEndDate;
        private int paternityLeave;
        private int pl; // paid leaves for the employee
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date probationPeriodEndDate;
        private int specialLeave;
        private String status;
        private String supervisorEmailId;
        private String supervisorId;
        private int sickLeave;

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

        public String getHrEmailId() {
            return this.hrEmailId;
        }

        public void setHrEmailId(String hrEmailId) {
            this.hrEmailId = hrEmailId;
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

        public String getSupervisorEmailId() {
            return this.supervisorEmailId;
        }

        public void setSupervisorEmailId(String supervisorEmailId) {
            this.supervisorEmailId = supervisorEmailId;
        }

        public String getSupervisorId() {
            return this.supervisorId;
        }

        public void setSupervisorId(String supervisorId) {
            this.supervisorId = supervisorId;
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
        private int countryId;
        private String countryName;
        private String description;
        private int districtId;
        private String districtName;
        private String houseNo;
        private String pinno;
        private String region;
        private int stateId;
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
            return "EmployeeAddress [area=" + this.area + ", countryId=" + this.countryId + ", countryName=" + this.countryName + ", description=" + this.description + ", districtId=" + this.districtId + ", districtName=" + this.districtName + ", houseNo="
                    + this.houseNo + ", pinno=" + this.pinno + ", region=" + this.region + ", stateId=" + this.stateId + ", stateName=" + this.stateName + ", streetName=" + this.streetName + "]";
        }

        public String getAddressType() {
            return this.addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }
    }

    public static class EmployeeAddlDetails {
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
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date preMedicalCheckUpDate;
        private int siblingNo;

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
            return "EmployeeAddlDetails [dependentNo=" + this.dependentNo + ", emergencyContactName=" + this.emergencyContactName + ", emergencyContactNo=" + this.emergencyContactNo + ", medicalReportComment=" + this.medicalReportComment + ", nomineeName1="
                    + this.nomineeName1 + ", nomineeName2=" + this.nomineeName2 + ", nomineeName3=" + this.nomineeName3 + ", nomineeShare1=" + this.nomineeShare1 + ", nomineeShare2=" + this.nomineeShare2 + ", nomineeShare3=" + this.nomineeShare3
                    + ", preMedicalCheckUpDate=" + this.preMedicalCheckUpDate + ", siblingNo=" + this.siblingNo + "]";
        }
    }

    public static class EmployeeBasicInfo {
        private String contactNo;
        private Department department;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date dob;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date doj;
        private String emailId;
        private String empFirstName;
        private String empId;
        private String empLastName;
        private String empMiddleName;
        private String empType;
        private String entryBy;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date entryDate;
        private String fatherName;
        private boolean hrFlag;
        private DocType identityDocType;
        private String identityNumber;
        private String maritalStatus;
        private int nationality;
        private String organization;
        private int organizationId;
        private String sex;
        private boolean supervisorFlag;
        private String title;
        private Unit unit;
        private String profileImage;
        private String countryName;
        
        
        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public int getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(int organizationId) {
            this.organizationId = organizationId;
        }

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

        public int getNationality() {
            return this.nationality;
        }

        public void setNationality(int nationality) {
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

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        @Override
        public String toString() {
            return "EmployeeBasicInfo [contactNo=" + contactNo + ", department=" + department + ", dob=" + dob + ", doj=" + doj + ", emailId=" + emailId + ", empFirstName=" + empFirstName + ", empId=" + empId + ", empLastName=" + empLastName
                    + ", empMiddleName=" + empMiddleName + ", empType=" + empType + ", entryBy=" + entryBy + ", entryDate=" + entryDate + ", fatherName=" + fatherName + ", hrFlag=" + hrFlag + ", identityDocType=" + identityDocType + ", identityNumber="
                    + identityNumber + ", maritalStatus=" + maritalStatus + ", nationality=" + nationality + ", organization=" + organization + ", sex=" + sex + ", supervisorFlag=" + supervisorFlag + ", title=" + title + ", unit=" + unit + "]";
        }

    }

    public static class EmployeeSearchResult {

        private String empId;
        private String name;
        private String emailId;
        private String departmentName;
        private String designation;
        private String contactNo;
        private String firstName;
        private String middleName;
        private String lastName;
        private String supervisorEmailId;
        private String hrEmailId;

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

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getSupervisorEmailId() {
            return supervisorEmailId;
        }

        public void setSupervisorEmailId(String supervisorEmailId) {
            this.supervisorEmailId = supervisorEmailId;
        }

        public String getHrEmailId() {
            return hrEmailId;
        }

        public void setHrEmailId(String hrEmailId) {
            this.hrEmailId = hrEmailId;
        }

        @Override
        public String toString() {
            return "EmployeeSearchResult [empId=" + empId + ", name=" + name + ", emailId=" + emailId + ", departmentName=" + departmentName + ", designation=" + designation + ", contactNo=" + contactNo + ", firstName=" + firstName + ", middleName="
                    + middleName + ", lastName=" + lastName + ", supervisorEmailId=" + supervisorEmailId + ", hrEmailId=" + hrEmailId + "]";
        }
    }

    public static class EmployeeTax{
    	private TaxComponent taxComponent;
    	private String entryBy;
    	@XmlJavaTypeAdapter(DateAdapter.class)
        private Date entryDate;
    	private double taxCompValue;
    	
    	public TaxComponent getTaxComponent() {
			return taxComponent;
		}
		public void setTaxComponent(TaxComponent taxComponent) {
			this.taxComponent = taxComponent;
		}
		public String getEntryBy() {
			return entryBy;
		}
		public void setEntryBy(String entryBy) {
			this.entryBy = entryBy;
		}
		public Date getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(Date entryDate) {
			this.entryDate = entryDate;
		}
		public double getTaxCompValue() {
			return taxCompValue;
		}
		public void setTaxCompValue(double taxCompValue) {
			this.taxCompValue = taxCompValue;
		}
		@Override
		public String toString() {
			return "EmployeeTax [taxComponent=" + taxComponent + ", entryBy=" + entryBy + ", entryDate=" + entryDate
					+ ", taxCompValue=" + taxCompValue + "]";
		}
		
    }
    
    public static class Leave{
    	private int eligibleCl;
    	private int eligiblePl;
    	private int eligiblePaternityMaternityLeave;
    	private int eligibleSickLeave;
    	private int eligibleSpecialLeave;
    	
    	private int availedCl;
    	private int availedPl;
    	private int availedPaternityMaternityLeave;
    	private int availedSickLeave;
    	private int availedSpecialLeave;
    	
    	private int remainingCl;
    	private int remainingPl;
    	private int remainingPaternityMaternityLeave;
    	private int remainingSickLeave;
    	private int remainingSpecialLeave;
    	
		public int getEligibleCl() {
			return eligibleCl;
		}
		public void setEligibleCl(int eligibleCl) {
			this.eligibleCl = eligibleCl;
		}
		public int getEligiblePl() {
			return eligiblePl;
		}
		public void setEligiblePl(int eligiblePl) {
			this.eligiblePl = eligiblePl;
		}
		public int getEligiblePaternityMaternityLeave() {
			return eligiblePaternityMaternityLeave;
		}
		public void setEligiblePaternityMaternityLeave(int eligiblePaternityMaternityLeave) {
			this.eligiblePaternityMaternityLeave = eligiblePaternityMaternityLeave;
		}
		public int getEligibleSickLeave() {
			return eligibleSickLeave;
		}
		public void setEligibleSickLeave(int eligibleSickLeave) {
			this.eligibleSickLeave = eligibleSickLeave;
		}
		public int getEligibleSpecialLeave() {
			return eligibleSpecialLeave;
		}
		public void setEligibleSpecialLeave(int eligibleSpecialLeave) {
			this.eligibleSpecialLeave = eligibleSpecialLeave;
		}
		public int getAvailedCl() {
			return availedCl;
		}
		public void setAvailedCl(int availedCl) {
			this.availedCl = availedCl;
		}
		public int getAvailedPl() {
			return availedPl;
		}
		public void setAvailedPl(int availedPl) {
			this.availedPl = availedPl;
		}
		public int getAvailedPaternityMaternityLeave() {
			return availedPaternityMaternityLeave;
		}
		public void setAvailedPaternityMaternityLeave(int availedPaternityMaternityLeave) {
			this.availedPaternityMaternityLeave = availedPaternityMaternityLeave;
		}
		public int getAvailedSickLeave() {
			return availedSickLeave;
		}
		public void setAvailedSickLeave(int availedSickLeave) {
			this.availedSickLeave = availedSickLeave;
		}
		public int getAvailedSpecialLeave() {
			return availedSpecialLeave;
		}
		public void setAvailedSpecialLeave(int availedSpecialLeave) {
			this.availedSpecialLeave = availedSpecialLeave;
		}
		public int getRemainingCl() {
			return remainingCl;
		}
		public void setRemainingCl(int remainingCl) {
			this.remainingCl = remainingCl;
		}
		public int getRemainingPl() {
			return remainingPl;
		}
		public void setRemainingPl(int remainingPl) {
			this.remainingPl = remainingPl;
		}
		public int getRemainingPaternityMaternityLeave() {
			return remainingPaternityMaternityLeave;
		}
		public void setRemainingPaternityMaternityLeave(int remainingPaternityMaternityLeave) {
			this.remainingPaternityMaternityLeave = remainingPaternityMaternityLeave;
		}
		public int getRemainingSickLeave() {
			return remainingSickLeave;
		}
		public void setRemainingSickLeave(int remainingSickLeave) {
			this.remainingSickLeave = remainingSickLeave;
		}
		public int getRemainingSpecialLeave() {
			return remainingSpecialLeave;
		}
		public void setRemainingSpecialLeave(int remainingSpecialLeave) {
			this.remainingSpecialLeave = remainingSpecialLeave;
		}
		
		@Override
		public String toString() {
			return "Leave [eligibleCl=" + eligibleCl + ", eligiblePl=" + eligiblePl
					+ ", eligiblePaternityMaternityLeave=" + eligiblePaternityMaternityLeave + ", eligibleSickLeave="
					+ eligibleSickLeave + ", eligibleSpecialLeave=" + eligibleSpecialLeave + ", availedCl=" + availedCl
					+ ", availedPl=" + availedPl + ", availedPaternityMaternityLeave=" + availedPaternityMaternityLeave
					+ ", availedSickLeave=" + availedSickLeave + ", availedSpecialLeave=" + availedSpecialLeave
					+ ", remainingCl=" + remainingCl + ", remainingPl=" + remainingPl
					+ ", remainingPaternityMaternityLeave=" + remainingPaternityMaternityLeave + ", remainingSickLeave="
					+ remainingSickLeave + ", remainingSpecialLeave=" + remainingSpecialLeave + "]";
		}

    }
    
    public AppraisalRating getAppraisalRating() {
        return appraisalRating;
    }

    public void setAppraisalRating(AppraisalRating appraisalRating) {
        this.appraisalRating = appraisalRating;
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

    public List<EmployeeDocument> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<EmployeeDocument> documentList) {
        this.documentList = documentList;
    }

    public List<EmployeeTax> getEmployeeTaxList() {
		return employeeTaxList;
	}

	public void setEmployeeTaxList(List<EmployeeTax> employeeTaxList) {
		this.employeeTaxList = employeeTaxList;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	@Override
	public String toString() {
		return "Employee [employeeAddlDetails=" + employeeAddlDetails + ", employeeAddress=" + employeeAddress
				+ ", employeeBasicInfo=" + employeeBasicInfo + ", employeeHierarchy=" + employeeHierarchy
				+ ", employeeOptionalBenefit=" + employeeOptionalBenefit + ", employeeProfile=" + employeeProfile
				+ ", employeeSalary=" + employeeSalary + ", appraisalRating=" + appraisalRating + ", documentList="
				+ documentList + ", employeeTaxList=" + employeeTaxList + ", leave=" + leave + "]";
	}

}
