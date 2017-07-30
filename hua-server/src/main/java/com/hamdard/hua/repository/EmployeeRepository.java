package com.hamdard.hua.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.model.Employee.EmployeeAddlDetails;
import com.hamdard.hua.model.Employee.EmployeeAddress;
import com.hamdard.hua.model.Employee.EmployeeBasicInfo;
import com.hamdard.hua.model.Employee.EmployeeDocument;
import com.hamdard.hua.model.Employee.EmployeeHierarchy;
import com.hamdard.hua.model.Employee.EmployeeOptionalBenefit;
import com.hamdard.hua.model.Employee.EmployeeProfile;
import com.hamdard.hua.model.Employee.EmployeeSalary;
import com.hamdard.hua.model.Unit;
import com.hamdard.hua.rowmapper.DocumentRowMapper;
import com.hamdard.hua.rowmapper.EmployeeImageFileMapper;
import com.hamdard.hua.rowmapper.EmployeeRowMapper;
import com.hamdard.hua.rowmapper.EmployeeSearchResultRowMapper;

/**
 * @author Jyotirmoy Banerjee
 * 
 */

@Component
public class EmployeeRepository {
    private static final Logger logger = LogManager.getLogger(EmployeeRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    /***************************************** AUTOWIRED COMPONENTS **************************************/
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UnitRepository unitRepo;

    @Autowired
    private AuthenticationRepository authRepo;

    /**************************************** AUTOWIRED PROPERTIES**************************************/

    @Value("${sql.employee.nextId}")
    private String obtainEmployeeIdFrmSeq;

    @Value("${sql.employee.setId}")
    private String updateSeqValue;

    @Value("${employeeIdNumericLength}")
    private String employeeIdNumericLength;

    @Value("${sql.employee.insert.basicInfo}")
    private String employeeBasicInfoInsert;

    @Value("${sql.employee.insert.addnDetails}")
    private String employeeAdditionalDtlInsert;

    @Value("${sql.employee.insert.address}")
    private String employeeAddressInsert;

    @Value("${sql.employee.insert.hierarchy}")
    private String employeeHierarchyInsert;

    @Value("${sql.employee.insert.profile}")
    private String employeeProfileInsert;

    @Value("${sql.employee.insert.employee.salary}")
    private String employeeSalaryInsert;

    @Value("${sql.employee.insert.employee.optional.benefit}")
    private String employeeOptionalBenefitsInsert;

    @Value("${sql.employee.get.appraisal.id}")
    private String getAppraisalId;

    @Value("${sql.employee.insert.employee.additional.details.history}")
    private String employeeAdditionalDetailsHistoryInsert;

    @Value("${sql.employee.insert.employee.hierarchy.status.history}")
    private String empHierStatusHistoryInsert;

    @Value("${sql.employee.insert.employee.profile.history}")
    private String employeeProfileHistoryInsert;

    /******************* Update Operations *******************/

    @Value("${sql.employee.update.employee.salary}")
    private String employeeSalaryUpdate;

    @Value("${sql.employee.update.profile}")
    private String employeeProfileUpdate;

    @Value("${sql.employee.update.employee.optional.benefit}")
    private String employeeOptionalBenefitsUpdate;

    @Value("${sql.employee.update.employee.additional.details.by.EmpId}")
    private String employeeAdditionalDetailsUpdatebyEmpId;

    @Value("${sql.employee.update.address.by.EmpId}")
    private String employeeAddressUpdatebyEmpId;

    @Value("${sql.employee.search.determine.privilege}")
    private String empSearchPrevilegeDetermineSql;

    @Value("${sql.employee.search.hierarchy}")
    private String employeeHierarchySearchSql;

    @Value("${hierarchy.search.limit}")
    private int hierarchySearchLimit;

    @Value("${autocomplete.limit}")
    private int autoCompleteLimit;

    @Value("${sql.employee.hier.status.by.EmpId}")
    private String empHierStatusUpdateByEmpId;

    @Value("${sql.employee.update.employee.by.EmpId}")
    private String empBasicInfoUpdateByEmpId;

    @Value("${sql.employee.insert.employee.history}")
    private String empInsertEmployeeHistory;

    @Value("${sql.update.employee.image.by.empId}")
    private String updateEmployeeImageByEmpId;
    
    @Value("${sql.insert.employeeDocument}")
    private String insertEmployeeDocument;
     
    @Value("${sql.update.employeeDocuments}")
    private String updateEmployeeDocument;
    
    /********************** GET Operations *************************/
    
    @Value("${sql.employee.get.byId}")
    private String getEmployeeDetailsByEmpId;
    
    @Value("${sql.employee.get.image.byId}")
    private String getEmployeeImageByEmpId;
    
    @Value("${sql.select.employeeDocumentDetails}")
    private String selectAllDocuments;

    @Value("${sql.select.employeeDocument}")
    private String selectDocument;
    /*****************************************************************************************************/

    @Transactional
    public String createEmployee(Employee newEmployee) throws Exception {
        String employeeId = this.generateEmployeeId(newEmployee.getEmployeeBasicInfo().getOrganization(), newEmployee.getEmployeeBasicInfo().getUnit());

        Date entryDate = newEmployee.getEmployeeBasicInfo().getEntryDate();
        if (entryDate == null)
            entryDate = new Date();
        String entryBy = newEmployee.getEmployeeBasicInfo().getEntryBy();

        this.insertBasicInfo(employeeId, newEmployee.getEmployeeBasicInfo());
        if(newEmployee.getEmployeeAddlDetails() != null)
            this.insertAdditionalInfo(employeeId, newEmployee.getEmployeeAddlDetails());
        if(newEmployee.getEmployeeAddress() != null)
            this.insertEmployeeAddress(employeeId, newEmployee.getEmployeeAddress());
        if(newEmployee.getEmployeeHierarchy() != null)
            this.insertEmployeeHierarchy (employeeId, newEmployee.getEmployeeHierarchy(), entryDate);
        if(newEmployee.getEmployeeProfile() != null)
            this.insertEmployeeProfile (employeeId, newEmployee.getEmployeeProfile());
        if(newEmployee.getEmployeeSalary() != null)
            this.insertEmpSalaryComponents(employeeId, entryBy, newEmployee.getEmployeeSalary(), entryDate);
        // TODO: take care of optional components

        // create LDAP user
        authRepo.createUser(employeeId, newEmployee);
        return String.format("Employee created with ID: %s", employeeId);
    }

    /**TODO: Confirm logic
     * Insert the salary components
     * @param employeeId
     * @param entryBy
     * @param salaryComponents
     * @throws Exception
     */
    private void insertEmpSalaryComponents(String employeeId, String entryBy, List<EmployeeSalary> salaryComponents, Date entryDate) throws Exception {
        if (salaryComponents != null)
            for (EmployeeSalary salary : salaryComponents) {
                long appraisalId = this.getAppraisalId(entryDate);
                logger.info(sqlMarker, employeeSalaryInsert);
                logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}", () -> appraisalId, () -> employeeId, () -> salary.getSalaryComponent().getCompId(), () -> entryBy, () -> entryDate, () -> salary.getSalaryValue());
                jdbcTemplate.update(employeeSalaryInsert, new Object[] { appraisalId, employeeId, salary.getSalaryComponent().getCompId(), entryBy, entryDate, salary.getSalaryValue() });
            }
    }

