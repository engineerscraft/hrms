CREATE TABLE HRMS.APP_SEQUENCE (
  SEQ_NAME VARCHAR(20) NOT NULL,
  SEQ_VALUE BIGINT CHECK (SEQ_VALUE > 0) NOT NULL,
  PRIMARY KEY (SEQ_NAME)
);

CREATE TABLE HRMS.ACCESS_TOKEN (
  ACCESS_ID BIGINT,
  INSERT_DATE TIMESTAMP(0),
  USER_NAME VARCHAR(30),
  EXPIRY_DATE TIMESTAMP(0),
  REFRESH_COUNT INT,
  LAST_REFRESH_DATE TIMESTAMP(0)
);

CREATE TABLE HRMS.ORGANISATION_TYPE_MASTER (
	ORG_TYPE_ID INT PRIMARY KEY,
	ORG_TYPE_NAME	VARCHAR(100),
	DESCRIPTION	VARCHAR(200)
);

CREATE TABLE HRMS.ORGANISATION_MASTER (		
	ORG_ID	INT	PRIMARY KEY,
	ORG_NAME	VARCHAR(100),
	ORG_TYPE_ID	INT,	
	ADDRESS	VARCHAR(256),
	FOREIGN KEY(ORG_TYPE_ID) REFERENCES HRMS.ORGANISATION_TYPE_MASTER(ORG_TYPE_ID)
);

CREATE TABLE HRMS.UNIT_MASTER (		
	UNIT_ID	INT PRIMARY KEY,
	UNIT_NAME VARCHAR(100),
	ORG_ID INT,
	EMP_ID_PREFIX VARCHAR(10),
	EMP_ID_SEQ_NAME VARCHAR(20),
	ADDRESS	VARCHAR(256),
	FOREIGN KEY(ORG_ID) REFERENCES HRMS.ORGANISATION_MASTER(ORG_ID)
);

CREATE TABLE HRMS.DEPARTMENT_MASTER (	
	DEPARTMENT_ID	INT	PRIMARY KEY,
	DEPARTMENT_NAME VARCHAR(100),
	UNIT_ID	INT,	
	ADDRESS	VARCHAR(256),
	PARENT_DEPARTMENT_ID INT,
	FOREIGN KEY(UNIT_ID) REFERENCES HRMS.UNIT_MASTER(UNIT_ID)
);

CREATE TABLE HRMS.GRADE_MASTER (	
	GRADE_ID INT,
	GRADE	VARCHAR(32),
	PRIMARY KEY(GRADE_ID)
);

CREATE TABLE HRMS.DESIGNATION_MASTER (	
	DESIGNATION_ID INT,
	DESIGNATION VARCHAR(32),
	PRIMARY KEY(DESIGNATION_ID)
);

CREATE TABLE HRMS.JOB_ROLE_MASTER (
	JOB_ROLE_ID INT,
	ORG_ID INT NOT NULL,
	GRADE_ID INT NOT NULL,
	PROBATION_NOTICE_PERIOD	INT,
	NOTICE_PERIOD	INT,
	PRIMARY KEY(JOB_ROLE_ID),
	UNIQUE (ORG_ID, GRADE_ID),
	FOREIGN KEY(ORG_ID) REFERENCES HRMS.ORGANISATION_MASTER(ORG_ID),
	FOREIGN KEY(GRADE_ID) REFERENCES HRMS.GRADE_MASTER(GRADE_ID)
);

CREATE TABLE HRMS.JOB_ROLE_DESIGNATION(
	JOB_ROLE_ID INT,
	DESIGNATION_ID INT,
	PRIMARY KEY(JOB_ROLE_ID, DESIGNATION_ID),
	FOREIGN KEY(DESIGNATION_ID) REFERENCES HRMS.DESIGNATION_MASTER(DESIGNATION_ID),
	FOREIGN KEY(JOB_ROLE_ID) REFERENCES HRMS.JOB_ROLE_MASTER(JOB_ROLE_ID)
);

CREATE TABLE HRMS.SALARY_COMPONENT_MASTER (	
	COMP_ID	INT	PRIMARY KEY,
	COMPONENT_NAME	VARCHAR(32),
	DESCRIPTION	VARCHAR(32),
	SAL_COMPONENT	VARCHAR(32)
);
					
