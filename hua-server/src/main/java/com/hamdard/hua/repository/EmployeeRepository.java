package com.hamdard.hua.repository;

import java.util.List;

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
