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
public class Country {
	private int countryId;
	private String countryName;

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
