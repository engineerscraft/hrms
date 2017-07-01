package com.hamdard.hua.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.model.Employee.EmployeeAddlDetails;
import com.hamdard.hua.model.Employee.EmployeeAddress;
import com.hamdard.hua.model.Employee.EmployeeBasicInfo;
import com.hamdard.hua.model.Employee.EmployeeHierarchy;
import com.hamdard.hua.model.Employee.EmployeeOptionalBenefit;
import com.hamdard.hua.model.Employee.EmployeeProfile;
import com.hamdard.hua.model.Employee.EmployeeSalary;
import com.hamdard.hua.model.Unit;

/**
 * @author Jyotirmoy Banerjee
 * 
 */

@Component
public class EmployeeRepository {
    private static final Logger logger    = LogManager.getLogger(EmployeeRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    /***************************************** AUTOWIRED COMPONENTS **************************************/
    @Autowired
    private JdbcTemplate                jdbcTemplate;

    @Autowired
    private UnitRepository              unitRepo;
    
    @Autowired
    private AuthenticationRepository    authRepo;

    /**************************************** AUTOWIRED PROPERTIES**************************************/

    @Value("${sql.employee.nextId}")
    private String              obtainEmployeeIdFrmSeq;
    
    @Value("${employeeIdNumericLength}")
    private String              employeeIdNumericLength;
    
    @Value("${sql.employee.insert.basicInfo}")
    private String              employeeBasicInfoInsert;
    
    @Value("${sql.employee.insert.addnDetails}")
    private String              employeeAdditionalDtlInsert;
    
    @Value("${sql.employee.insert.address}")
    private String              employeeAddressInsert;
    
    @Value("${sql.employee.insert.hierarchy}")
    private String              employeeHierarchyInsert;
    
    @Value("${sql.employee.insert.profile}")
    private String              employeeProfileInsert;
    
    @Value("${sql.employee.insert.employee.salary}")
    private String              employeeSalaryInsert;
    
    @Value("${sql.employee.insert.employee.optional.benefit}")
    private String              employeeOptionalBenefitsInsert;
    
    @Value("${sql.employee.get.appraisal.id}")
    private String              getAppraisalId;
    
    /******************* Update Operations *******************/
    
    @Value("${sql.employee.update.employee.salary}")
    private String              employeeSalaryUpdate;
    
    @Value("${sql.employee.update.profile}")
    private String              employeeProfileUpdate;

    /*****************************************************************************************************/
    
    @Transactional
    public void createEmployee(Employee newEmployee) throws Exception {
        String employeeId           = this.generateEmployeeId(newEmployee.getEmployeeBasicInfo().getUnit());
        
        String entryDate            = newEmployee.getEmployeeBasicInfo().getEntryDate();
        String entryBy              = newEmployee.getEmployeeBasicInfo().getEntryBy();
        
        this.insertBasicInfo            (employeeId,        newEmployee.getEmployeeBasicInfo());
        this.insertAdditionalInfo       (employeeId,        newEmployee.getEmployeeAddlDetails());
        this.insertEmployeeAddress      (employeeId,        newEmployee.getEmployeeAddress());
        this.insertEmployeeHierarchy    (employeeId,        newEmployee.getEmployeeHierarchy(), entryDate);
        this.insertEmployeeProfile      (employeeId,        newEmployee.getEmployeeProfile());
        this.insertEmpSalaryComponents  (employeeId,        entryBy, 
                                                            newEmployee.getEmployeeSalary(), entryDate);
        //TODO: take care of optional components
        
        //create LDAP user
        authRepo.createUser(employeeId, newEmployee);
    }
    
    /**TODO: Confirm logic
     * Insert the salary components
     * @param employeeId
     * @param entryBy
     * @param salaryComponents
     * @throws Exception
     */
    private void insertEmpSalaryComponents(String employeeId, String entryBy, List<EmployeeSalary> salaryComponents, 
            String entryDate) throws Exception {
        if(salaryComponents != null)
            for(EmployeeSalary salary: salaryComponents){
                long appraisalId        = this.getAppraisalId(entryDate);
                logger.info(sqlMarker, employeeSalaryInsert);
                logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}",
                        () -> appraisalId,
                        () -> employeeId,
                        () -> salary.getSalaryComponent().getCompId(),
                        () -> entryBy,
                        () -> entryDate,
                        () -> salary.getSalaryValue());
                jdbcTemplate.update(employeeSalaryInsert, new Object[] {
                        appraisalId,
                        employeeId,
                        salary.getSalaryComponent().getCompId(),
                        entryBy,
                        entryDate,
                        salary.getSalaryValue()
                });
            }
    }
    
    private long getAppraisalId(String  entryDate) throws Exception{
        logger.info(sqlMarker, getAppraisalId);
        logger.info(sqlMarker, "Params {}, {}",
                entryDate,
                entryDate);
        Long appraisalId        = null;
        try{
            appraisalId         = jdbcTemplate.queryForObject(getAppraisalId, 
                new Object []{entryDate, entryDate}, Long.class);
        }catch(Exception ex){
            logger.debug("Given entryDate {} does not correspond to any appraisal cycle",
                    () -> entryDate);
            throw new Exception("Given entryDate " + entryDate + " does not correspond to any appraisal cycle");
        }
        return appraisalId;
    }
    
    /** TODO: Confirm logic
     * Insert into optional salary components table
     * @param employeeId
     * @param optBenefits
     * @throws Exception
     */
    private void insertEmpOptionalBenefits(String employeeId, String entryBy, List<EmployeeOptionalBenefit> optBenefits) throws Exception {
        if(optBenefits != null)
            for(EmployeeOptionalBenefit optBenefit : optBenefits) {
                logger.info(sqlMarker, employeeOptionalBenefitsInsert);
                logger.info(sqlMarker, "Params {}, {}, {}, {}, {}",
                        () -> null,
                        () -> employeeId,
                        () -> optBenefit.getOptSalaryComponent().getOptCompId(),
                        () -> optBenefit.getOptSalaryComponent().getSalOptComponent(),
                        () -> optBenefit.getBenefitValue(),
                        () -> optBenefit.getStartDate(),
                        () -> optBenefit.getStopDate(),
                        () -> optBenefit.getNextDueDate(),
                        () -> null,
                        () -> entryBy,
                        () -> optBenefit.getFrequency(),
                        () -> optBenefit.getIterations());
                jdbcTemplate.update(employeeOptionalBenefitsInsert, new Object[] {
                        null,
                        employeeId,
                        optBenefit.getOptSalaryComponent().getOptCompId(),
                        optBenefit.getOptSalaryComponent().getSalOptComponent(),
                        optBenefit.getBenefitValue(),
                        optBenefit.getStartDate(),
                        optBenefit.getStopDate(),
                        optBenefit.getNextDueDate(),
                        null,
                        entryBy,
                        optBenefit.getFrequency(),
                        optBenefit.getIterations()
                });
            }
    }
    
    /**
     * Insert employee profile
     * @param employeeId
     * @param profile
     * @throws Exception
     */
    private void insertEmployeeProfile(String employeeId, EmployeeProfile profile) throws Exception {
        logger.info(sqlMarker, employeeProfileInsert);
        logger.info(sqlMarker, "Params {}, {}, {}, {}",
                () -> employeeId,
                () -> profile.getQualification(),
                () -> profile.getDescription(),
                () -> profile.getComments());

        jdbcTemplate.update(employeeProfileInsert, new Object[] {
                employeeId,
                profile.getQualification(),
                profile.getDescription(),
                profile.getComments()
        });
    }
    
    /**
     * Insert employee hierarchy
     * @param employeeId
     * @param hierarchy
     * @throws Exception
     */
    private void insertEmployeeHierarchy(String employeeId, EmployeeHierarchy hierarchy, String entryDate) throws Exception {
        logger.info(sqlMarker, employeeHierarchyInsert);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
                () -> employeeId,
                () -> hierarchy.getSupervisorId(),
                () -> hierarchy.getHrId(),
                () -> hierarchy.getStatus(),
                () -> hierarchy.getCl(),
                () -> hierarchy.getPl(),
                () -> hierarchy.getPaternityLeave(),
                () -> hierarchy.getSickLeave(),
                () -> hierarchy.getMaternityLeave(),
                () -> hierarchy.getSpecialLeave(),
                () -> hierarchy.getProbationPeriodEndDate(),
                () -> hierarchy.getNoticePeriodEndDate(),
                () -> entryDate);

        jdbcTemplate.update(employeeHierarchyInsert, new Object[] {
                employeeId,
                hierarchy.getSupervisorId(),
                hierarchy.getHrId(),
                hierarchy.getStatus(),
                hierarchy.getCl(),
                hierarchy.getPl(),
                hierarchy.getPaternityLeave(),
                hierarchy.getSickLeave(),
                hierarchy.getMaternityLeave(),
                hierarchy.getSpecialLeave(),
                hierarchy.getProbationPeriodEndDate(),
                hierarchy.getNoticePeriodEndDate(),
                entryDate
        });
    }
    
    /**
     * Insert employee address
     * @param employeeId
     * @param address
     * @throws Exception
     */
    private void insertEmployeeAddress(String employeeId, List<EmployeeAddress> addressList) throws Exception {
        if(addressList != null)
            for(EmployeeAddress address : addressList){
                logger.info(sqlMarker, employeeAddressInsert);
                logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
                        () -> employeeId,
                        () -> address.getAddressType(),
                        () -> address.getHouseNo(),
                        () -> address.getStreetName(),
                        () -> address.getArea(),
                        () -> address.getRegion(),
                        () -> address.getPinno(),
                        () -> address.getDistrictId(),
                        () -> address.getStateId(),
                        () -> address.getCountryId(),
                        () -> address.getDescription());

                jdbcTemplate.update(employeeAddressInsert, new Object[] {
                        employeeId,
                        address.getAddressType(),
                        address.getHouseNo(),
                        address.getStreetName(),
                        address.getArea(),
                        address.getRegion(),
                        address.getPinno(),
                        address.getDistrictId(),
                        address.getStateId(),
                        address.getCountryId(),
                        address.getDescription()
                });
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
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, "
                + "{}, {}, {}, {}, {}, "
                + "{}, {}, {}",
                () -> employeeId,
                () -> additonalDetails.getSiblingNo(),
                () -> additonalDetails.getDependentNo(),
                () -> additonalDetails.getNomineeName1(),
                () -> additonalDetails.getNomineeName2(),
                () -> additonalDetails.getNomineeName3(),
                () -> additonalDetails.getNomineeShare1(),
                () -> additonalDetails.getNomineeShare2(),
                () -> additonalDetails.getNomineeShare3(),
                () -> additonalDetails.getEmergencyContactName(),
                () -> additonalDetails.getEmergencyContactNo(),
                () -> additonalDetails.getPreMedicalCheckUpDate(),
                () -> additonalDetails.getMedicalReportComment());
        
        jdbcTemplate.update(employeeAdditionalDtlInsert, new Object[] {
                employeeId, 
                additonalDetails.getSiblingNo(),
                additonalDetails.getDependentNo(),
                additonalDetails.getNomineeName1(),
                additonalDetails.getNomineeName2(),
                additonalDetails.getNomineeName3(),
                additonalDetails.getNomineeShare1(),
                additonalDetails.getNomineeShare2(),
                additonalDetails.getNomineeShare3(),
                additonalDetails.getEmergencyContactName(),
                additonalDetails.getEmergencyContactNo(),
                additonalDetails.getPreMedicalCheckUpDate(),
                additonalDetails.getMedicalReportComment()
                });
    }
    
    /**
     * Insertion into the EMPLOYEE
     * @param empNo
     * @param basicInfo
     * @throws Exception
     */
    private void insertBasicInfo(String employeeId, EmployeeBasicInfo basicInfo) throws Exception {
        
        logger.info(sqlMarker, employeeBasicInfoInsert);
        logger.info(sqlMarker, "Params {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, "
                + "{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, "
                + "{}, {}, {}",
                () -> employeeId,
                () -> basicInfo.getTitle(),
                () -> basicInfo.getEmpFirstName(),
                () -> basicInfo.getEmpMiddleName(),
                () -> basicInfo.getEmpLastName(),
                () -> basicInfo.getSex(),
                () -> basicInfo.getEmpType(),
                () -> basicInfo.getMaritalStatus(),
                () -> basicInfo.getDoj(),
                () -> basicInfo.getUnit() != null? basicInfo.getUnit().getOrgId(): null,
                () -> basicInfo.getUnit() != null? basicInfo.getUnit().getUnitId(): null,
                () -> basicInfo.getDepartment() != null? basicInfo.getDepartment().getDepartmentId(): null,
                () -> basicInfo.getNationality(),
                () -> basicInfo.getIdentityDocType() != null? basicInfo.getIdentityDocType().getDocTypeId() : null,
                () -> basicInfo.getIdentityNumber(),
                () -> basicInfo.getDob(),
                () -> basicInfo.getFatherName(),
                () -> basicInfo.getEmailId(),
                () -> basicInfo.getContactNo(),
                () -> basicInfo.getEntryBy(),
                () -> basicInfo.getEntryDate(),
                () -> basicInfo.isHrFlag(),
                () -> basicInfo.isSupervisorFlag());
        
        jdbcTemplate.update(employeeBasicInfoInsert, new Object[] {
                employeeId, 
                basicInfo.getTitle(),
                basicInfo.getEmpFirstName(),
                basicInfo.getEmpMiddleName(),
                basicInfo.getEmpLastName(),
                basicInfo.getSex(),
                basicInfo.getEmpType(),
                basicInfo.getMaritalStatus(),
                basicInfo.getDoj(),
                basicInfo.getUnit() != null? basicInfo.getUnit().getOrgId(): null,
                basicInfo.getUnit() != null? basicInfo.getUnit().getUnitId(): null,
                basicInfo.getDepartment() != null? basicInfo.getDepartment().getDepartmentId(): null,
                basicInfo.getNationality(),
                basicInfo.getIdentityDocType() != null? basicInfo.getIdentityDocType().getDocTypeId() : null,
                basicInfo.getIdentityNumber(),
                basicInfo.getDob(),
                basicInfo.getFatherName(),
                basicInfo.getEmailId(),
                basicInfo.getContactNo(),
                basicInfo.getEntryBy(),
                basicInfo.getEntryDate(),
                basicInfo.isHrFlag(),
                basicInfo.isSupervisorFlag()
                });
    }
    
    /**
     * Generates an employee number for the insert operation
     * @param unit
     * @return the complete employee number generated for the given unit
     * @throws Exception
     */
    private String generateEmployeeId(Unit unit) throws Exception {
        List<Unit> listOfUnits          = unitRepo.getUnitsByOrganizationId(unit.getOrgId());
        if(listOfUnits == null || listOfUnits.size() != 1)
            throw new Exception("The Unit provided as an iput does not match to an unique value in the DB!");

        String prefix                   = listOfUnits.get(0).getEmpIdPrefix();
        String seqName                  = listOfUnits.get(0).getEmpIdSeqName();
        
        logger.info(sqlMarker, obtainEmployeeIdFrmSeq);
        logger.info(sqlMarker, "Params {}", () -> obtainEmployeeIdFrmSeq);
        
        Long empIdObtainedNo            = jdbcTemplate.queryForObject(obtainEmployeeIdFrmSeq, 
                                                new Object[] {seqName}, Long.class);
        String paddedEmpNo              = StringUtils.leftPad(String.valueOf(empIdObtainedNo), 
                                                Integer.valueOf(employeeIdNumericLength), '0');
        String completeEmpNo            = prefix + paddedEmpNo;
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
    
    public void updateEmpSalaryComponents(String employeeId, String entryBy,
            List<EmployeeSalary> salaryComponents) throws Exception {
        if(salaryComponents != null)
            for(EmployeeSalary salary: salaryComponents){
                logger.info(sqlMarker, employeeSalaryUpdate);
                logger.info(sqlMarker, "Params {}, {}, {}, {}",
                        () -> salary.getSalaryValue(),
                        () -> entryBy,
                        () -> employeeId,
                        () -> salary.getSalaryComponent().getCompId());
                jdbcTemplate.update(employeeSalaryUpdate, new Object[] {
                        salary.getSalaryValue(),
                        entryBy,
                        employeeId,
                        salary.getSalaryComponent().getCompId()
                });
            }
    }
    
    /**
     * Update Employee Profile
     * @param employeeId
     * @param profile
     * @throws Exception
     */
    
    public void updateEmployeeProfile(String employeeId, EmployeeProfile profile) throws Exception {
        logger.info(sqlMarker, employeeProfileUpdate);
        logger.info(sqlMarker, "Params {}, {}, {}, {}",
                () -> profile.getQualification(),
                () -> profile.getDescription(),
                () -> profile.getComments(),
                () -> employeeId);

        jdbcTemplate.update(employeeProfileInsert, new Object[] {
                profile.getQualification(),
                profile.getDescription(),
                profile.getComments(),
                employeeId
        });
    }
}


