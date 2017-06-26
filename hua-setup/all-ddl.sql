CREATE USER HUAAPPUSER IDENTIFIED BY 'HUAAPPUSER';
CREATE DATABASE HUA;
USE HUA;
GRANT ALL PRIVILEGES ON HUA.* TO HUAAPPUSER@'localhost';

CREATE TABLE APP_SEQUENCE (
  SEQ_NAME VARCHAR(20) NOT NULL,
  SEQ_VALUE BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (SEQ_NAME)
);

DELIMITER $$

CREATE FUNCTION GET_SEQUENCE_NEXT_VAL(NAME VARCHAR(20)) 
RETURNS BIGINT
BEGIN
 UPDATE APP_SEQUENCE SET SEQ_VALUE=LAST_INSERT_ID(SEQ_VALUE+1) 
 WHERE SEQ_NAME=NAME;
 
 RETURN LAST_INSERT_ID();
END$$

DELIMITER ;

CREATE TABLE ACCESS_TOKEN (
  ACCESS_ID BIGINT,
  INSERT_DTM DATETIME,
  USER_NAME VARCHAR(30),
  EXPIRY_DTM DATETIME,
  REFRESH_COUNT INT,
  LAST_REFRESH_DTM DATETIME
);

CREATE TABLE ACTIVITY_HISTORY (
  INSERT_DTM DATETIME,
  USER_NAME VARCHAR(30),
  MESSAGE VARCHAR(4000)
);

CREATE TABLE GL_ACCOUNT (
  ACCOUNT_CODE VARCHAR(20),
  ACCOUNT_NAME VARCHAR(100),
  ACCOUNT_TYPE VARCHAR(15),
  CHECK (ACCOUNT_TYPE IN ('Asset', 'Liability', 'Equity', 'Income', 'Expense')),
  PRIMARY KEY (ACCOUNT_CODE)
);

CREATE TABLE GL_ACCOUNT_HISTORY (
  HISTID INT NOT NULL AUTO_INCREMENT,
  INSERT_DTM DATETIME,
  USERNAME CHAR(20),
  OPERATION CHAR(10) CHECK (OPERATION IN ('INSERT', 'DELETE', 'UPDATE')),
  ACCOUNT_CODE VARCHAR(20),
  ACCOUNT_NAME VARCHAR(100),
  ACCOUNT_TYPE VARCHAR(15),
  CHECK (ACCOUNT_TYPE IN ('Asset', 'Liability', 'Equity', 'Income', 'Expense')),
  PRIMARY KEY (histid)
);

