CREATE USER HUAAPPUSER IDENTIFIED BY 'HUAAPPUSER';
CREATE DATABASE HUA;
USE HUA;
GRANT ALL PRIVILEGES ON HUA TO HUAAPPUSER@'%';

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