    private long getAppraisalId(Date entryDate) throws Exception {
        logger.info(sqlMarker, getAppraisalId);
        logger.info(sqlMarker, "Params {}, {}", entryDate, entryDate);
        Long appraisalId = null;
        try {
            appraisalId = jdbcTemplate.queryForObject(getAppraisalId, new Object[] { entryDate, entryDate }, Long.class);
        } catch (Exception ex) {
            logger.debug("Given entryDate {} does not correspond to any appraisal cycle", () -> entryDate);
            throw new Exception("Given entryDate " + entryDate + " does not correspond to any appraisal cycle");
        }
        return appraisalId;
    }

    /**
     * Insert employee profile
     * @param employeeId
     * @param profile
     * @throws Exception
     */
    private void insertEmployeeProfile(String employeeId, EmployeeProfile profile) throws Exception {
        logger.info(sqlMarker, employeeProfileInsert);
        logger.info(sqlMarker, "Params {}, {}, {}, {}", () -> employeeId, () -> profile.getQualification(), () -> profile.getDescription(), () -> profile.getComments());

        jdbcTemplate.update(employeeProfileInsert, new Object[] { employeeId, profile.getQualification(), profile.getDescription(), profile.getComments() });
    }

    /**
     * Insert employee hierarchy
     * @param employeeId
     * @param hierarchy
     * @throws Exception
     */
    private void insertEmployeeHierarchy(String employeeId, EmployeeHierarchy hierarchy, Date entryDate) throws Exception {
        logger.info(sqlMarker, employeeHierarchyInsert);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", 
                () -> employeeId, 
                () -> hierarchy.getSupervisorEmailId(), 
                () -> hierarchy.getSupervisorEmailId(), 
                () -> hierarchy.getHrEmailId() , 
                () -> hierarchy.getHrEmailId(),
                () -> hierarchy.getStatus(), 
                () -> hierarchy.getCl(), 
                () -> hierarchy.getPl(), 
                () -> hierarchy.getPaternityLeave(), 
                () -> hierarchy.getSickLeave(), 
                () -> hierarchy.getMaternityLeave(), () -> hierarchy.getSpecialLeave(), 
                () -> hierarchy.getProbationPeriodEndDate(), () -> hierarchy.getNoticePeriodEndDate(), 
                () -> entryDate);