CREATE TABLE EMPLOYEE(
	ID INT NOT NULL AUTO_INCREMENT,
	FIRST_NAME VARCHAR(32) NOT NULL,
	MIDDLE_NAME VARCHAR(32) NULL,
	LAST_NAME VARCHAR(32) NOT NULL,
	EMAIL_ADDRESS VARCHAR(64) NOT NULL,
	COLLEGE_ID INT NOT NULL,
	STREET_ADDRESS VARCHAR (256) NOT NULL,
	CITY_ID INT NOT NULL,
	POSTAL_CODE VARCHAR(10) NOT NULL,
	CONTACT_NUMBER VARCHAR(20) NOT NULL,
	EMERGENCY_CONTACT_NAME VARCHAR(128) NOT NULL,
	EMERGENCY_CONTACT_NUMBER VARCHAR(20) NOT NULL,
	DESIGNATION_ID INT NOT NULL,
	DEPARTMENT_ID INT NOT NULL,
	DATE_OF_BIRTH DATE NOT NULL,
	QUALIFICATION VARCHAR(64) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE COLLEGE (
ID INT,
NAME VARCHAR(50)
);

CREATE TABLE DEPARTMENT (
ID INT,
NAME VARCHAR(50)
);

CREATE TABLE DESIGNATION (
ID INT,
NAME VARCHAR(50)
);

CREATE USER HRMSADM@'localhost' IDENTIFIED BY 'HRMSADM';
CREATE USER HRMSADM@'127.0.0.1' IDENTIFIED BY 'HRMSADM';
CREATE USER HRMSADM@'%' IDENTIFIED BY 'HRMSADM';
CREATE DATABASE HRMS;
USE HRMS;
GRANT ALL PRIVILEGES ON *.* TO HRMSADM@'localhost';
GRANT ALL PRIVILEGES ON *.* TO HRMSADM@'127.0.0.1';
GRANT ALL PRIVILEGES ON *.* TO HRMSADM@'%';

CREATE TABLE APP_SEQUENCE (
  SEQ_NAME VARCHAR(20) NOT NULL,
  SEQ_VALUE BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (SEQ_NAME)
);

DELIMITER $$

CREATE FUNCTION GET_SEQUENCE_NEXT_VAL(NAME VARCHAR(20)) 
RETURNS BIGINT
BEGIN
 UPDATE APP_SEQUENCE SET SEQ_VALUE=LAST_INSERT_ID(SEQ_VALUE+1) 
 WHERE SEQ_NAME=NAME;
 
 RETURN LAST_INSERT_ID();
END$$

DELIMITER ;

CREATE TABLE ACCESS_TOKEN (
  ACCESS_ID BIGINT,
  INSERT_DATE DATETIME,
  USER_NAME VARCHAR(30),
  EXPIRY_DATE DATETIME,
  REFRESH_COUNT INT,
  LAST_REFRESH_DATE DATETIME
);

CREATE TABLE ORGANISATION_TYPE_MASTER (
	ORG_TYPE_ID INT(32) PRIMARY KEY,
	ORG_TYPE_NAME	VARCHAR(100),
	DESCRIPTION	VARCHAR(200)
);

CREATE TABLE ORGANISATION_MASTER (		
	ORG_ID	INT(32)	PRIMARY KEY,
	ORG_NAME	VARCHAR(100),
	ORG_TYPE_ID	INT(32),	
	ADDRESS	VARCHAR(256),
	FOREIGN KEY(ORG_TYPE_ID) REFERENCES ORGANISATION_TYPE_MASTER(ORG_TYPE_ID)
);

CREATE TABLE UNIT_MASTER (		
	UNIT_ID	INT(32) PRIMARY KEY,
	UNIT_NAME VARCHAR(100),
	ORG_ID INT(32),
	EMP_ID_PREFIX VARCHAR(10),
	EMP_ID_SEQ_NAME VARCHAR(20),
	ADDRESS	VARCHAR(256),
	FOREIGN KEY(ORG_ID) REFERENCES ORGANISATION_MASTER(ORG_ID)
);

CREATE TABLE DEPARTMENT_MASTER (	
	DEPARTMENT_ID	INT(32)	PRIMARY KEY,
	DEPARTMENT_NAME VARCHAR(100),
	UNIT_ID	INT(32),	
	ADDRESS	VARCHAR(256),
	PARENT_DEPARTMENT_ID INT(32),
	FOREIGN KEY(UNIT_ID) REFERENCES UNIT_MASTER(UNIT_ID)
);

CREATE TABLE GRADE_MASTER (	
	GRADE_ID INT(32),
	GRADE	VARCHAR(32),
	PRIMARY KEY(GRADE_ID)
);

CREATE TABLE DESIGNATION_MASTER (	
	DESIGNATION_ID INT(32),
	DESIGNATION VARCHAR(32),
	PRIMARY KEY(DESIGNATION_ID)
);

CREATE TABLE JOB_ROLE_MASTER (
	JOB_ROLE_ID INT(32),
	ORG_ID INT(32) NOT NULL,
	GRADE_ID INT(32) NOT NULL,
	PROBATION_NOTICE_PERIOD	INT(32),
	NOTICE_PERIOD	INT(32),
	PRIMARY KEY(JOB_ROLE_ID),
	UNIQUE KEY(ORG_ID, GRADE_ID),
	FOREIGN KEY(ORG_ID) REFERENCES ORGANISATION_MASTER(ORG_ID),
	FOREIGN KEY(GRADE_ID) REFERENCES GRADE_MASTER(GRADE_ID)
);

CREATE TABLE JOB_ROLE_DESIGNATION(
	JOB_ROLE_ID INT(32),
	DESIGNATION_ID INT(32),
	PRIMARY KEY(JOB_ROLE_ID, DESIGNATION_ID),
	FOREIGN KEY(DESIGNATION_ID) REFERENCES DESIGNATION_MASTER(DESIGNATION_ID),
	FOREIGN KEY(JOB_ROLE_ID) REFERENCES JOB_ROLE_MASTER(JOB_ROLE_ID)
);

CREATE TABLE SALARY_COMPONENT_MASTER (	
	COMP_ID	INT(32)	PRIMARY KEY,
	COMPONENT_NAME	VARCHAR(32),
	DESCRIPTION	VARCHAR(32),
	SAL_COMPONENT	VARCHAR(32)
);
					
CREATE TABLE SALARY_OPT_COMPONENT_MASTER	(	
	OPT_COMP_ID	INT(32)	PRIMARY KEY,
	OPT_COMPONENT_NAME VARCHAR(32),
	DESCRIPTION	VARCHAR(100),
	SAL_OPT_COMPONENT	VARCHAR(32),
	RESTRICTION_WINDOW INT(32),
	FREQUENCY INT(32),
	END_DATE DATETIME,
	CREDIT_DEBIT_FLAG VARCHAR(1),
	EMP_OPT_OUT_FLAG VARCHAR(1)
);
	
CREATE TABLE SALARY_MASTER (
	SALARY_ID	INT(32) PRIMARY KEY,
	DESCRIPTION	VARCHAR(100),
	JOB_ROLE_ID INT(32) NOT NULL,
	SAL_COMP_ID	INT(32) NOT NULL,
	SAL_VALUE	DECIMAL(32,2),		
	MAX_ALLOW_LIMIT DECIMAL(32,2),
	SAL_OPT_COMP_FLAG INT(5),
	CONSTRAINT SAL_COMP_FK FOREIGN KEY(SAL_COMP_ID) REFERENCES SALARY_COMPONENT_MASTER(COMP_ID),
	CONSTRAINT SAL_OPT_COMP_FK FOREIGN KEY(SAL_COMP_ID) REFERENCES SALARY_OPT_COMPONENT_MASTER(OPT_COMP_ID),
	UNIQUE KEY (JOB_ROLE_ID, SAL_COMP_ID),
	FOREIGN KEY(JOB_ROLE_ID) REFERENCES JOB_ROLE_MASTER(JOB_ROLE_ID)
);

CREATE TABLE DOC_TYPE_MASTER (
	DOC_TYPE_ID INT(32) PRIMARY KEY,
	DOC_TYPE_NAME VARCHAR(32),
	IDENTITY_DOC VARCHAR(1)
);

CREATE TABLE EMPLOYEE (	
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	TITLE	VARCHAR(5),
	EMPLOYEE_FIRST_NAME	VARCHAR(60),
	EMPLOYEE_MIDDLE_NAME	VARCHAR(30),
	EMPLOYEE_LAST_NAME	VARCHAR(60),
	SEX VARCHAR(10),
	EMP_TYPE VARCHAR(15),
	MARITAL_STATUS VARCHAR(10),
	DATE_OF_JOINING	DATETIME,
	ORG_ID	INT(32),	
	UNIT_ID	INT(32),	
	DEPARTMENT_ID INT(32),
	NATIONALITY	VARCHAR(32),
	IDENTITY_DOC_TYPE_ID INT(32),
	IDENTITY_NUMBER	VARCHAR(32),
	DATE_OF_BIRTH	DATETIME,		
	FATHER_NAME	VARCHAR(120),
	EMAIL_ID VARCHAR(50),
	CONTACTNO VARCHAR(15),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	DATETIME,
	HR_FLAG VARCHAR(1),
	SUPERVISOR_FLAG VARCHAR(1),
	EMPLOYEE_IMAGE LONGBLOB,
	FOREIGN KEY(DEPARTMENT_ID) REFERENCES DEPARTMENT_MASTER(DEPARTMENT_ID),
	FOREIGN KEY(ORG_ID) REFERENCES ORGANISATION_MASTER(ORG_ID),
	FOREIGN KEY(UNIT_ID) REFERENCES UNIT_MASTER(UNIT_ID),
	FOREIGN KEY(IDENTITY_DOC_TYPE_ID) REFERENCES DOC_TYPE_MASTER(DOC_TYPE_ID)
);

CREATE TABLE EMPLOYEE_ADDITIONAL_DETAILS (	
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	NO_OF_SIBLINGS	INT(32),
	NO_OF_DEPENDENT	INT(32),
	NOMINEE_NAME1	VARCHAR(120),
	NOMINEE_NAME2	VARCHAR(120),
	NOMINEE_NAME3	VARCHAR(120),
	NOMINEE1_SHARE	FLOAT(32),
	NOMINEE2_SHARE	FLOAT(32),
	NOMINEE3_SHARE	FLOAT(32),
	EMERGENCY_CONTACT_NAME VARCHAR(30),
	EMERGENCY_CONTACTNO VARCHAR(15),
	PRE_MEDICAL_CHKUP_DATE	DATETIME,	
	MEDICAL_REPORT_COMMENT	VARCHAR(32),
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID)
);

