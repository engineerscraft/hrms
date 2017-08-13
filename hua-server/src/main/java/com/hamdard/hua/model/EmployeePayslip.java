package com.hamdard.hua.model;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * /api/#_payslip
 * 
 */
@XmlRootElement
public class EmployeePayslip {
	private String month;
	private int year;
	private double totalEarning;
	private double totalDeduction;
	private double netPayable;
	private String payslipFile;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getTotalEarning() {
		return totalEarning;
	}
	public void setTotalEarning(double totalEarning) {
		this.totalEarning = totalEarning;
	}
	public double getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public double getNetPayable() {
		return netPayable;
	}
	public void setNetPayable(double netPayable) {
		this.netPayable = netPayable;
	}
	public String getPayslipFile() {
		return payslipFile;
	}
	public void setPayslipFile(String payslipFile) {
		this.payslipFile = payslipFile;
	}
	@Override
	public String toString() {
		return "EmployeePayslip [month=" + month + ", year=" + year + ", totalEarning=" + totalEarning
				+ ", totalDeduction=" + totalDeduction + ", netPayable=" + netPayable + ", payslipFile=" + payslipFile
				+ "]";
	}
	
	
}
