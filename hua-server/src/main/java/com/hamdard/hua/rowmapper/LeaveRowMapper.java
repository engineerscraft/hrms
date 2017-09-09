package com.hamdard.hua.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hamdard.hua.model.Employee.Leave;

public class LeaveRowMapper implements RowMapper<Leave> {

    public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Leave leave = new Leave();
    	leave.setAvailedCl(rs.getInt("availed_cl"));
    	leave.setAvailedPaternityMaternityLeave(rs.getInt("availed_paternity_maternity_leave"));
    	leave.setAvailedPl(rs.getInt("availed_pl"));
    	leave.setAvailedSickLeave(rs.getInt("availed_sick_leave"));
    	leave.setAvailedSpecialLeave(rs.getInt("availed_special_leave"));
    	
    	leave.setEligibleCl(rs.getInt("eligible_cl"));
    	leave.setEligiblePaternityMaternityLeave(rs.getInt("eligible_paternity_maternity_leave"));
    	leave.setEligiblePl(rs.getInt("eligible_pl"));
    	leave.setEligibleSickLeave(rs.getInt("eligible_sick_leave"));
    	leave.setEligibleSpecialLeave(rs.getInt("eligible_special_leave"));
    	
    	leave.setRemainingCl(rs.getInt("remaining_cl"));
    	leave.setRemainingPaternityMaternityLeave(rs.getInt("remaining_paternity_maternity_leave"));
    	leave.setRemainingPl(rs.getInt("remaining_pl"));
    	leave.setRemainingSickLeave(rs.getInt("remaining_sick_leave"));
    	leave.setRemainingSpecialLeave(rs.getInt("remaining_special_leave"));
    	    	
        return leave;
    }

}
