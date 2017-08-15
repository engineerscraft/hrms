package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.EmployeePayslip;

public class EmployeePayslipRowMapper implements RowMapper <EmployeePayslip>{

	public EmployeePayslip mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeePayslip employeePayslip=new EmployeePayslip();
		employeePayslip.setMonth(rs.getString("MONTH"));
		employeePayslip.setYear(rs.getInt("YEAR"));
		employeePayslip.setTotalEarning(rs.getDouble("TOTAL_EARNING"));
		employeePayslip.setTotalDeduction(rs.getDouble("TOTAL_DEDUCTION"));
		employeePayslip.setNetPayable(rs.getDouble("NET_PAYABLE"));
		employeePayslip.setPayslipFile(rs.getString("PAYSLIP_FILE"));
		return employeePayslip;
	}

}
