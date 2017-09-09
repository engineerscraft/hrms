package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Employee.EmployeeTax;
import com.hamdard.hua.model.TaxComponent;

public class EmployeeTaxRowMapper implements RowMapper<EmployeeTax> {

	public EmployeeTax mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeTax employeeTax = new EmployeeTax();
		TaxComponent taxComponent = new TaxComponent();
		taxComponent.setTaxCompDescription(rs.getString("tax_comp_description"));
		taxComponent.setTaxCompId(rs.getInt("tax_comp_id"));
		taxComponent.setTaxCompName(rs.getString("tax_comp_name"));
		employeeTax.setEntryBy(rs.getString("entry_by"));
		employeeTax.setEntryDate(rs.getDate("entry_date"));
		employeeTax.setTaxComponent(taxComponent);
		employeeTax.setTaxCompValue(rs.getDouble("tax_comp_value"));

		return employeeTax;
	}

}