CREATE TABLE COUNTRY_MASTER(
	COUNTRY_ID INT(32) PRIMARY KEY,
	COUNTRY_NAME VARCHAR(50)
);

CREATE TABLE STATE_MASTER(
	STATE_ID INT(32) PRIMARY KEY,
	STATE_NAME VARCHAR(32),
	COUNTRY_ID INT(32),
	FOREIGN KEY(COUNTRY_ID) REFERENCES COUNTRY_MASTER(COUNTRY_ID)
);

CREATE TABLE DISTRICT_MASTER(
	DISTRICT_ID INT(32) PRIMARY KEY,
	DISTRICT_NAME VARCHAR(32),
	STATE_ID INT(32),
	FOREIGN KEY(STATE_ID) REFERENCES STATE_MASTER(STATE_ID)
);

CREATE TABLE ADDRESS(	
	EMP_ID	VARCHAR(20),
	ADDRESS_TYPE VARCHAR(20),
	HOUSE_NO	VARCHAR(32),
	STREET_NAME	VARCHAR(32),
	AREA	VARCHAR(32),
	REGION	VARCHAR(32),
	PIN_NO	INT(32),
	DISTRICT_ID	INT(32),
	STATE_ID	INT(32),
	COUNTRY_ID	INT(32),
	DESCRIPTION	VARCHAR(100),
	PRIMARY KEY (EMP_ID, ADDRESS_TYPE),
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID),
	FOREIGN KEY(DISTRICT_ID) REFERENCES DISTRICT_MASTER(DISTRICT_ID),
	FOREIGN KEY(STATE_ID) REFERENCES STATE_MASTER(STATE_ID),
	FOREIGN KEY(COUNTRY_ID) REFERENCES COUNTRY_MASTER(COUNTRY_ID)
);

