package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Department;
import com.hamdard.hua.model.DocType;
import com.hamdard.hua.model.Employee;
import com.hamdard.hua.model.Employee.AppraisalRating;
import com.hamdard.hua.model.Unit;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeBasicInfo(new Employee.EmployeeBasicInfo());
        emp.getEmployeeBasicInfo().setContactNo(rs.getString("CONTACTNO"));
        emp.getEmployeeBasicInfo().setDepartment(new Department());
        emp.getEmployeeBasicInfo().getDepartment().setDepartmentId(rs.getInt("DEPARTMENT_ID"));
        emp.getEmployeeBasicInfo().getDepartment().setDepartmentName(rs.getString("DEPARTMENT_NAME"));
        emp.getEmployeeBasicInfo().setDob(rs.getDate("DATE_OF_BIRTH"));
        emp.getEmployeeBasicInfo().setDoj(rs.getDate("DATE_OF_JOINING"));
        emp.getEmployeeBasicInfo().setEmailId(rs.getString("EMAIL_ID"));
        emp.getEmployeeBasicInfo().setEmpFirstName(rs.getString("EMPLOYEE_FIRST_NAME"));
        emp.getEmployeeBasicInfo().setEmpId(rs.getString("EMP_ID"));
        emp.getEmployeeBasicInfo().setEmpLastName(rs.getString("EMPLOYEE_LAST_NAME"));
        emp.getEmployeeBasicInfo().setEmpMiddleName(rs.getString("EMPLOYEE_MIDDLE_NAME"));
        emp.getEmployeeBasicInfo().setEmpType(rs.getString("EMP_TYPE"));
        emp.getEmployeeBasicInfo().setFatherName(rs.getString("FATHER_NAME"));
        emp.getEmployeeBasicInfo().setHrFlag("X".equals(rs.getString("HR_FLAG"))?true:false);
        emp.getEmployeeBasicInfo().setIdentityDocType(new DocType());
        emp.getEmployeeBasicInfo().getIdentityDocType().setDocTypeId(rs.getInt("IDENTITY_DOC_TYPE_ID"));
        emp.getEmployeeBasicInfo().getIdentityDocType().setDocTypeName(rs.getString("DOC_TYPE_NAME"));
        emp.getEmployeeBasicInfo().setIdentityNumber(rs.getString("IDENTITY_NUMBER"));
        emp.getEmployeeBasicInfo().setMaritalStatus(rs.getString("MARITAL_STATUS"));
        emp.getEmployeeBasicInfo().setNationality(rs.getInt("NATIONALITY"));
        emp.getEmployeeBasicInfo().setCountryName(rs.getString("COUNTRY_NAME"));
        emp.getEmployeeBasicInfo().setOrganization(rs.getString("ORG_NAME"));
        emp.getEmployeeBasicInfo().setOrganizationId(rs.getInt("ORG_ID"));
        emp.getEmployeeBasicInfo().setProfileImage(rs.getString("EMPLOYEE_IMAGE"));
        emp.getEmployeeBasicInfo().setSex(rs.getString("SEX"));
        emp.getEmployeeBasicInfo().setSupervisorFlag("X".equals(rs.getString("SUPERVISOR_FLAG"))?true:false);
        emp.getEmployeeBasicInfo().setTitle(rs.getString("TITLE"));
        emp.getEmployeeBasicInfo().setUnit(new Unit());
        emp.getEmployeeBasicInfo().getUnit().setUnitName(rs.getString("UNIT_NAME"));
        emp.getEmployeeBasicInfo().getUnit().setUnitId(rs.getInt("UNIT_ID"));
        
        emp.setEmployeeAddlDetails(new Employee.EmployeeAddlDetails());
        emp.getEmployeeAddlDetails().setDependentNo(rs.getInt("NO_OF_DEPENDENT"));
        emp.getEmployeeAddlDetails().setEmergencyContactName(rs.getString("EMERGENCY_CONTACT_NAME"));
        emp.getEmployeeAddlDetails().setEmergencyContactNo(rs.getString("EMERGENCY_CONTACTNO"));
        emp.getEmployeeAddlDetails().setMedicalReportComment(rs.getString("MEDICAL_REPORT_COMMENT"));
        emp.getEmployeeAddlDetails().setNomineeName1(rs.getString("NOMINEE_NAME1"));
        emp.getEmployeeAddlDetails().setNomineeName2(rs.getString("NOMINEE_NAME2"));
        emp.getEmployeeAddlDetails().setNomineeName3(rs.getString("NOMINEE_NAME3"));
        emp.getEmployeeAddlDetails().setNomineeShare1(rs.getDouble("NOMINEE1_SHARE"));
        emp.getEmployeeAddlDetails().setNomineeShare2(rs.getDouble("NOMINEE2_SHARE"));
        emp.getEmployeeAddlDetails().setNomineeShare3(rs.getDouble("NOMINEE3_SHARE"));
        emp.getEmployeeAddlDetails().setPreMedicalCheckUpDate(rs.getDate("PRE_MEDICAL_CHKUP_DATE"));
        emp.getEmployeeAddlDetails().setSiblingNo(rs.getInt("NO_OF_SIBLINGS"));
        
        emp.setEmployeeHierarchy(new Employee.EmployeeHierarchy());
        emp.getEmployeeHierarchy().setCl(rs.getInt("CL"));
        emp.getEmployeeHierarchy().setHrEmailId(rs.getString("HR_EMAIL_ID"));
        emp.getEmployeeHierarchy().setHrId(rs.getString("HR_ID"));
        emp.getEmployeeHierarchy().setMaternityLeave(rs.getInt("MATERNITY_LEAVE"));
        emp.getEmployeeHierarchy().setNoticePeriodEndDate(rs.getDate("NOTICE_PERIOD_LAST_DATE"));
        emp.getEmployeeHierarchy().setPaternityLeave(rs.getInt("PATERNITY_LEAVE"));
        emp.getEmployeeHierarchy().setPl(rs.getInt("PL"));
        emp.getEmployeeHierarchy().setProbationPeriodEndDate(rs.getDate("PROBATION_PERIOD_END_DATE"));
        emp.getEmployeeHierarchy().setSickLeave(rs.getInt("SICK_LEAVE"));
        emp.getEmployeeHierarchy().setSpecialLeave(rs.getInt("SPECIAL_LEAVE"));
        emp.getEmployeeHierarchy().setStatus(rs.getString("STATUS"));
        emp.getEmployeeHierarchy().setSupervisorEmailId(rs.getString("SUPERVISOR_EMAIL_ID"));
        emp.getEmployeeHierarchy().setSupervisorId(rs.getString("SUPERVISOR_ID"));
        
        emp.setEmployeeProfile(new Employee.EmployeeProfile());
        emp.getEmployeeProfile().setComments(rs.getString("COMMENTS"));
        emp.getEmployeeProfile().setDescription(rs.getString("DESCRIPTION"));
        emp.getEmployeeProfile().setQualification(rs.getString("QUALIFICATION"));
        
        emp.setEmployeeAddress(new ArrayList<Employee.EmployeeAddress>());
        
        Employee.EmployeeAddress permAddress = new Employee.EmployeeAddress();
        permAddress.setAddressType(rs.getString("P_ADDRESS_TYPE"));
        permAddress.setArea(rs.getString("P_AREA"));
        permAddress.setCountryId(rs.getInt("P_COUNTRY_ID"));
        permAddress.setCountryName(rs.getString("P_COUNTRY_NAME"));
        permAddress.setDistrictId(rs.getInt("P_DISTRICT_ID"));
        permAddress.setDistrictName(rs.getString("P_DISTRICT_NAME"));
        permAddress.setHouseNo(rs.getString("P_HOUSE_NO"));
        permAddress.setPinno(rs.getString("P_PIN_NO"));
        permAddress.setRegion(rs.getString("P_REGION"));
        permAddress.setStateId(rs.getInt("P_STATE_ID"));
        permAddress.setStateName(rs.getString("P_STATE_NAME"));
        permAddress.setStreetName(rs.getString("P_STREET_NAME"));

        emp.getEmployeeAddress().add(permAddress);
        
        Employee.EmployeeAddress currentAddress = new Employee.EmployeeAddress();
        currentAddress.setAddressType(rs.getString("C_ADDRESS_TYPE"));
        currentAddress.setArea(rs.getString("C_AREA"));
        currentAddress.setCountryId(rs.getInt("C_COUNTRY_ID"));
        currentAddress.setCountryName(rs.getString("C_COUNTRY_NAME"));
        currentAddress.setDistrictId(rs.getInt("C_DISTRICT_ID"));
        currentAddress.setDistrictName(rs.getString("C_DISTRICT_NAME"));
        currentAddress.setHouseNo(rs.getString("C_HOUSE_NO"));
        currentAddress.setPinno(rs.getString("C_PIN_NO"));
        currentAddress.setRegion(rs.getString("C_REGION"));
        currentAddress.setStateId(rs.getInt("C_STATE_ID"));
        currentAddress.setStateName(rs.getString("C_STATE_NAME"));
        currentAddress.setStreetName(rs.getString("C_STREET_NAME"));

        emp.getEmployeeAddress().add(currentAddress);
        
        emp.setAppraisalRating(new AppraisalRating());
        emp.getAppraisalRating().setDesignation(rs.getString("DESIGNATION"));
        emp.getAppraisalRating().setDesignationId(rs.getInt("DESIGNATION_ID"));
        emp.getAppraisalRating().setGrade(rs.getString("GRADE"));
        emp.getAppraisalRating().setGradeId(rs.getInt("GRADE_ID"));
        emp.getAppraisalRating().setPerformanceIndicator(rs.getInt("PERFORMANCE_INDICATOR"));
        emp.getAppraisalRating().setPerformanceDescripton(rs.getString("PERFORMANCE_DESCRIPTION"));
        return emp;
    }
}