CREATE TABLE HRMS.SALARY_OPT_COMPONENT_MASTER	(	
	OPT_COMP_ID	INT	PRIMARY KEY,
	OPT_COMPONENT_NAME VARCHAR(32),
	DESCRIPTION	VARCHAR(100),
	SAL_OPT_COMPONENT	VARCHAR(32),
	RESTRICTION_WINDOW INT,
	FREQUENCY INT,
	END_DATE TIMESTAMP(0),
	CREDIT_DEBIT_FLAG VARCHAR(1),
	EMP_OPT_OUT_FLAG VARCHAR(1)
);
	
CREATE TABLE HRMS.SALARY_MASTER (
	SALARY_ID	INT PRIMARY KEY,
	DESCRIPTION	VARCHAR(100),
	JOB_ROLE_ID INT NOT NULL,
	SAL_COMP_ID	INT NOT NULL,
	SAL_VALUE	DECIMAL(32,2),		
	MAX_ALLOW_LIMIT DECIMAL(32,2),
	SAL_OPT_COMP_FLAG INT,
	CONSTRAINT SAL_COMP_FK FOREIGN KEY(SAL_COMP_ID) REFERENCES HRMS.SALARY_COMPONENT_MASTER(COMP_ID),
	CONSTRAINT SAL_OPT_COMP_FK FOREIGN KEY(SAL_COMP_ID) REFERENCES HRMS.SALARY_OPT_COMPONENT_MASTER(OPT_COMP_ID),
	UNIQUE (JOB_ROLE_ID, SAL_COMP_ID),
	FOREIGN KEY(JOB_ROLE_ID) REFERENCES HRMS.JOB_ROLE_MASTER(JOB_ROLE_ID)
);

CREATE TABLE HRMS.DOC_TYPE_MASTER (
	DOC_TYPE_ID INT PRIMARY KEY,
	DOC_TYPE_NAME VARCHAR(32),
	IDENTITY_DOC VARCHAR(1)
);

CREATE TABLE HRMS.EMPLOYEE (	
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	TITLE	VARCHAR(5),
	EMPLOYEE_FIRST_NAME	VARCHAR(60),
	EMPLOYEE_MIDDLE_NAME	VARCHAR(30),
	EMPLOYEE_LAST_NAME	VARCHAR(60),
	SEX VARCHAR(10),
	EMP_TYPE VARCHAR(15),
	MARITAL_STATUS VARCHAR(10),
	DATE_OF_JOINING	TIMESTAMP(0),
	ORG_ID	INT,	
	UNIT_ID	INT,	
	DEPARTMENT_ID INT,
	NATIONALITY	VARCHAR(32),
	IDENTITY_DOC_TYPE_ID INT,
	IDENTITY_NUMBER	VARCHAR(32),
	DATE_OF_BIRTH	TIMESTAMP(0),		
	FATHER_NAME	VARCHAR(120),
	EMAIL_ID VARCHAR(50),
	CONTACTNO VARCHAR(15),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	TIMESTAMP(0),
	HR_FLAG VARCHAR(1),
	SUPERVISOR_FLAG VARCHAR(1),
	EMPLOYEE_IMAGE bytea,
	FOREIGN KEY(DEPARTMENT_ID) REFERENCES HRMS.DEPARTMENT_MASTER(DEPARTMENT_ID),
	FOREIGN KEY(ORG_ID) REFERENCES HRMS.ORGANISATION_MASTER(ORG_ID),
	FOREIGN KEY(UNIT_ID) REFERENCES HRMS.UNIT_MASTER(UNIT_ID),
	FOREIGN KEY(IDENTITY_DOC_TYPE_ID) REFERENCES HRMS.DOC_TYPE_MASTER(DOC_TYPE_ID)
);

CREATE TABLE HRMS.EMPLOYEE_ADDITIONAL_DETAILS (	
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	NO_OF_SIBLINGS	INT,
	NO_OF_DEPENDENT	INT,
	NOMINEE_NAME1	VARCHAR(120),
	NOMINEE_NAME2	VARCHAR(120),
	NOMINEE_NAME3	VARCHAR(120),
	NOMINEE1_SHARE	DOUBLE PRECISION,
	NOMINEE2_SHARE	DOUBLE PRECISION,
	NOMINEE3_SHARE	DOUBLE PRECISION,
	EMERGENCY_CONTACT_NAME VARCHAR(30),
	EMERGENCY_CONTACTNO VARCHAR(15),
	PRE_MEDICAL_CHKUP_DATE	TIMESTAMP(0),	
	MEDICAL_REPORT_COMMENT	VARCHAR(32),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID)
);

