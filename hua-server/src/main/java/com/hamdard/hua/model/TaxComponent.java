package com.hamdard.hua.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaxComponent {
	private int taxCompId;
	private String taxCompName;
	private String taxCompDescription;
	
	public int getTaxCompId() {
		return taxCompId;
	}
	public void setTaxCompId(int taxCompId) {
		this.taxCompId = taxCompId;
	}
	public String getTaxCompName() {
		return taxCompName;
	}
	public void setTaxCompName(String taxCompName) {
		this.taxCompName = taxCompName;
	}
	public String getTaxCompDescription() {
		return taxCompDescription;
	}
	public void setTaxCompDescription(String taxCompDescription) {
		this.taxCompDescription = taxCompDescription;
	}
}