CREATE TABLE EMPLOYEE_PROFILE(
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	QUALIFICATION	VARCHAR(32),
	DESCRIPTION	VARCHAR(256),
	COMMENTS	VARCHAR(52),
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID)
);
				
CREATE TABLE EMPLOYEE_HIERARCHY_STATUS(	
	EMP_ID VARCHAR(20) PRIMARY KEY,
	SUPERVISOR_ID VARCHAR(20),
	HR_ID VARCHAR(20),
	STATUS VARCHAR(20),
	CL	INT(32),
	PL	INT(32),
	PATERNITY_LEAVE	INT(32),
	SICK_LEAVE	INT(32),
	MATERNITY_LEAVE	INT(32),
	SPECIAL_LEAVE	INT(32),
	PROBATION_PERIOD_END_DATE	DATETIME,	
	NOTICE_PERIOD_LAST_DATE	DATETIME,
	LAST_MOD_DATE	DATETIME,
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID),
	FOREIGN KEY(SUPERVISOR_ID) REFERENCES EMPLOYEE(EMP_ID),
	FOREIGN KEY(HR_ID) REFERENCES EMPLOYEE(EMP_ID)
);

CREATE TABLE APPRAISAL_CYCLE(
	APPRAISAL_ID INT(32) PRIMARY KEY,
	CYCLE_NAME VARCHAR(32),
	START_DATE DATETIME,
	END_DATE DATETIME,
	SAL_REV_START_DATE DATETIME,
	SAL_REV_END_DATE DATETIME,
	EFFECTIVE_DATE DATETIME,
	ACTIVATION_DATE DATETIME
);


CREATE TABLE APPRAISAL_RATING(
	EMP_ID VARCHAR(20),
	APPRAISAL_ID INT(32),
	PERFORMANCE_INDICATOR	INT(32),
	PERFORMANCE_DESCRIPTION VARCHAR(2000),
	JOB_ROLE_ID INT(32),
	DESIGNATION_ID INT(32),
	FOREIGN KEY(JOB_ROLE_ID) REFERENCES JOB_ROLE_MASTER(JOB_ROLE_ID),
	FOREIGN KEY(DESIGNATION_ID) REFERENCES DESIGNATION_MASTER(DESIGNATION_ID),
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID),
	FOREIGN KEY(APPRAISAL_ID) REFERENCES APPRAISAL_CYCLE(APPRAISAL_ID),
	PRIMARY KEY(APPRAISAL_ID, EMP_ID)
);