CREATE TABLE HRMS.COUNTRY_MASTER(
	COUNTRY_ID INT PRIMARY KEY,
	COUNTRY_NAME VARCHAR(50)
);

CREATE TABLE HRMS.STATE_MASTER(
	STATE_ID INT PRIMARY KEY,
	STATE_NAME VARCHAR(32),
	COUNTRY_ID INT,
	FOREIGN KEY(COUNTRY_ID) REFERENCES HRMS.COUNTRY_MASTER(COUNTRY_ID)
);

CREATE TABLE HRMS.DISTRICT_MASTER(
	DISTRICT_ID INT PRIMARY KEY,
	DISTRICT_NAME VARCHAR(32),
	STATE_ID INT,
	FOREIGN KEY(STATE_ID) REFERENCES HRMS.STATE_MASTER(STATE_ID)
);

CREATE TABLE HRMS.ADDRESS(	
	EMP_ID	VARCHAR(20),
	ADDRESS_TYPE VARCHAR(20),
	HOUSE_NO	VARCHAR(32),
	STREET_NAME	VARCHAR(32),
	AREA	VARCHAR(32),
	REGION	VARCHAR(32),
	PIN_NO	VARCHAR(15),
	DISTRICT_ID	INT,
	STATE_ID	INT,
	COUNTRY_ID	INT,
	DESCRIPTION	VARCHAR(100),
	PRIMARY KEY (EMP_ID, ADDRESS_TYPE),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(DISTRICT_ID) REFERENCES HRMS.DISTRICT_MASTER(DISTRICT_ID),
	FOREIGN KEY(STATE_ID) REFERENCES HRMS.STATE_MASTER(STATE_ID),
	FOREIGN KEY(COUNTRY_ID) REFERENCES HRMS.COUNTRY_MASTER(COUNTRY_ID)
);

CREATE TABLE HRMS.EMPLOYEE_PROFILE(
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	QUALIFICATION	VARCHAR(32),
	DESCRIPTION	VARCHAR(256),
	COMMENTS	VARCHAR(52),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID)
);
				
CREATE TABLE HRMS.EMPLOYEE_HIERARCHY_STATUS(	
	EMP_ID VARCHAR(20) PRIMARY KEY,
	SUPERVISOR_ID VARCHAR(20),
	HR_ID VARCHAR(20),
	STATUS VARCHAR(20),
	CL	INT,
	PL	INT,
	PATERNITY_LEAVE	INT,
	SICK_LEAVE	INT,
	MATERNITY_LEAVE	INT,
	SPECIAL_LEAVE	INT,
	PROBATION_PERIOD_END_DATE	TIMESTAMP(0),	
	NOTICE_PERIOD_LAST_DATE	TIMESTAMP(0),
	LAST_MOD_DATE	TIMESTAMP(0),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(SUPERVISOR_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(HR_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID)
);

CREATE TABLE HRMS.APPRAISAL_CYCLE(
	APPRAISAL_ID INT PRIMARY KEY,
	CYCLE_NAME VARCHAR(32),
	START_DATE TIMESTAMP(0),
	END_DATE TIMESTAMP(0),
	SAL_REV_START_DATE TIMESTAMP(0),
	SAL_REV_END_DATE TIMESTAMP(0),
	EFFECTIVE_DATE TIMESTAMP(0),
	ACTIVATION_DATE TIMESTAMP(0)
);


CREATE TABLE HRMS.APPRAISAL_RATING(
	EMP_ID VARCHAR(20),
	APPRAISAL_ID INT,
	PERFORMANCE_INDICATOR	INT,
	PERFORMANCE_DESCRIPTION VARCHAR(2000),
	JOB_ROLE_ID INT,
	DESIGNATION_ID INT,
	FOREIGN KEY(JOB_ROLE_ID) REFERENCES HRMS.JOB_ROLE_MASTER(JOB_ROLE_ID),
	FOREIGN KEY(DESIGNATION_ID) REFERENCES HRMS.DESIGNATION_MASTER(DESIGNATION_ID),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(APPRAISAL_ID) REFERENCES HRMS.APPRAISAL_CYCLE(APPRAISAL_ID),
	PRIMARY KEY(APPRAISAL_ID, EMP_ID)
);

CREATE TABLE HRMS.EMPLOYEE_SALARY(
	APPRAISAL_ID INT,	
	EMP_ID	VARCHAR(20),
	COMP_ID	INT,
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	TIMESTAMP(0),	
	SAL_VALUE	DECIMAL(32,2),
	PRIMARY KEY(APPRAISAL_ID, EMP_ID, COMP_ID),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(COMP_ID) REFERENCES HRMS.SALARY_COMPONENT_MASTER(COMP_ID),
	FOREIGN KEY(APPRAISAL_ID) REFERENCES HRMS.APPRAISAL_CYCLE(APPRAISAL_ID)
);
				
CREATE TABLE HRMS.EMPLOYEE_OPTIONAL_BENEFITS (	
	BENEFIT_ID INT PRIMARY KEY,
	EMP_ID VARCHAR(20),
	OPT_COMP_ID	INT,
	SAL_OPT_FLAG	INT,
	SAL_OPT_VALUE	DOUBLE PRECISION,
	SAL_OPT_START_DATE	TIMESTAMP(0),
	SAL_OPT_END_DATE	TIMESTAMP(0),
	NEXT_DUE_DATE TIMESTAMP(0),
	REMARKS	VARCHAR(100),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	TIMESTAMP(0),
	FREQUENCY INT,
	NO_OF_ITERATION INT,
	FOREIGN KEY(OPT_COMP_ID) REFERENCES HRMS.SALARY_OPT_COMPONENT_MASTER(OPT_COMP_ID),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID)
);