        jdbcTemplate.update(employeeHierarchyInsert, new Object[] { employeeId, hierarchy.getSupervisorEmailId(), hierarchy.getSupervisorEmailId(), 
                hierarchy.getHrEmailId() , hierarchy.getHrEmailId(),
                hierarchy.getStatus(), hierarchy.getCl(), hierarchy.getPl(), hierarchy.getPaternityLeave(), 
                hierarchy.getSickLeave(), hierarchy.getMaternityLeave(), hierarchy.getSpecialLeave(), 
                hierarchy.getProbationPeriodEndDate(), hierarchy.getNoticePeriodEndDate(), entryDate });
    }

    /**
     * Insert employee address
     * @param employeeId
     * @param address
     * @throws Exception
     */
    private void insertEmployeeAddress(String employeeId, List<EmployeeAddress> addressList) throws Exception {
        if (addressList != null)
            for (EmployeeAddress address : addressList) {
                logger.info(sqlMarker, employeeAddressInsert);
                logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeId, () -> address.getAddressType(), () -> address.getHouseNo(), () -> address.getStreetName(), () -> address.getArea(), () -> address.getRegion(),
                        () -> address.getPinno(), () -> address.getDistrictId(), () -> address.getStateId(), () -> address.getCountryId(), () -> address.getDescription());

                jdbcTemplate.update(employeeAddressInsert, new Object[] { employeeId, address.getAddressType(), address.getHouseNo(), address.getStreetName(), address.getArea(), address.getRegion(), address.getPinno(), address.getDistrictId(),
                        address.getStateId(), address.getCountryId(), address.getDescription() });
            }
    }

    /**
     * Insert into EMPLOYEE_ADDITIONAL_DETAILS
     * @param empNo
     * @param additonalDetails
     * @throws Exception
     */
    private void insertAdditionalInfo(String employeeId, EmployeeAddlDetails additonalDetails) throws Exception {
        logger.info(sqlMarker, employeeAdditionalDtlInsert);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, " + "{}, {}, {}, {}, {}, " + "{}, {}, {}", () -> employeeId, () -> additonalDetails.getSiblingNo(), () -> additonalDetails.getDependentNo(), () -> additonalDetails.getNomineeName1(),
                () -> additonalDetails.getNomineeName2(), () -> additonalDetails.getNomineeName3(), () -> additonalDetails.getNomineeShare1(), () -> additonalDetails.getNomineeShare2(), () -> additonalDetails.getNomineeShare3(),
                () -> additonalDetails.getEmergencyContactName(), () -> additonalDetails.getEmergencyContactNo(), () -> additonalDetails.getPreMedicalCheckUpDate(), () -> additonalDetails.getMedicalReportComment());

        jdbcTemplate.update(employeeAdditionalDtlInsert,
                new Object[] { employeeId, additonalDetails.getSiblingNo(), additonalDetails.getDependentNo(), additonalDetails.getNomineeName1(), additonalDetails.getNomineeName2(), additonalDetails.getNomineeName3(), additonalDetails.getNomineeShare1(),
                        additonalDetails.getNomineeShare2(), additonalDetails.getNomineeShare3(), additonalDetails.getEmergencyContactName(), additonalDetails.getEmergencyContactNo(), additonalDetails.getPreMedicalCheckUpDate(),
                        additonalDetails.getMedicalReportComment() });
    }

    /**
     * Insertion into the EMPLOYEE
     * @param empNo
     * @param basicInfo
     * @throws Exception
     */
    private void insertBasicInfo(String employeeId, EmployeeBasicInfo basicInfo) throws Exception {

        logger.info(sqlMarker, employeeBasicInfoInsert);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, " + "{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, " + "{}, {}, {}", () -> employeeId, () -> basicInfo.getTitle(), () -> basicInfo.getEmpFirstName(), () -> basicInfo.getEmpMiddleName(),
                () -> basicInfo.getEmpLastName(), () -> basicInfo.getSex(), () -> basicInfo.getEmpType(), () -> basicInfo.getMaritalStatus(), () -> basicInfo.getDoj(), () -> basicInfo.getOrganization(),
                () -> basicInfo.getUnit() != null ? basicInfo.getUnit().getUnitId() : null, () -> basicInfo.getDepartment() != null ? basicInfo.getDepartment().getDepartmentId() : null, () -> basicInfo.getNationality(),
                () -> basicInfo.getIdentityDocType() != null ? basicInfo.getIdentityDocType().getDocTypeId() : null, () -> basicInfo.getIdentityNumber(), () -> basicInfo.getDob(), () -> basicInfo.getFatherName(), () -> basicInfo.getEmailId(),
                () -> basicInfo.getContactNo(), () -> basicInfo.getEntryBy(), () -> basicInfo.getEntryDate(), () -> basicInfo.isHrFlag() ? "X" : null, () -> basicInfo.isSupervisorFlag() ? "X" : null);

        jdbcTemplate.update(employeeBasicInfoInsert,
                new Object[] { employeeId, basicInfo.getTitle(), basicInfo.getEmpFirstName(), basicInfo.getEmpMiddleName(), basicInfo.getEmpLastName(), basicInfo.getSex(), basicInfo.getEmpType(), basicInfo.getMaritalStatus(), basicInfo.getDoj(),
                        Integer.parseInt(basicInfo.getOrganization()), basicInfo.getUnit() != null ? basicInfo.getUnit().getUnitId() : null, basicInfo.getDepartment() != null ? basicInfo.getDepartment().getDepartmentId() : null, basicInfo.getNationality(),
                        basicInfo.getIdentityDocType() != null ? basicInfo.getIdentityDocType().getDocTypeId() : null, basicInfo.getIdentityNumber(), basicInfo.getDob(), basicInfo.getFatherName(), basicInfo.getEmailId(), basicInfo.getContactNo(),
                        basicInfo.getEntryBy(), basicInfo.getEntryDate(), basicInfo.isHrFlag() ? "X" : null, basicInfo.isSupervisorFlag() ? "X" : null });
    }

    /**
     * Generates an employee number for the insert operation
     * @param unit
     * @return the complete employee number generated for the given unit
     * @throws Exception
     */
    private String generateEmployeeId(String orgn, Unit unit) throws Exception {
        List<Unit> allUnits = unitRepo.getUnitsByOrganizationId(Integer.valueOf(orgn));
        Unit matchedUnit = null;
        for (Unit iUnit : allUnits) {
            if (iUnit.getUnitId() == unit.getUnitId()) {
                matchedUnit = iUnit;
                break;
            }
        }
        if (matchedUnit == null)
            throw new Exception("The Unit provided as an iput does not match to an unique value in the DB!");

        String prefix = matchedUnit.getEmpIdPrefix();
        String seqName = matchedUnit.getEmpIdSeqName();

        logger.info(sqlMarker, obtainEmployeeIdFrmSeq);
        logger.info(sqlMarker, "Params {}", () -> seqName);

        Long empIdObtainedNo = jdbcTemplate.queryForObject(obtainEmployeeIdFrmSeq, new Object[] { seqName }, Long.class);
        Long newEmpNo = empIdObtainedNo + 1;

        logger.info(sqlMarker, updateSeqValue);
        logger.info(sqlMarker, "Params {} {}", () -> newEmpNo, () -> seqName);
        jdbcTemplate.update(updateSeqValue, new Object[] { newEmpNo, seqName });

        String paddedEmpNo = StringUtils.leftPad(String.valueOf(newEmpNo), Integer.valueOf(employeeIdNumericLength), '0');
        String completeEmpNo = prefix + paddedEmpNo;
        logger.debug("Employee number generated: {}", () -> completeEmpNo);
        return completeEmpNo;
    }

    /**
     * Update Employee Salary
     * @param employeeId
     * @param entryBy
     * @param salaryComponents
     * @param entryDate
     * @throws Exception
     */

    public void updateEmpSalaryComponents(String employeeId, String entryBy, List<EmployeeSalary> salaryComponents) throws Exception {
        if (salaryComponents != null)
            for (EmployeeSalary salary : salaryComponents) {
                logger.info(sqlMarker, employeeSalaryUpdate);
                logger.info(sqlMarker, "Params {}, {}, {}, {}", () -> salary.getSalaryValue(), () -> entryBy, () -> employeeId, () -> salary.getSalaryComponent().getCompId());
                jdbcTemplate.update(employeeSalaryUpdate, new Object[] { salary.getSalaryValue(), entryBy, employeeId, salary.getSalaryComponent().getCompId() });
            }
    }

    /**
     * Update Employee Profile
     * @param employeeId
     * @param profile
     * @throws Exception
     */
    @Transactional
    public String updateEmployeeProfile(String employeeId, String modifiedBy, EmployeeProfile profile) throws Exception {
        logger.info(sqlMarker, employeeProfileUpdate);
        logger.info(sqlMarker, "Params {}, {}, {}, {}", () -> profile.getQualification(), () -> profile.getDescription(), () -> profile.getComments(), () -> employeeId);

        int numberOfRowsUpdated = jdbcTemplate.update(employeeProfileUpdate, new Object[] { profile.getQualification(), profile.getDescription(), profile.getComments(), employeeId });

        /* Make entry in employee_profile_history 
         * if there is successful update in table 
         * employee_profile
         * */
        if (numberOfRowsUpdated > 0) {
            logger.info(sqlMarker, employeeProfileHistoryInsert);
            logger.info(sqlMarker, "Params {}, {}, {}, {}, {}", () -> employeeId, () -> profile.getQualification(), () -> profile.getDescription(), () -> profile.getComments(), () -> modifiedBy);

            jdbcTemplate.update(employeeProfileHistoryInsert, new Object[] { employeeId, profile.getQualification(), profile.getDescription(), profile.getComments(), modifiedBy });
            return String.format("Successfully updated employee profile for Emp ID : %s", employeeId);
        } else {
            return String.format("Unable to update employee profile for Emp ID : %s", employeeId);
        }
    }

    /** TODO: Confirm logic
     * Insert into optional salary components table
     * @param employeeId
     * @param optBenefits
     * @throws Exception
     */
    public void insertEmpOptionalBenefits(String employeeId, String entryBy, List<EmployeeOptionalBenefit> optBenefits) throws Exception {
        if (optBenefits != null)
            for (EmployeeOptionalBenefit optBenefit : optBenefits) {
                logger.info(sqlMarker, employeeOptionalBenefitsUpdate);
                logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> null, () -> employeeId, () -> optBenefit.getOptSalaryComponent().getOptCompId(), () -> optBenefit.getOptSalaryComponent().getSalOptComponent(),
                        () -> optBenefit.getBenefitValue(), () -> optBenefit.getStartDate(), () -> optBenefit.getStopDate(), () -> optBenefit.getNextDueDate(), () -> optBenefit.getRemarks(), () -> entryBy, () -> optBenefit.getFrequency(),
                        () -> optBenefit.getIterations());
                jdbcTemplate.update(employeeOptionalBenefitsInsert, new Object[] { null, employeeId, optBenefit.getOptSalaryComponent().getOptCompId(), optBenefit.getOptSalaryComponent().getSalOptComponent(), optBenefit.getBenefitValue(),
                        optBenefit.getStartDate(), optBenefit.getStopDate(), optBenefit.getNextDueDate(), optBenefit.getRemarks(), entryBy, optBenefit.getFrequency(), optBenefit.getIterations() });
            }
    }

    /** TODO: Confirm logic
     * Update employee optional salary components table
     * @param employeeId
     * @param optBenefits
     * @throws Exception
     */
    public void updateEmpOptionalBenefits(String employeeId, int optCompId, String entryBy, EmployeeOptionalBenefit optBenefit) throws Exception {
        if (optBenefit != null) {
            logger.info(sqlMarker, employeeOptionalBenefitsUpdate);
            logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> optBenefit.getOptSalaryComponent().getOptCompId(), () -> optBenefit.getOptSalaryComponent().getSalOptComponent(), () -> optBenefit.getBenefitValue(),
                    () -> optBenefit.getStartDate(), () -> optBenefit.getStopDate(), () -> optBenefit.getNextDueDate(), () -> optBenefit.getRemarks(), () -> entryBy, () -> optBenefit.getFrequency(), () -> optBenefit.getIterations(), () -> optCompId,
                    () -> employeeId);
            jdbcTemplate.update(employeeOptionalBenefitsInsert, new Object[] { optBenefit.getOptSalaryComponent().getOptCompId(), optBenefit.getOptSalaryComponent().getSalOptComponent(), optBenefit.getBenefitValue(), optBenefit.getStartDate(),
                    optBenefit.getStopDate(), optBenefit.getNextDueDate(), optBenefit.getRemarks(), entryBy, optBenefit.getFrequency(), optBenefit.getIterations(), optCompId, employeeId });
        }
    }

    /** TODO: Confirm logic
     * Update employee_additional_details table
     * @param employeeId
     * @param employeeAddlDetails
     * @throws Exception
     */
    public void updatedEmployeeAddlDetails(String employeeId, String modifiedBy, EmployeeAddlDetails employeeAddlDetails) throws Exception {

        logger.info(sqlMarker, employeeAdditionalDetailsUpdatebyEmpId);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeAddlDetails.getSiblingNo(), () -> employeeAddlDetails.getDependentNo(), () -> employeeAddlDetails.getNomineeName1(),
                () -> employeeAddlDetails.getNomineeName2(), () -> employeeAddlDetails.getNomineeName3(), () -> employeeAddlDetails.getNomineeShare1(), () -> employeeAddlDetails.getNomineeShare2(), () -> employeeAddlDetails.getNomineeShare3(),
                () -> employeeAddlDetails.getEmergencyContactName(), () -> employeeAddlDetails.getEmergencyContactNo(), () -> employeeAddlDetails.getPreMedicalCheckUpDate(), () -> employeeAddlDetails.getMedicalReportComment(), () -> employeeId);
        int numberOfRowsUpdated = jdbcTemplate.update(employeeAdditionalDetailsUpdatebyEmpId,
                new Object[] { employeeAddlDetails.getSiblingNo(), employeeAddlDetails.getDependentNo(), employeeAddlDetails.getNomineeName1(), employeeAddlDetails.getNomineeName2(), employeeAddlDetails.getNomineeName3(),
                        employeeAddlDetails.getNomineeShare1(), employeeAddlDetails.getNomineeShare2(), employeeAddlDetails.getNomineeShare3(), employeeAddlDetails.getEmergencyContactName(), employeeAddlDetails.getEmergencyContactNo(),
                        employeeAddlDetails.getPreMedicalCheckUpDate(), employeeAddlDetails.getMedicalReportComment(), employeeId });

        /* Make entry in employee_additional_details_history 
         * iff there is successful update  in table 
         * employee_additional_details
         * */
        if (numberOfRowsUpdated > 0) {
            logger.info(sqlMarker, employeeAdditionalDetailsHistoryInsert);
            logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeId, () -> employeeAddlDetails.getSiblingNo(), () -> employeeAddlDetails.getDependentNo(), () -> employeeAddlDetails.getNomineeName1(),
                    () -> employeeAddlDetails.getNomineeName2(), () -> employeeAddlDetails.getNomineeName3(), () -> employeeAddlDetails.getNomineeShare1(), () -> employeeAddlDetails.getNomineeShare2(), () -> employeeAddlDetails.getNomineeShare3(),
                    () -> employeeAddlDetails.getEmergencyContactName(), () -> employeeAddlDetails.getEmergencyContactNo(), () -> employeeAddlDetails.getPreMedicalCheckUpDate(), () -> employeeAddlDetails.getMedicalReportComment(), () -> modifiedBy);

            jdbcTemplate.update(employeeAdditionalDetailsHistoryInsert,
                    new Object[] { employeeId, employeeAddlDetails.getSiblingNo(), employeeAddlDetails.getDependentNo(), employeeAddlDetails.getNomineeName1(), employeeAddlDetails.getNomineeName2(), employeeAddlDetails.getNomineeName3(),
                            employeeAddlDetails.getNomineeShare1(), employeeAddlDetails.getNomineeShare2(), employeeAddlDetails.getNomineeShare3(), employeeAddlDetails.getEmergencyContactName(), employeeAddlDetails.getEmergencyContactNo(),
                            employeeAddlDetails.getPreMedicalCheckUpDate(), employeeAddlDetails.getMedicalReportComment(), modifiedBy });

        }

    }

    public void updatedEmployeeAddress(String employeeId, EmployeeAddress employeeAddress) throws Exception {
        logger.info(sqlMarker, employeeAddressUpdatebyEmpId);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeAddress.getHouseNo(), () -> employeeAddress.getStreetName(), () -> employeeAddress.getArea(), () -> employeeAddress.getRegion(), () -> employeeAddress.getPinno(),
                () -> employeeAddress.getDistrictId(), () -> employeeAddress.getStateId(), () -> employeeAddress.getCountryId(), () -> employeeAddress.getDescription(), () -> employeeId);
        jdbcTemplate.update(employeeAddressUpdatebyEmpId, new Object[] { employeeAddress.getHouseNo(), employeeAddress.getStreetName(), employeeAddress.getArea(), employeeAddress.getRegion(), employeeAddress.getPinno(), employeeAddress.getDistrictId(),
                employeeAddress.getStateId(), employeeAddress.getCountryId(), employeeAddress.getDescription(), employeeId });

    }

    public List<Employee.EmployeeSearchResult> searchHierarchyEmployee(String firstName, String middleName, String lastName, String employeeId, String employmentType, String emailId, Integer orgId, Integer unitId, Integer departmentId, Integer jobRoleId,
            Integer designationId, String supervisorFlag, String hrFlag, String supervisorEmailId, String hrEmailId, String sex, Integer identityDocTypeId, String identityNumber, String username) {

        List<String> roles = authRepo.retrieveRoles(username);

        StringBuilder sqlCondition = new StringBuilder();

        if (firstName != null && !firstName.trim().isEmpty()) {
            sqlCondition.append(" AND LOWER(EMPL.EMPLOYEE_FIRST_NAME) = LOWER('").append(firstName.trim()).append("')");
        }
        if (middleName != null && !middleName.trim().isEmpty()) {
            sqlCondition.append(" AND LOWER(EMPL.EMPLOYEE_MIDDLE_NAME) = LOWER('").append(middleName.trim()).append("')");
        }
        if (lastName != null && !lastName.trim().isEmpty()) {
            sqlCondition.append(" AND LOWER(EMPL.EMPLOYEE_LAST_NAME) = LOWER('").append(lastName.trim()).append("')");
        }
        if (employeeId != null && !employeeId.trim().isEmpty()) {
            sqlCondition.append(" AND EMPL.EMP_ID = '").append(employeeId.trim()).append("'");
        }
        if (orgId != null) {
            sqlCondition.append(" AND EMPL.ORG_ID = ").append(orgId.toString());
        }
        if (unitId != null) {
            sqlCondition.append(" AND EMPL.UNIT_ID = ").append(unitId.toString());
        }
        if (departmentId != null) {
            sqlCondition.append(" AND EMPL.DEPARTMENT_ID = ").append(departmentId.toString());
        }
        if (designationId != null) {
            sqlCondition.append(" AND AR.DESIGNATION_ID = ").append(designationId.toString());
        }
        if (emailId != null && !emailId.trim().isEmpty()) {
            sqlCondition.append(" AND LOWER(EMPL.EMAIL_ID) = LOWER('").append(emailId.trim()).append("')");
        }
        if (supervisorEmailId != null && !supervisorEmailId.trim().isEmpty()) {
            sqlCondition.append(" AND LOWER(SUP.EMAIL_ID) = LOWER('").append(supervisorEmailId.trim()).append("')");
        }
        if (hrEmailId != null && !hrEmailId.trim().isEmpty()) {
            sqlCondition.append(" AND LOWER(HR.EMAIL_ID) = LOWER('").append(hrEmailId.trim()).append("')");
        }
        if (sex != null && !sex.trim().isEmpty()) {
            sqlCondition.append(" AND EMPL.SEX = '").append(sex.trim()).append("'");
        }
        if (identityDocTypeId != null) {
            sqlCondition.append(" AND EMPL.IDENTITY_DOC_TYPE_ID = '").append(identityDocTypeId.toString());
        }
        if (identityNumber != null && !identityNumber.trim().isEmpty()) {
            sqlCondition.append(" AND LOWER(EMPL.IDENTITY_NUMBER) = LOWER('").append(identityNumber.trim()).append("')");
        }
        if (hrFlag != null && !hrFlag.trim().isEmpty() && "YES".equals(hrFlag.trim())) {
            sqlCondition.append(" AND EMPL.HR_FLAG = 'X'");
        }
        if (hrFlag != null && !hrFlag.trim().isEmpty() && "NO".equals(hrFlag.trim())) {
            sqlCondition.append(" AND EMPL.HR_FLAG IS NULL");
        }
        if (supervisorFlag != null && !supervisorFlag.trim().isEmpty() && "YES".equals(supervisorFlag.trim())) {
            sqlCondition.append(" AND EMPL.SUPERVISOR_FLAG = 'X'");
        }
        if (supervisorFlag != null && !supervisorFlag.trim().isEmpty() && "NO".equals(supervisorFlag.trim())) {
            sqlCondition.append(" AND EMPL.SUPERVISOR_FLAG IS NULL");
        }
        if (employmentType != null && !employmentType.trim().isEmpty()) {
            sqlCondition.append(" AND EMPL.EMP_TYPE = '").append(employmentType.trim()).append("'");
        }
        sqlCondition.append(" LIMIT " + hierarchySearchLimit);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleList", roles);
        paramMap.put("username", username);

        logger.info(sqlMarker, employeeHierarchySearchSql + sqlCondition.toString());
        logger.info(sqlMarker, "Params {}, {}", () -> roles, () -> username);
        return namedParameterJdbcTemplate.query(employeeHierarchySearchSql + sqlCondition.toString(), paramMap, new EmployeeSearchResultRowMapper());
    }

    public List<Employee.EmployeeSearchResult> autoCompleteEmployee(String attributeName, String attributeValuePrefix, Integer numberOfItems, String username) {
        List<String> roles = authRepo.retrieveRoles(username);

        StringBuilder sqlCondition = new StringBuilder();
        if ("empFirstName".equals(attributeName)) {
            sqlCondition.append(" AND LOWER(EMPL.EMPLOYEE_FIRST_NAME) LIKE LOWER('").append(attributeValuePrefix).append("%')");
        }
        if ("empMiddleName".equals(attributeName)) {
            sqlCondition.append(" AND LOWER(EMPL.EMPLOYEE_MIDDLE_NAME) LIKE LOWER('").append(attributeValuePrefix).append("%')");
        }
        if ("empLastName".equals(attributeName)) {
            sqlCondition.append(" AND LOWER(EMPL.EMPLOYEE_LAST_NAME) LIKE LOWER('").append(attributeValuePrefix).append("%')");
        }
        if ("empEmailId".equals(attributeName)) {
            sqlCondition.append(" AND LOWER(EMPL.EMAIL_ID) LIKE LOWER('").append(attributeValuePrefix).append("%')");
        }
        if ("supervisorEmailId".equals(attributeName)) {
            sqlCondition.append(" AND LOWER(SUP.EMAIL_ID) LIKE LOWER('").append(attributeValuePrefix).append("%')");
        }
        if ("hrEmailId".equals(attributeName)) {
            sqlCondition.append(" AND LOWER(HR.EMAIL_ID) LIKE LOWER('").append(attributeValuePrefix).append("%')");
        }

        sqlCondition.append(" LIMIT " + ((numberOfItems == null) ? autoCompleteLimit : numberOfItems));

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleList", roles);
        paramMap.put("username", username);

        logger.info(sqlMarker, employeeHierarchySearchSql + sqlCondition.toString());
        logger.info(sqlMarker, "Params {}, {}", () -> roles, () -> username);
        return namedParameterJdbcTemplate.query(employeeHierarchySearchSql + sqlCondition.toString(), paramMap, new EmployeeSearchResultRowMapper());

    }

    public boolean isPrivilegedForHierarchySearch(String username) {
        List<String> roles = authRepo.retrieveRoles(username);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleList", roles);
        paramMap.put("username", username);
        logger.info(sqlMarker, empSearchPrevilegeDetermineSql);
        logger.info(sqlMarker, "Params {}, {}", () -> roles, () -> username);
        List<Integer> objectList = namedParameterJdbcTemplate.query(empSearchPrevilegeDetermineSql, paramMap, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowum) throws SQLException {
                return rs.getInt(1);
            }
        });
        return objectList.get(0) > 0 ? true : false;
    }

    public void updatedEmployeeHierarchyStatus(String employeeId, String modifiedBy, EmployeeHierarchy employeeHierarchy) throws Exception {
        logger.info(sqlMarker, empHierStatusUpdateByEmpId);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeHierarchy.getSupervisorId(), () -> employeeHierarchy.getHrId(), () -> employeeHierarchy.getStatus(), () -> employeeHierarchy.getCl(),
                () -> employeeHierarchy.getPl(), () -> employeeHierarchy.getPaternityLeave(), () -> employeeHierarchy.getSickLeave(), () -> employeeHierarchy.getMaternityLeave(), () -> employeeHierarchy.getSpecialLeave(),
                () -> employeeHierarchy.getProbationPeriodEndDate(), () -> employeeHierarchy.getNoticePeriodEndDate(), () -> employeeId);
        int numberOfRowsUpdated = jdbcTemplate.update(empHierStatusUpdateByEmpId,
                new Object[] { employeeHierarchy.getSupervisorId(), employeeHierarchy.getHrId(), employeeHierarchy.getStatus(), employeeHierarchy.getCl(), employeeHierarchy.getPl(), employeeHierarchy.getPaternityLeave(), employeeHierarchy.getSickLeave(),
                        employeeHierarchy.getMaternityLeave(), employeeHierarchy.getSpecialLeave(), employeeHierarchy.getProbationPeriodEndDate(), employeeHierarchy.getNoticePeriodEndDate(), employeeId });

        /* Make entry in employee_hierarchy_status_history 
         * iff there is successful update  in table 
         * employee_hierarchy_status
         * */
        if (numberOfRowsUpdated > 0) {
            logger.info(sqlMarker, empHierStatusHistoryInsert);
            logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeId, () -> employeeHierarchy.getSupervisorId(), () -> employeeHierarchy.getHrId(), () -> employeeHierarchy.getStatus(),
                    () -> employeeHierarchy.getCl(), () -> employeeHierarchy.getPl(), () -> employeeHierarchy.getPaternityLeave(), () -> employeeHierarchy.getSickLeave(), () -> employeeHierarchy.getMaternityLeave(), () -> employeeHierarchy.getSpecialLeave(),
                    () -> employeeHierarchy.getProbationPeriodEndDate(), () -> employeeHierarchy.getNoticePeriodEndDate(), () -> modifiedBy

            );
            jdbcTemplate.update(empHierStatusHistoryInsert,
                    new Object[] { employeeId, employeeHierarchy.getSupervisorId(), employeeHierarchy.getHrId(), employeeHierarchy.getStatus(), employeeHierarchy.getCl(), employeeHierarchy.getPl(), employeeHierarchy.getPaternityLeave(),
                            employeeHierarchy.getSickLeave(), employeeHierarchy.getMaternityLeave(), employeeHierarchy.getSpecialLeave(), employeeHierarchy.getProbationPeriodEndDate(), employeeHierarchy.getNoticePeriodEndDate(), modifiedBy });
        }

    }

    public void updatedEmployeeBasicInfo(String employeeId, String modifiedBy, EmployeeBasicInfo employeeBasicInfo) throws Exception {
        logger.info(sqlMarker, empBasicInfoUpdateByEmpId);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeId, () -> employeeBasicInfo.getTitle(), () -> employeeBasicInfo.getEmpFirstName(),
                () -> employeeBasicInfo.getEmpMiddleName(), () -> employeeBasicInfo.getEmpLastName(), () -> employeeBasicInfo.getSex(), () -> employeeBasicInfo.getEmpType(), () -> employeeBasicInfo.getMaritalStatus(), () -> employeeBasicInfo.getDoj(),
                () -> employeeBasicInfo.getOrganizationId(), () -> employeeBasicInfo.getUnit() != null ? employeeBasicInfo.getUnit().getUnitId() : null,
                () -> employeeBasicInfo.getDepartment() != null ? employeeBasicInfo.getDepartment().getDepartmentId() : null, () -> employeeBasicInfo.getNationality(),
                () -> employeeBasicInfo.getIdentityDocType() != null ? employeeBasicInfo.getIdentityDocType().getDocTypeId() : null, () -> employeeBasicInfo.getIdentityNumber(), () -> employeeBasicInfo.getDob(), () -> employeeBasicInfo.getFatherName(),
                () -> employeeBasicInfo.getEmailId(), () -> employeeBasicInfo.getContactNo(), () -> employeeBasicInfo.getEntryBy(), () -> employeeBasicInfo.getEntryDate(), () -> employeeBasicInfo.isHrFlag() ? "X" : null,
                () -> employeeBasicInfo.isSupervisorFlag() ? "X" : null, () -> employeeId

        );
        int numberOfRowsUpdated = jdbcTemplate.update(empBasicInfoUpdateByEmpId,
                new Object[] { employeeId, employeeBasicInfo.getTitle(), employeeBasicInfo.getEmpFirstName(), employeeBasicInfo.getEmpMiddleName(), employeeBasicInfo.getEmpLastName(), employeeBasicInfo.getSex(), employeeBasicInfo.getEmpType(),
                        employeeBasicInfo.getMaritalStatus(), employeeBasicInfo.getDoj(), employeeBasicInfo.getOrganizationId(), employeeBasicInfo.getUnit() != null ? employeeBasicInfo.getUnit().getUnitId() : null,
                        employeeBasicInfo.getDepartment() != null ? employeeBasicInfo.getDepartment().getDepartmentId() : null, employeeBasicInfo.getNationality(),
                        employeeBasicInfo.getIdentityDocType() != null ? employeeBasicInfo.getIdentityDocType().getDocTypeId() : null, employeeBasicInfo.getIdentityNumber(), employeeBasicInfo.getDob(), employeeBasicInfo.getFatherName(),
                        employeeBasicInfo.getEmailId(), employeeBasicInfo.getContactNo(), employeeBasicInfo.getEntryBy(), employeeBasicInfo.getEntryDate(), employeeBasicInfo.isHrFlag() ? "X" : null, employeeBasicInfo.isSupervisorFlag() ? "X" : null, employeeId

        });

        /*
         * Make entry in employee_history iff there is successful update in
         * table employee
         */
        if (numberOfRowsUpdated > 0) {
            logger.info(sqlMarker, empInsertEmployeeHistory);
            logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", () -> employeeId, () -> employeeBasicInfo.getTitle(), () -> employeeBasicInfo.getEmpFirstName(),
                    () -> employeeBasicInfo.getEmpMiddleName(), () -> employeeBasicInfo.getEmpLastName(), () -> employeeBasicInfo.getSex(), () -> employeeBasicInfo.getEmpType(), () -> employeeBasicInfo.getMaritalStatus(), () -> employeeBasicInfo.getDoj(),

            () -> employeeBasicInfo.getOrganizationId(), () -> employeeBasicInfo.getUnit() != null ? employeeBasicInfo.getUnit().getUnitId() : null,
                    () -> employeeBasicInfo.getDepartment() != null ? employeeBasicInfo.getDepartment().getDepartmentId() : null, () -> employeeBasicInfo.getNationality(),
                    () -> employeeBasicInfo.getIdentityDocType() != null ? employeeBasicInfo.getIdentityDocType().getDocTypeId() : null, () -> employeeBasicInfo.getIdentityNumber(), () -> employeeBasicInfo.getDob(), () -> employeeBasicInfo.getFatherName(),
                    () -> employeeBasicInfo.getEmailId(), () -> employeeBasicInfo.getContactNo(), () -> employeeBasicInfo.getEntryBy(), () -> employeeBasicInfo.getEntryDate(), () -> employeeBasicInfo.isHrFlag() ? "X" : null,
                    () -> employeeBasicInfo.isSupervisorFlag() ? "X" : null, () -> modifiedBy

            );
            jdbcTemplate.update(empInsertEmployeeHistory,
                    new Object[] { employeeId, employeeBasicInfo.getTitle(), employeeBasicInfo.getEmpFirstName(), employeeBasicInfo.getEmpMiddleName(), employeeBasicInfo.getEmpLastName(), employeeBasicInfo.getSex(), employeeBasicInfo.getEmpType(),
                            employeeBasicInfo.getMaritalStatus(), employeeBasicInfo.getDoj(), employeeBasicInfo.getOrganizationId(), employeeBasicInfo.getUnit() != null ? employeeBasicInfo.getUnit().getUnitId() : null,
                            employeeBasicInfo.getDepartment() != null ? employeeBasicInfo.getDepartment().getDepartmentId() : null, employeeBasicInfo.getNationality(),
                            employeeBasicInfo.getIdentityDocType() != null ? employeeBasicInfo.getIdentityDocType().getDocTypeId() : null, employeeBasicInfo.getIdentityNumber(), employeeBasicInfo.getDob(), employeeBasicInfo.getFatherName(),
                            employeeBasicInfo.getEmailId(), employeeBasicInfo.getContactNo(), employeeBasicInfo.getEntryBy(), employeeBasicInfo.getEntryDate(), employeeBasicInfo.isHrFlag() ? "X" : null, employeeBasicInfo.isSupervisorFlag() ? "X" : null,
                            modifiedBy

            });
        }

    }

    public int insertEmployeeImage(String employeeImage, String employeeId) throws Exception {

        logger.info(sqlMarker, updateEmployeeImageByEmpId);
        logger.info(sqlMarker, "Params {}, {}", () -> employeeImage, () -> employeeId);
        int rowsAffected = jdbcTemplate.update(updateEmployeeImageByEmpId, new Object[] { employeeImage, employeeId });
        return rowsAffected;

    }
    
    public Employee getEmployeeDetailsByEmpId(String employeeId) throws Exception {
        Object[] args = { employeeId };
        logger.info(sqlMarker, getEmployeeDetailsByEmpId);
        logger.info(sqlMarker, "Params {}", () -> employeeId);
        
        Employee employeeInfo = (Employee) jdbcTemplate.queryForObject(getEmployeeDetailsByEmpId, args, new EmployeeRowMapper());
        logger.debug("Retrieved Employee Details: {}", () -> employeeInfo.toString());
        return employeeInfo;
    }
    
    public byte[] getEmployeeImageByEmpId(String employeeId) throws Exception {
        Object[] args = { employeeId };
        logger.info(sqlMarker, getEmployeeImageByEmpId);
        logger.info(sqlMarker, "Params {}", () -> employeeId);
        //File file = new File("/Users/isomdeb/Pictures/For-Employees.jpg");
        return jdbcTemplate.queryForObject(getEmployeeImageByEmpId, args, new EmployeeImageFileMapper());
    }

    public void createDocument(String employeeId, EmployeeDocument empDoc) {
        Object[] args = { employeeId, empDoc.getRemarks(), empDoc.getDocTypeId(), empDoc.getDocTypeName(), empDoc.getDocument() };
        logger.info(sqlMarker, insertEmployeeDocument);
        logger.info(sqlMarker, "Params {}", () -> employeeId, () -> empDoc.getDocTypeId(), () -> empDoc.getRemarks(), () -> empDoc.getDocument());
        jdbcTemplate.update(insertEmployeeDocument, args);
    }

    public List<EmployeeDocument> getAllDocuments(String employeeId) {
        Object[] args = { employeeId };
        logger.info(sqlMarker, selectAllDocuments);
        logger.info(sqlMarker, "Params {}", () -> employeeId);
        return jdbcTemplate.query(selectAllDocuments, args, new DocumentRowMapper());
    }

    public EmployeeDocument getDocument(int docId) {
        Object[] args = { docId };
        logger.info(sqlMarker, selectDocument);
        logger.info(sqlMarker, "Params {}", () -> docId);
        return jdbcTemplate.queryForObject(selectDocument, args, new DocumentRowMapper());
    }

    public void updateDocument(EmployeeDocument empDoc) {
        Object[] args = { empDoc.getRemarks(), empDoc.getDocTypeId(), empDoc.getDocument(), empDoc.getDocId() };
        logger.info(sqlMarker, updateEmployeeDocument);
        logger.info(sqlMarker, "Params {}", () -> empDoc.getRemarks(), () -> empDoc.getDocTypeId(), () -> empDoc.getDocument(), () -> empDoc.getDocId());
        jdbcTemplate.update(updateEmployeeDocument, args);
    }
}