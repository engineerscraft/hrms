/**
 * 
 */
package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Biswajit
 *
 */
@XmlRootElement
public class District {

	private int districtId;
	private String districtName;
	private int stateId;

	/**
	 * @return the districtId
	 */
	public int getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId
	 *            the districtId to set
	 */
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName
	 *            the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

}