CREATE TABLE HRMS.EMPLOYEE_DOCS (	
	EMP_ID VARCHAR(20),
	DOC_TYPE_ID INT,
	REMARKS VARCHAR(2000),
	UPLOAD_DATE TIMESTAMP(0),
	UPLOADED_BY VARCHAR(20),
	DOCUMENT bytea,
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(DOC_TYPE_ID) REFERENCES HRMS.DOC_TYPE_MASTER(DOC_TYPE_ID),
	PRIMARY KEY(EMP_ID, DOC_TYPE_ID)
);

CREATE TABLE HRMS.EMPLOYEE_HISTORY(
	EMP_ID	VARCHAR(20) PRIMARY KEY,
	TITLE	VARCHAR(5),
	EMPLOYEE_FIRST_NAME	VARCHAR(60),
	EMPLOYEE_MIDDLE_NAME	VARCHAR(30),
	EMPLOYEE_LAST_NAME	VARCHAR(60),
	SEX VARCHAR(10),
	EMP_TYPE VARCHAR(15),
	MARITAL_STATUS VARCHAR(10),
	DATE_OF_JOINING	TIMESTAMP(0),
	ORG_ID	INT,	
	UNIT_ID	INT,	
	DEPARTMENT_ID INT,
	NATIONALITY	VARCHAR(32),
	IDENTITY_DOC_TYPE_ID INT,
	IDENTITY_NUMBER	VARCHAR(32),
	DATE_OF_BIRTH	TIMESTAMP(0),		
	FATHER_NAME	VARCHAR(120),
	EMAIL_ID VARCHAR(30),
	CONTACTNO VARCHAR(15),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	TIMESTAMP(0),
	HR_FLAG INT,
	SUPERVISOR_FLAG INT,
	EMPLOYEE_IMAGE bytea,
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	TIMESTAMP(0)	
);

CREATE TABLE HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY (	
	EMP_ADL_HIST_ID SERIAL PRIMARY KEY,
	EMP_ID	VARCHAR(20),
	NO_OF_SIBLINGS	INT,
	NO_OF_DEPENDENT	INT,
	NOMINEE_NAME1	VARCHAR(120),
	NOMINEE_NAME2	VARCHAR(120),
	NOMINEE_NAME3	VARCHAR(120),
	NOMINEE1_SHARE	DOUBLE PRECISION,
	NOMINEE2_SHARE	DOUBLE PRECISION,
	NOMINEE3_SHARE	DOUBLE PRECISION,
	EMERGENCY_CONTACT_NAME VARCHAR(30),
	EMERGENCY_CONTACTNO VARCHAR(15),
	PRE_MEDICAL_CHKUP_DATE	TIMESTAMP(0),	
	MEDICAL_REPORT_COMMENT	VARCHAR(32),
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	TIMESTAMP(0)	
);

ALTER SEQUENCE HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY_EMP_ADL_HIST_ID_SEQ RESTART WITH 100000;	

