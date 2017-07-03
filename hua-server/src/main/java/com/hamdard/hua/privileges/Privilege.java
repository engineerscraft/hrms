package com.hamdard.hua.privileges;

public enum Privilege {
    ACCOUNT_ALL_READ_CMD,
    ACCOUNT_UPDATE_CMD,
    ACCOUNT_DELETE_CMD,
    ACCOUNT_CREATE_CMD,
    COLLEGE_ALL_READ_CMD,
    DESIGNATION_ALL_READ_CMD,
    ORGANIZATION_ALL_READ_CMD,
    UNIT_ALL_READ_CMD,
    DEPARTMENT_ALL_READ_CMD,
    COUNTRY_ALL_READ_CMD,
    DISTRICT_ALL_READ_CMD,
    STATE_ALL_READ_CMD,
    GET_ALL_ORGS,
    GET_UNITS_OF_AN_ORG,
    READ_DEPTS_OF_A_UNIT,
    CREATE_AN_EMP,
    READ_ALL_DOC_TYPES,
    READ_ID_DOC_TYPES,
    READ_JOB_ROLES_OF_AN_ORG,
    READ_DSGNS_OF_A_JOB_ROLE,
    READ_SALARY_OF_JOB_ROLE,
    READ_OPT_BENEFIT_OF_JOB_ROLE,
    UPDATE_EMP_ADDL_DETLS_OF_AN_EMP,
    GET_EMP_SEARCH_IN_HIERARCHY,
    UPDATE_EMP_ADDRESS_OF_AN_EMP,
    DEFAULT
}
