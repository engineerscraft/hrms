package com.hamdard.hua.model;

import javax.validation.constraints.NotNull;

/**
 * @author Somdeb
 *
 */
public class Unit {
	private int unitId;
	private String unitName;
	@NotNull(message = "Organization ID cannot be empty")
	private int orgId;
	private String empIdPrefix;
	private String empIdSeqName;
	private String address;

	/**
	 * @return the unitId
	 */
	public int getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId
	 *            the unitId to set
	 */
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	
	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName
	 *            the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the orgId
	 */
	public int getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the empIdPrefix
	 */
	public String getEmpIdPrefix() {
		return empIdPrefix;
	}

	/**
	 * @param empIdPrefix the empIdPrefix to set
	 */
	public void setEmpIdPrefix(String empIdPrefix) {
		this.empIdPrefix = empIdPrefix;
	}

	/**
	 * @return the empIdSeqName
	 */
	public String getEmpIdSeqName() {
		return empIdSeqName;
	}

	/**
	 * @param empIdSeqName the empIdSeqName to set
	 */
	public void setEmpIdSeqName(String empIdSeqName) {
		this.empIdSeqName = empIdSeqName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
}