CREATE TABLE EMPLOYEE_SALARY(
	APPRAISAL_ID INT(32),	
	EMP_ID	VARCHAR(20),
	COMP_ID	INT(32),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	DATETIME,	
	SAL_VALUE	DECIMAL(32,2),
	PRIMARY KEY(APPRAISAL_ID, EMP_ID, COMP_ID),
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID),
	FOREIGN KEY(COMP_ID) REFERENCES SALARY_COMPONENT_MASTER(COMP_ID),
	FOREIGN KEY(APPRAISAL_ID) REFERENCES APPRAISAL_CYCLE(APPRAISAL_ID)
);
				
CREATE TABLE EMPLOYEE_OPTIONAL_BENEFITS (	
	BENEFIT_ID INT(64) PRIMARY KEY,
	EMP_ID VARCHAR(20),
	OPT_COMP_ID	INT(52),
	SAL_OPT_FLAG	INT(1),
	SAL_OPT_VALUE	FLOAT(32),
	SAL_OPT_START_DATE	DATETIME,
	SAL_OPT_END_DATE	DATETIME,
	NEXT_DUE_DATE DATETIME,
	REMARKS	VARCHAR(100),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	DATETIME,
	FREQUENCY INT(32),
	NO_OF_ITERATION INT(32),
	FOREIGN KEY(OPT_COMP_ID) REFERENCES SALARY_OPT_COMPONENT_MASTER(OPT_COMP_ID),
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID)
);

CREATE TABLE EMPLOYEE_DOCS (	
	EMP_ID VARCHAR(20),
	DOC_TYPE_ID INT(32),
	REMARKS VARCHAR(2000),
	UPLOAD_DATE DATETIME,
	UPLOADED_BY VARCHAR(20),
	DOCUMENT LONGBLOB,
	FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID),
	FOREIGN KEY(DOC_TYPE_ID) REFERENCES DOC_TYPE_MASTER(DOC_TYPE_ID),
	PRIMARY KEY(EMP_ID, DOC_TYPE_ID)
);

CREATE TABLE EMPLOYEE_HISTORY(
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	TITLE	VARCHAR(5),
	EMPLOYEE_FIRST_NAME	VARCHAR(60),
	EMPLOYEE_MIDDLE_NAME	VARCHAR(30),
	EMPLOYEE_LAST_NAME	VARCHAR(60),
	SEX VARCHAR(10),
	EMP_TYPE VARCHAR(15),
	MARITAL_STATUS VARCHAR(10),
	DATE_OF_JOINING	DATETIME,
	ORG_ID	INT(32),	
	UNIT_ID	INT(32),	
	DEPARTMENT_ID INT(32),
	NATIONALITY	VARCHAR(32),
	IDENTITY_DOC_TYPE_ID INT(32),
	IDENTITY_NUMBER	VARCHAR(32),
	DATE_OF_BIRTH	DATETIME,		
	FATHER_NAME	VARCHAR(120),
	EMAIL_ID VARCHAR(30),
	CONTACTNO VARCHAR(15),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	DATETIME,
	HR_FLAG INT(5),
	SUPERVISOR_FLAG INT(5),
	EMPLOYEE_IMAGE LONGBLOB,
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	DATETIME	
);

CREATE TABLE EMPLOYEE_ADDITIONAL_DETAILS_HISTORY (	
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	NO_OF_SIBLINGS	INT(32),
	NO_OF_DEPENDENT	INT(32),
	NOMINEE_NAME1	VARCHAR(120),
	NOMINEE_NAME2	VARCHAR(120),
	NOMINEE_NAME3	VARCHAR(120),
	NOMINEE1_SHARE	FLOAT(32),
	NOMINEE2_SHARE	FLOAT(32),
	NOMINEE3_SHARE	FLOAT(32),
	EMERGENCY_CONTACT_NAME VARCHAR(30),
	EMERGENCY_CONTACTNO VARCHAR(15),
	PRE_MEDICAL_CHKUP_DATE	DATETIME,	
	MEDICAL_REPORT_COMMENT	VARCHAR(32),
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	DATETIME	
);	

