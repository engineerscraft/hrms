package com.hamdard.hua.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.InternalServerErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hamdard.hua.model.Employee;
import com.hamdard.hua.rowmapper.EmployeeRowMapper;

@Component
public class EmployeeRepository {

    private static final Logger logger = LogManager.getLogger(EmployeeRepository.class);
    private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.employee.list}")
    private String employeeListSql;

    @Value("${sql.employee.nextId}")
    private String employeeIdSql;

    @Value("${sql.employee.create}")
    private String employeeCreateSql;

    @Value("${sql.employee.get.byId}")
    private String employeeGetByIdSql;

    public List<Employee> getAllEmployees(){
    	try {
            logger.info(sqlMarker, employeeListSql);
            List<Employee> employees = (List<Employee>) jdbcTemplate.query(employeeListSql, new EmployeeRowMapper());
            logger.debug("Retrieved accounts: {}", () -> employees);
            return employees;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new InternalServerErrorException();
        }
    }
    
    public Employee createEmployee(Employee e){
    	logger.info(sqlMarker, employeeCreateSql);
    	Long employeeId = jdbcTemplate.queryForObject(employeeIdSql, new Object[] {}, Long.class);
		//ID,FIRST_NAME,MIDDLE_NAME,LAST_NAME,EMAIL_ADDRESS,
    	//COLLEGE_NAME,STREET_ADDRESS,CITY,CONTACT_NUMBER,EMERGENCY_CONTACT_NAME,EMERGENCY_CONTACT_NUMBER,DESIGNATION_ID,DATE_OF_BIRTH,QUALIFICATION
    	jdbcTemplate.update(employeeCreateSql, employeeId,e.getFirstName(),e.getMiddleName(),e.getLastName(),e.getEmailAddress(),
    			e.getCollegeId(),e.getStreetAddress(),e.getCityId(),e.getContactNumber(),e.getEmergencyContact(),e.getEmergencyContactNumber(),
    			e.getDesignationId(),e.getDateOfBirth(),e.getQualification(),e.getPostalCode(),e.getDepartmentId());
    	return getEmployeeById(employeeId);
    }
    public List<String> getValues(String columnName, String valueLike){
    	String sql ="SELECT DISTINCT <COLUMN_NAME> from employee where <COLUMN_NAME> like '<COLUMN_VALUE>%' LIMIT 5";
    	sql=sql.replace("<COLUMN_NAME>", columnName);
    	sql=sql.replace("<COLUMN_VALUE>", valueLike);
    	List<String> values = (List<String>) jdbcTemplate.queryForList(sql, String.class);
    	return values;
    }
    
    public List<Employee> searchByColumnMap(Map<String, Object> columnMap){
    	logger.info(sqlMarker, columnMap);
    	String sql="SELECT * FROM EMPLOYEE WHERE ";
    	int i=0;
    	ArrayList<Object> params=new ArrayList<Object>();
    	for(String key: columnMap.keySet()){
    		params.add(columnMap.get(key));
    		if(i++ ==0){
    			sql=sql+key +"= ? ";
    		}else{
    			sql=sql+ " AND " + key +"= ? ";
    		}
    	}
    	Object[] input=params.toArray(new Object[0]);
    	logger.info(sqlMarker, sql);
    	return jdbcTemplate.query(sql,input,new EmployeeRowMapper());
    }
    public Employee getEmployeeById(Long id){
    	try {
    		Object[] args={id};
            logger.info(sqlMarker, employeeGetByIdSql);
            Employee employee = (Employee) jdbcTemplate.queryForObject(employeeGetByIdSql,args, new EmployeeRowMapper());
            logger.debug("Retrieved accounts: {}", () -> employee);
            return employee;
        } catch (Exception e) {
            logger.error("No parameter found", e);
            throw new InternalServerErrorException();
        }
    }

}
