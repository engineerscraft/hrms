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
	NATIONALITY	INT,
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
	EMPLOYEE_IMAGE TEXT,
	FOREIGN KEY(DEPARTMENT_ID) REFERENCES HRMS.DEPARTMENT_MASTER(DEPARTMENT_ID),
	FOREIGN KEY(ORG_ID) REFERENCES HRMS.ORGANISATION_MASTER(ORG_ID),
	FOREIGN KEY(UNIT_ID) REFERENCES HRMS.UNIT_MASTER(UNIT_ID),
	FOREIGN KEY(IDENTITY_DOC_TYPE_ID) REFERENCES HRMS.DOC_TYPE_MASTER(DOC_TYPE_ID),
	FOREIGN KEY(NATIONALITY) REFERENCES HRMS.COUNTRY_MASTER(COUNTRY_ID)
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
/*				
CREATE TABLE HRMS.EMPLOYEE_HIERARCHY_STATUS(	
	EMP_ID VARCHAR(20) PRIMARY KEY,
	SUPERVISOR_ID VARCHAR(20),
	SUPERVISOR_EMAIL_ID VARCHAR(50),
	HR_ID VARCHAR(20),
	HR_EMAIL_ID VARCHAR(50),
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
*/

/* Leave details shifted to LEAVE_MASTER table */
CREATE TABLE HRMS.EMPLOYEE_HIERARCHY_STATUS(	
	EMP_ID VARCHAR(20) PRIMARY KEY,
	SUPERVISOR_ID VARCHAR(20),
	SUPERVISOR_EMAIL_ID VARCHAR(50),
	HR_ID VARCHAR(20),
	HR_EMAIL_ID VARCHAR(50),
	STATUS VARCHAR(20),
	CL	INT,
	PL	INT,
	PATERNITY_LEAVE	INT,
	SICK_LEAVE	INT,
	MATERNITY_LEAVE	INT,
	SPECIAL_LEAVE	INT,
	CHARGE_OF_EXTRA_CLASS DOUBLE PRECISION,
	MONTHLY_EXTRA_CLASS INT,
	YEARLY_EXTRA_CLASS INT,
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

CREATE SEQUENCE HRMS.EMP_OPT_BENEFIT_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;

CREATE TABLE HRMS.EMPLOYEE_OPTIONAL_BENEFITS (	
	BENEFIT_ID INT PRIMARY KEY DEFAULT NEXTVAL('HRMS.EMP_OPT_BENEFIT_SEQ'),
	EMP_ID VARCHAR(20),
	OPT_COMP_ID	INT,
	SAL_OPT_FLAG	INT,
	SAL_OPT_VALUE	DOUBLE PRECISION,
	TOTAL_AMOUNT	DOUBLE PRECISION,
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

CREATE SEQUENCE HRMS.EMP_OPT_BENEFIT_HIST_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;

CREATE TABLE HRMS.EMPLOYEE_OPTIONAL_BENEFITS_HISTORY (	
	OPT_BENEFIT_HIST_ID INT PRIMARY KEY DEFAULT NEXTVAL('HRMS.EMP_OPT_BENEFIT_HIST_SEQ'),
	BENEFIT_ID INT,
	EMP_ID VARCHAR(20),
	OPT_COMP_ID	INT,
	SAL_OPT_FLAG	INT,
	SAL_OPT_VALUE	DOUBLE PRECISION,
	TOTAL_AMOUNT	DOUBLE PRECISION,
	SAL_OPT_START_DATE	TIMESTAMP(0),
	SAL_OPT_END_DATE	TIMESTAMP(0),
	NEXT_DUE_DATE TIMESTAMP(0),
	REMARKS	VARCHAR(100),
	ENTRY_BY	VARCHAR(32),
	ENTRY_DATE	TIMESTAMP(0),
	FREQUENCY INT,
	NO_OF_ITERATION INT,
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	TIMESTAMP(0)
);

CREATE TABLE HRMS.EMPLOYEE_DOCS (	
	EMP_ID VARCHAR(20),
	DOC_ID INT PRIMARY KEY,
	DOC_TYPE_ID INT,
	REMARKS VARCHAR(2000),
	UPLOAD_DATE TIMESTAMP(0),
	UPLOADED_BY VARCHAR(20),
	DOCUMENT TEXT,
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(DOC_TYPE_ID) REFERENCES HRMS.DOC_TYPE_MASTER(DOC_TYPE_ID)
);

CREATE SEQUENCE HRMS.EMP_HIST_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;

CREATE TABLE HRMS.EMPLOYEE_HISTORY(
	EMP_HIST_ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('HRMS.EMP_HIST_SEQ'),
	EMP_ID	VARCHAR(20),
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
	NATIONALITY	INT,
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
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	TIMESTAMP(0)	
);


CREATE SEQUENCE HRMS.EMP_ADL_DTL_HIST_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;

CREATE TABLE HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY (	
	EMP_ADL_DTL_HIST_ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('HRMS.EMP_ADL_DTL_HIST_SEQ'),
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


CREATE SEQUENCE HRMS.EMP_PROFILE_HIST_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;	

CREATE TABLE HRMS.EMPLOYEE_PROFILE_HISTORY	(
	EMP_PROFILE_HIST_ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('HRMS.EMP_PROFILE_HIST_SEQ'),
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

CREATE SEQUENCE HRMS.EMP_HRCHY_STAT_HIST_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;	

CREATE TABLE HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY	(	
	EMP_HRCHY_STAT_HIST_ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('HRMS.EMP_HRCHY_STAT_HIST_SEQ'),
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
	PERMISSION_NAME VARCHAR(50),
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

/*CREATE TABLE HRMS.EMPLOYEE_HISTORY_y2017m07 ( CHECK ( entry_date >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
entry_date < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_HISTORY);
CREATE TABLE HRMS.EMPLOYEE_HISTORY_y2017m08 ( CHECK ( entry_date >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND entry_date < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_HISTORY);
CREATE TABLE HRMS.EMPLOYEE_HISTORY_y2017m09 ( CHECK ( entry_date >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND entry_date < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_HISTORY);
CREATE TABLE HRMS.EMPLOYEE_HISTORY_y2017m10 ( CHECK ( entry_date >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND entry_date < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  ))
INHERITS (HRMS.EMPLOYEE_HISTORY);


CREATE INDEX EMPLOYEE_HISTORY_y2017m07_entry_date ON HRMS.EMPLOYEE_HISTORY_y2017m07 (entry_date);
CREATE INDEX EMPLOYEE_HISTORY_y2017m08_entry_date ON HRMS.EMPLOYEE_HISTORY_y2017m08 (entry_date);
CREATE INDEX EMPLOYEE_HISTORY_y2017m09_entry_date ON HRMS.EMPLOYEE_HISTORY_y2017m09 (entry_date);
CREATE INDEX EMPLOYEE_HISTORY_y2017m10_entry_date ON HRMS.EMPLOYEE_HISTORY_y2017m10 (entry_date);


CREATE OR REPLACE FUNCTION HRMS.EMPLOYEE_HISTORY_TRIG_FUNC()
RETURNS TRIGGER AS $$
BEGIN
    IF ( NEW.entry_date >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.entry_date < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') )  THEN
        INSERT INTO HRMS.EMPLOYEE_HISTORY_y2017m07 VALUES (NEW.*);
    ELSIF ( NEW.entry_date >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.entry_date < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ) THEN
        INSERT INTO HRMS.EMPLOYEE_HISTORY_y2017m08 VALUES (NEW.*);
    ELSIF (  NEW.entry_date >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.entry_date < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMPLOYEE_HISTORY_y2017m09 VALUES (NEW.*);
    ELSIF (  NEW.entry_date >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.entry_date < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMPLOYEE_HISTORY_y2017m10 VALUES (NEW.*);
    ELSE
        RAISE EXCEPTION 'Date out of range.';
    END IF;
    RETURN NULL;
END;
$$
LANGUAGE plpgsql;


CREATE TRIGGER emp_hist_trig
    BEFORE INSERT OR UPDATE ON HRMS.EMPLOYEE_HISTORY
    FOR EACH ROW EXECUTE PROCEDURE HRMS.EMPLOYEE_HISTORY_TRIG_FUNC();
	
	
	
CREATE TABLE HRMS.EMP_ADL_DTL_HIST_y2017m07 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
LAST_MOD_DATE < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY);
CREATE TABLE HRMS.EMP_ADL_DTL_HIST_y2017m08 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY);
CREATE TABLE HRMS.EMP_ADL_DTL_HIST_y2017m09 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY);
CREATE TABLE HRMS.EMP_ADL_DTL_HIST_y2017m10 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  ))
INHERITS (HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY);
	
	
CREATE INDEX EMP_ADL_DTL_HIST_y2017m07_mod_date ON HRMS.EMP_ADL_DTL_HIST_y2017m07 (LAST_MOD_DATE);
CREATE INDEX EMP_ADL_DTL_HIST_y2017m08_mod_date ON HRMS.EMP_ADL_DTL_HIST_y2017m08 (LAST_MOD_DATE);
CREATE INDEX EMP_ADL_DTL_HIST_y2017m09_mod_date ON HRMS.EMP_ADL_DTL_HIST_y2017m09 (LAST_MOD_DATE);
CREATE INDEX EMP_ADL_DTL_HIST_y2017m10_mod_date ON HRMS.EMP_ADL_DTL_HIST_y2017m10 (LAST_MOD_DATE);

CREATE OR REPLACE FUNCTION HRMS.EMP_ADL_DTL_HIST_FUNC()
RETURNS TRIGGER AS $$
BEGIN
    IF ( NEW.LAST_MOD_DATE >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') )  THEN
        INSERT INTO HRMS.EMP_ADL_DTL_HIST_y2017m07 VALUES (NEW.*);
    ELSIF ( NEW.LAST_MOD_DATE >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ) THEN
        INSERT INTO HRMS.EMP_ADL_DTL_HIST_y2017m08 VALUES (NEW.*);
    ELSIF (  NEW.LAST_MOD_DATE >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMP_ADL_DTL_HIST_y2017m09 VALUES (NEW.*);
    ELSIF (  NEW.LAST_MOD_DATE >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMP_ADL_DTL_HIST_y2017m10 VALUES (NEW.*);
    ELSE
        RAISE EXCEPTION 'Date out of range.';
    END IF;
    RETURN NULL;
END;
$$
LANGUAGE plpgsql;


CREATE TRIGGER EMP_ADL_DTL_HIST_TRIG
    BEFORE INSERT OR UPDATE ON HRMS.EMPLOYEE_ADDITIONAL_DETAILS_HISTORY
    FOR EACH ROW EXECUTE PROCEDURE HRMS.EMP_ADL_DTL_HIST_FUNC();
	

	
CREATE TABLE HRMS.EMP_PROFILE_HIST_y2017m07 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
LAST_MOD_DATE < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_PROFILE_HISTORY);
CREATE TABLE HRMS.EMP_PROFILE_HIST_y2017m08 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_PROFILE_HISTORY);
CREATE TABLE HRMS.EMP_PROFILE_HIST_y2017m09 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_PROFILE_HISTORY);
CREATE TABLE HRMS.EMP_PROFILE_HIST_y2017m10 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  ))
INHERITS (HRMS.EMPLOYEE_PROFILE_HISTORY);


CREATE INDEX EMP_PROFILE_HIST_y2017m07_mod_date ON HRMS.EMP_PROFILE_HIST_y2017m07 (LAST_MOD_DATE);
CREATE INDEX EMP_PROFILE_HIST_y2017m08_mod_date ON HRMS.EMP_PROFILE_HIST_y2017m08 (LAST_MOD_DATE);
CREATE INDEX EMP_PROFILE_HIST_y2017m09_mod_date ON HRMS.EMP_PROFILE_HIST_y2017m09 (LAST_MOD_DATE);
CREATE INDEX EMP_PROFILE_HIST_y2017m10_mod_date ON HRMS.EMP_PROFILE_HIST_y2017m10 (LAST_MOD_DATE);


CREATE OR REPLACE FUNCTION HRMS.EMP_PROFILE_HIST_FUNC()
RETURNS TRIGGER AS $$
BEGIN
    IF ( NEW.LAST_MOD_DATE >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') )  THEN
        INSERT INTO HRMS.EMP_PROFILE_HIST_y2017m07 VALUES (NEW.*);
    ELSIF ( NEW.LAST_MOD_DATE >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ) THEN
        INSERT INTO HRMS.EMP_PROFILE_HIST_y2017m08 VALUES (NEW.*);
    ELSIF (  NEW.LAST_MOD_DATE >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMP_PROFILE_HIST_y2017m09 VALUES (NEW.*);
    ELSIF (  NEW.LAST_MOD_DATE >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMP_PROFILE_HIST_y2017m10 VALUES (NEW.*);
    ELSE
        RAISE EXCEPTION 'Date out of range.';
    END IF;
    RETURN NULL;
END;
$$
LANGUAGE plpgsql;


CREATE TRIGGER EMP_PROFILE_HIST_TRIG
    BEFORE INSERT OR UPDATE ON HRMS.EMPLOYEE_PROFILE_HISTORY
    FOR EACH ROW EXECUTE PROCEDURE HRMS.EMP_PROFILE_HIST_FUNC();
	
	
CREATE TABLE HRMS.EMP_HRCHY_HIST_y2017m07 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
LAST_MOD_DATE < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY);
CREATE TABLE HRMS.EMP_HRCHY_HIST_y2017m08 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY);
CREATE TABLE HRMS.EMP_HRCHY_HIST_y2017m09 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ))
INHERITS (HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY);
CREATE TABLE HRMS.EMP_HRCHY_HIST_y2017m10 ( CHECK ( LAST_MOD_DATE >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND LAST_MOD_DATE < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  ))
INHERITS (HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY);


CREATE INDEX EMP_HRCHY_HIST_y2017m07_mod_date ON HRMS.EMP_HRCHY_HIST_y2017m07 (LAST_MOD_DATE);
CREATE INDEX EMP_HRCHY_HIST_y2017m08_mod_date ON HRMS.EMP_HRCHY_HIST_y2017m08 (LAST_MOD_DATE);
CREATE INDEX EMP_HRCHY_HIST_y2017m09_mod_date ON HRMS.EMP_HRCHY_HIST_y2017m09 (LAST_MOD_DATE);
CREATE INDEX EMP_HRCHY_HIST_y2017m10_mod_date ON HRMS.EMP_HRCHY_HIST_y2017m10 (LAST_MOD_DATE);

CREATE OR REPLACE FUNCTION HRMS.EMP_HRCHY_HIST_FUNC()
RETURNS TRIGGER AS $$
BEGIN
    IF ( NEW.LAST_MOD_DATE >= to_timestamp('01-07-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') )  THEN
        INSERT INTO HRMS.EMP_HRCHY_HIST_y2017m07 VALUES (NEW.*);
    ELSIF ( NEW.LAST_MOD_DATE >= to_timestamp('01-08-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss') ) THEN
        INSERT INTO HRMS.EMP_HRCHY_HIST_y2017m08 VALUES (NEW.*);
    ELSIF (  NEW.LAST_MOD_DATE >= to_timestamp('01-09-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMP_HRCHY_HIST_y2017m09 VALUES (NEW.*);
    ELSIF (  NEW.LAST_MOD_DATE >= to_timestamp('01-10-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')  AND
         NEW.LAST_MOD_DATE < to_timestamp('01-11-2017 00:00:00', 'dd-mm-yyyy hh24:mi:ss')) THEN
        INSERT INTO HRMS.EMP_HRCHY_HIST_y2017m10 VALUES (NEW.*);
    ELSE
        RAISE EXCEPTION 'Date out of range.';
    END IF;
    RETURN NULL;
END;
$$
LANGUAGE plpgsql;



CREATE TRIGGER EMP_HRCHY_HIST_TRIG
    BEFORE INSERT OR UPDATE ON HRMS.EMPLOYEE_HIERARCHY_STATUS_HISTORY
    FOR EACH ROW EXECUTE PROCEDURE HRMS.EMP_HRCHY_HIST_FUNC();
	
*/	

CREATE SEQUENCE HRMS.EMP_DOC_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;

CREATE TABLE HRMS.LEAVE_MASTER(	
	EMP_ID VARCHAR(20) PRIMARY KEY,
	ELIGIBLE_CL	INT,
	ELIGIBLE_PL	INT,
	ELIGIBLE_PATERNITY_MATERNITY_LEAVE	INT,
	ELIGIBLE_SICK_LEAVE	INT,
	ELIGIBLE_SPECIAL_LEAVE	INT,
	AVAILED_CL	INT,
	AVAILED_PL	INT,
	AVAILED_PATERNITY_MATERNITY_LEAVE	INT,
	AVAILED_SICK_LEAVE	INT,
	AVAILED_SPECIAL_LEAVE	INT,	
	REMAINING_CL	INT,
	REMAINING_PL	INT,
	REMAINING_PATERNITY_MATERNITY_LEAVE	INT,
	REMAINING_SICK_LEAVE	INT,
	REMAINING_SPECIAL_LEAVE	INT,	
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID)
);

CREATE SEQUENCE HRMS.LEAVE_MASTER_HIST_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;

CREATE TABLE HRMS.LEAVE_MASTER_HISTORY(	
	LEAVE_MAS_HIST_ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('HRMS.LEAVE_MASTER_HIST_SEQ'),
	EMP_ID VARCHAR(20),
	ELIGIBLE_CL	INT,
	ELIGIBLE_PL	INT,
	ELIGIBLE_PATERNITY_MATERNITY_LEAVE	INT,
	ELIGIBLE_SICK_LEAVE	INT,
	ELIGIBLE_SPECIAL_LEAVE	INT,
	AVAILED_CL	INT,
	AVAILED_PL	INT,
	AVAILED_PATERNITY_MATERNITY_LEAVE	INT,
	AVAILED_SICK_LEAVE	INT,
	AVAILED_SPECIAL_LEAVE	INT,	
	REMAINING_CL	INT,
	REMAINING_PL	INT,
	REMAINING_PATERNITY_MATERNITY_LEAVE	INT,
	REMAINING_SICK_LEAVE	INT,
	REMAINING_SPECIAL_LEAVE	INT,
	MOD_BY	VARCHAR(32),
	LAST_MOD_DATE	TIMESTAMP(0)
);


CREATE TYPE HRMS.COMPONENT_TYPE AS ENUM ('Tax','Fixed', 'Optinal');

CREATE TABLE HRMS.EMPLOYEE_MONTHLY_PAYROLL(	
	EMP_ID VARCHAR(20),
	MONTH	VARCHAR(15),
	YEAR	INT,
	COMP_ID INT,
	COMP_VALUE DOUBLE PRECISION,
	COMP_TYPE HRMS.COMPONENT_TYPE,
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID)
);



CREATE SEQUENCE HRMS.EMPLOYEE_PAYSLIP_ID_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;
CREATE TABLE HRMS.EMPLOYEE_PAYSLIP(	
	PAYSLIP_ID BIGINT PRIMARY KEY DEFAULT NEXTVAL('HRMS.EMPLOYEE_PAYSLIP_ID_SEQ'),
	EMP_ID VARCHAR(20),
	MONTH	VARCHAR(15),
	YEAR	INT,
	TOTAL_EARNING DOUBLE PRECISION,
	TOTAL_DEDUCTION DOUBLE PRECISION,
	NET_PAYABLE DOUBLE PRECISION,
	PAYSLIP_FILE TEXT,
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID)
);

CREATE SEQUENCE HRMS.TAX_COMPONENT_MASTER_SEQ START WITH 1 INCREMENT BY 1 CACHE 5 CYCLE;
CREATE TABLE HRMS.TAX_COMPONENT_MASTER(	
	TAX_COMP_ID INT PRIMARY KEY DEFAULT NEXTVAL('HRMS.TAX_COMPONENT_MASTER_SEQ'),
	TAX_COMP_NAME VARCHAR(30),
	TAX_COMP_DESCRIPTION VARCHAR(40)
);

CREATE TABLE HRMS.EMPLOYEE_TAX(	
	EMP_ID VARCHAR(20),
	TAX_COMP_ID INT,
	ENTRY_BY VARCHAR(32),
	ENTRY_DATE DATE,
	TAX_COMP_VALUE DOUBLE precision,
	primary key (EMP_ID, TAX_COMP_ID),
	FOREIGN KEY(EMP_ID) REFERENCES HRMS.EMPLOYEE(EMP_ID),
	FOREIGN KEY(TAX_COMP_ID) REFERENCES HRMS.TAX_COMPONENT_MASTER(TAX_COMP_ID)
);



GRANT ALL ON SCHEMA HRMS TO "hrmsapp";
GRANT ALL ON ALL TABLES IN SCHEMA HRMS TO "hrmsapp";
GRANT ALL ON ALL SEQUENCES IN SCHEMA HRMS TO "hrmsapp";