CREATE TABLE EMPLOYEE_PROFILE_HISTORY	(
	EMP_ID	VARCHAR(20),
	QUALIFICATION	VARCHAR(32),
	DESCRIPTION	VARCHAR(256),
	CERTIFICATE_DOC	LONGBLOB,
	IDENTIFICATION_DOC	LONGBLOB,
	EMPLOYEE_IMAGE	LONGBLOB,
	COMMENTS	VARCHAR(52),
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	DATETIME	
);

CREATE TABLE EMPLOYEE_HIERARCHY_STATUS_HISTORY	(	
	EMP_ID VARCHAR(20) PRIMARY KEY,
	SUPERVISOR_ID INT(32),
	HR_ID INT(32),
	STATUS VARCHAR(4),
	CL	INT(32),
	PL	INT(32),
	PATERNITY_LEAVE	INT(32),
	SICK_LEAVE	INT(32),
	MATERNITY_LEAVE	INT(32),
	SPECIAL_LEAVE INT(32),
	PROBATION_PERIOD_END_DATE	DATETIME,	
	NOTICE_PERIOD_LAST_DATE	DATETIME,
	LAST_MOD_DATE	DATETIME,
	MOD_BY	VARCHAR(32)
);

CREATE TABLE ORG_LEAVE (
	ORG_ID INT(32),
	PROBATION_FLAG VARCHAR(1),
	CL	INT(32),
	PL	INT(32),
	PATERNITY_LEAVE	INT(32),
	SICK_LEAVE	INT(32),
	MATERNITY_LEAVE	INT(32),
	FOREIGN KEY(ORG_ID) REFERENCES ORGANISATION_MASTER(ORG_ID)
);

CREATE TABLE ROLE_MASTER (
	ROLE_ID INT(32) PRIMARY KEY,
	ROLE_NAME VARCHAR(30),
	ROLE_DESCRIPTION VARCHAR(60)
);

CREATE TABLE PERMISSION_MASTER (
	PERMISSION_ID INT(32) PRIMARY KEY,
	PERMISSION_NAME VARCHAR(30),
	PERMISSION_TYPE VARCHAR(1),
	RESOURCE_NAME VARCHAR(100)
);

CREATE TABLE ROLE_PERMISSION (
	ROLE_ID INT(32),
	PERMISSION_ID INT(32),
	PRIMARY KEY (ROLE_ID, PERMISSION_ID),
	FOREIGN KEY(ROLE_ID) REFERENCES ROLE_MASTER(ROLE_ID),
	FOREIGN KEY(PERMISSION_ID) REFERENCES PERMISSION_MASTER(PERMISSION_ID)
);

CREATE INDEX EMP_HRCHY_HIST_IDX_1 ON EMPLOYEE_HIERARCHY_STATUS_HISTORY(EMP_ID);
CREATE INDEX EMP_HRCHY_HIST_IDX_2 ON EMPLOYEE_HIERARCHY_STATUS_HISTORY(HR_ID);
CREATE INDEX EMP_HRCHY_HIST_IDX_3 ON EMPLOYEE_HIERARCHY_STATUS_HISTORY(SUPERVISOR_ID);
CREATE INDEX EMP_PROF_HIST_IDX ON EMPLOYEE_PROFILE_HISTORY(EMP_ID);
CREATE INDEX EMP_ADD_DET_IDX ON EMPLOYEE_ADDITIONAL_DETAILS_HISTORY(EMP_ID);
CREATE INDEX EMP_HIST_IDX ON EMPLOYEE_HISTORY(EMP_ID);
CREATE INDEX EMP_OPT_BEN_IDX_1 ON EMPLOYEE_OPTIONAL_BENEFITS(EMP_ID);
CREATE INDEX EMP_OPT_BEN_IDX_2 ON EMPLOYEE_OPTIONAL_BENEFITS(OPT_COMP_ID);
CREATE INDEX EMP_HRCHY_IDX_1 ON EMPLOYEE_HIERARCHY_STATUS(HR_ID);
CREATE INDEX EMP_HRCHY_IDX_2 ON EMPLOYEE_HIERARCHY_STATUS(SUPERVISOR_ID);