CREATE TABLE HRMS.EMPLOYEE_PROFILE_HISTORY	(
	EMP_ID	VARCHAR(20),
	QUALIFICATION	VARCHAR(32),
	DESCRIPTION	VARCHAR(256),
	CERTIFICATE_DOC	bytea,
	IDENTIFICATION_DOC	bytea,
	EMPLOYEE_IMAGE	bytea,
	COMMENTS	VARCHAR(52),
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	TIMESTAMP(0)	
);

CREATE TABLE HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY	(	
	EMP_HIER_STAT_HIST_ID SERIAL PRIMARY KEY,
	EMP_ID VARCHAR(20),
	SUPERVISOR_ID INT,
	HR_ID INT,
	STATUS VARCHAR(4),
	CL	INT,
	PL	INT,
	PATERNITY_LEAVE	INT,
	SICK_LEAVE	INT,
	MATERNITY_LEAVE	INT,
	SPECIAL_LEAVE INT,
	PROBATION_PERIOD_END_DATE	TIMESTAMP(0),	
	NOTICE_PERIOD_LAST_DATE	TIMESTAMP(0),
	LAST_MOD_DATE	TIMESTAMP(0),
	MOD_BY	VARCHAR(32)
);

CREATE TABLE HRMS.ORG_LEAVE (
	ORG_ID INT,
	PROBATION_FLAG VARCHAR(1),
	CL	INT,
	PL	INT,
	PATERNITY_LEAVE	INT,
	SICK_LEAVE	INT,
	MATERNITY_LEAVE	INT,
	FOREIGN KEY(ORG_ID) REFERENCES HRMS.ORGANISATION_MASTER(ORG_ID)
);

CREATE TABLE HRMS.ROLE_MASTER (
	ROLE_ID INT PRIMARY KEY,
	ROLE_NAME VARCHAR(30),
	ROLE_DESCRIPTION VARCHAR(60)
);

CREATE TABLE HRMS.PERMISSION_MASTER (
	PERMISSION_ID INT PRIMARY KEY,
	PERMISSION_NAME VARCHAR(30),
	PERMISSION_TYPE VARCHAR(1),
	RESOURCE_NAME VARCHAR(100)
);

CREATE TABLE HRMS.ROLE_PERMISSION (
	ROLE_ID INT,
	PERMISSION_ID INT,
	PRIMARY KEY (ROLE_ID, PERMISSION_ID),
	FOREIGN KEY(ROLE_ID) REFERENCES HRMS.ROLE_MASTER(ROLE_ID),
	FOREIGN KEY(PERMISSION_ID) REFERENCES HRMS.PERMISSION_MASTER(PERMISSION_ID)
);

CREATE TABLE HRMS.ROLE_DEPARTMENT (
    DEPARTMENT_ID INT,
    ROLE_ID INT,
    PRIMARY KEY (DEPARTMENT_ID, ROLE_ID),
    FOREIGN KEY (DEPARTMENT_ID) REFERENCES HRMS.DEPARTMENT_MASTER(DEPARTMENT_ID),
    FOREIGN KEY (ROLE_ID) REFERENCES HRMS.ROLE_MASTER(ROLE_ID)
);

CREATE INDEX EMP_HRCHY_HIST_IDX_1 ON HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY(EMP_ID);
CREATE INDEX EMP_HRCHY_HIST_IDX_2 ON HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY(HR_ID);
CREATE INDEX EMP_HRCHY_HIST_IDX_3 ON HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY(SUPERVISOR_ID);
CREATE INDEX EMP_PROF_HIST_IDX ON HRMS.EMPLOYEE_PROFILE_HISTORY(EMP_ID);
CREATE INDEX EMP_ADD_DET_IDX ON HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY(EMP_ID);
CREATE INDEX EMP_HIST_IDX ON HRMS.EMPLOYEE_HISTORY(EMP_ID);
CREATE INDEX EMP_OPT_BEN_IDX_1 ON HRMS.EMPLOYEE_OPTIONAL_BENEFITS(EMP_ID);
CREATE INDEX EMP_OPT_BEN_IDX_2 ON HRMS.EMPLOYEE_OPTIONAL_BENEFITS(OPT_COMP_ID);
CREATE INDEX EMP_HRCHY_IDX_1 ON HRMS.EMPLOYEE_HIERARCHY_STATUS(HR_ID);
CREATE INDEX EMP_HRCHY_IDX_2 ON HRMS.EMPLOYEE_HIERARCHY_STATUS(SUPERVISOR_ID);

CREATE SEQUENCE HRMS.ACCESS_ID_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;

