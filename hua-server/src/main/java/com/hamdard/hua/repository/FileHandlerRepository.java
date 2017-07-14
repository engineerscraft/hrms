package com.hamdard.hua.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FileHandlerRepository {
	private static final Logger logger = LogManager.getLogger(FileHandlerRepository.class);
	private static final Marker sqlMarker = MarkerManager.getMarker("SQL");

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${sql.update.employee.image.by.empId}")
	private String updateEmployeeImageByEmpId;

	public int insertEmployeeImage(byte[] employeeImage, String employeeId) throws Exception {

		logger.info(sqlMarker, updateEmployeeImageByEmpId);
		logger.info(sqlMarker, "Params {}, {}", () -> employeeImage, () -> employeeId);
		int rowsAffected = jdbcTemplate.update(updateEmployeeImageByEmpId, new Object[] { employeeImage, employeeId });
		return rowsAffected;

	}
}
